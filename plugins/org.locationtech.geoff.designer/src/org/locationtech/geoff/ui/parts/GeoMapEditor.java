/*******************************************************************************
 * Copyright (c) 2016 Erdal Karaca and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Erdal Karaca - initial API and implementation
 *******************************************************************************/
package org.locationtech.geoff.ui.parts;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.EventObject;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.equinox.http.servlet.ExtendedHttpService;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.core.GeoMapServiceFactory;
import org.locationtech.geoff.core.IGeoMapService;
import org.locationtech.geoff.core.logging.LogUtil;
import org.locationtech.geoff.ui.swt.GeoMapComposite;
import org.locationtech.geoff.ui.swt.IGeoMapWidget;
import org.locationtech.geoff.ui.swt.IPrintable;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;

@SuppressWarnings("restriction")
public class GeoMapEditor extends EditorPart {
	public static final String ORG_LOCATIONTECH_GEOFF_EDITOR_SWITCHED = "org/locationtech/geoff/EditorSwitched";
	private GeoMapComposite geoMapComposite;
	private boolean canSave = false;

	private IGeoMapService geoMapService;

	private Consumer<EventObject> changeConsumer = (event) -> {
		if (geoMapComposite.isDisposed()) {
			return;
		}

		geoMapComposite.getDisplay().asyncExec(() -> {
			// TODO incremental update instead of full reload
			geoMapComposite.reloadMap();
		});

		IEventBroker eventBrowker = getSite().getService(IEventBroker.class);
		eventBrowker.post(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC, IWorkbenchCommandConstants.EDIT_UNDO);
		eventBrowker.post(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC, IWorkbenchCommandConstants.EDIT_REDO);

		canSave = geoMapService.canUndo();
		firePropertyChange(PROP_DIRTY);
	};

	private Object undoHandler = new Object() {
		@Execute
		public void run() {
			geoMapService.undo();
		}

		@CanExecute
		public boolean isHandled() {
			return geoMapService.canUndo();
		}
	};

	private Object redoHandler = new Object() {
		@Execute
		public void run() {
			geoMapService.redo();
		}

		@CanExecute
		public boolean isHandled() {
			return geoMapService.canRedo();
		}
	};

	private String alias;

	@Override
	public void doSave(IProgressMonitor monitor) {
		SafeRunnable.run(new SafeRunnable() {

			@Override
			public void run() throws Exception {
				geoMapService.save();
				canSave = false;
				firePropertyChange(PROP_DIRTY);
			}
		});
	}

	@Override
	public void doSaveAs() {
		FileDialog saveAs = new FileDialog(geoMapComposite.getShell(), SWT.SAVE);
		saveAs.setFilterExtensions(new String[] { "*.png" });
		String open = saveAs.open();

		if (open == null) {
			return;
		}

		File file = new File(open);

		geoMapComposite.print(IPrintable.Format.IMAGE_PNG, io -> {
			SafeRunnable.run(new SafeRunnable() {

				@Override
				public void run() throws Exception {
					try (InputStream in = io) {
						file.createNewFile();
						Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
					}
				}
			});
		});
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		IEclipseContext context = getContext(site);
		setSite(site);
		setInput(input);

		if (input instanceof FileEditorInput) {
			FileEditorInput fInput = (FileEditorInput) getEditorInput();
			init(context, fInput);
		} else {
			throw new UnsupportedOperationException("Provided editor input not supported: " + input);
		}

		EHandlerService hs = (EHandlerService) getSite().getService(EHandlerService.class);
		hs.activateHandler(IWorkbenchCommandConstants.EDIT_UNDO, undoHandler);
		hs.activateHandler(IWorkbenchCommandConstants.EDIT_REDO, redoHandler);

		context.set(IGeoMapService.class, geoMapService);
	}

	private void init(IEclipseContext context, FileEditorInput fInput) {
		IFile file = fInput.getFile();
		IProject project = file.getProject();
		geoMapService = GeoMapServiceFactory.create(file.getRawLocation().toFile());
		geoMapService.onChange(changeConsumer);
		alias = String.format("/workspace/%s", project.getName());

		ExtendedHttpService httpService = (ExtendedHttpService) context.get(HttpService.class);
		HttpContext hctx = new HttpContext() {

			@Override
			public URL getResource(String name) {
				IFile resource = project.getFile(name);

				try {
					return resource.getLocationURI().toURL();
				} catch (MalformedURLException e) {
					// resource does not exist
				}

				return null;
			}

			@Override
			public String getMimeType(String name) {
				return null;
			}

			@Override
			public boolean handleSecurity(HttpServletRequest request, HttpServletResponse response) throws IOException {
				return true;
			}
		};
		LogUtil.logErrorOnException(() -> {
			httpService.registerResources(alias, "/", hctx);
		});
	}

	private IEclipseContext getContext(IEditorSite site) {
		return (IEclipseContext) site.getService(IEclipseContext.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAdapter(Class<T> adapter) {
		if (IGeoMapService.class.equals(adapter)) {
			return (T) geoMapService;
		}

		return super.getAdapter(adapter);
	}

	@Override
	public boolean isDirty() {
		return canSave;
	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayoutFactory.fillDefaults().numColumns(1).applyTo(parent);
		Composite top = new Composite(parent, SWT.None);
		top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		top.setLayout(new FillLayout());
		Composite center = new Composite(parent, SWT.None);
		center.setLayoutData(new GridData(GridData.FILL_BOTH));
		center.setLayout(new FillLayout());
		geoMapComposite = new GeoMapComposite(center, SWT.None) {
			@Override
			protected String doGetResourcesPath() {
				String resourcesPath = String.format("%s/%s", alias.substring(1), "index-fullmap.html");
				return resourcesPath;
			}
		};
		final GeoMap geoMap = geoMapService.getGeoMap();
		geoMapComposite.loadMap(geoMap);
		IEclipseContext context = getSite().getService(IEclipseContext.class);
		context.set(IGeoMapWidget.class, geoMapComposite);
		
		EditingUI editingUI = new EditingUI();
		IEclipseContext local = context.createChild();
		local.set(Composite.class, top);
		ContextInjectionFactory.inject(editingUI, local);
	}

	@Override
	public void setFocus() {
		geoMapComposite.setFocus();
	}

	@Override
	public void dispose() {
		IEclipseContext context = (IEclipseContext) getSite().getService(IEclipseContext.class);
		ExtendedHttpService httpService = (ExtendedHttpService) context.get(HttpService.class);
		httpService.unregister(alias);

		EHandlerService hs = (EHandlerService) getSite().getWorkbenchWindow().getService(EHandlerService.class);
		hs.deactivateHandler(IWorkbenchCommandConstants.EDIT_UNDO, undoHandler);
		hs.deactivateHandler(IWorkbenchCommandConstants.EDIT_REDO, redoHandler);

		geoMapComposite = null;
		geoMapService.shutdown();

		super.dispose();
	}
}
