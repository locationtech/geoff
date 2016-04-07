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

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.equinox.http.servlet.ExtendedHttpService;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.core.GeoMapServiceFactory;
import org.locationtech.geoff.core.IGeoMapService;
import org.locationtech.geoff.core.logging.LogUtil;
import org.locationtech.geoff.ui.UIConsts;
import org.locationtech.geoff.ui.PersistAs;
import org.locationtech.geoff.ui.swt.GeoMapComposite;
import org.locationtech.geoff.ui.swt.IGeoMapWidget;
import org.locationtech.geoff.ui.swt.IPrintable;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;

public class GeoMapUI {
	private GeoMapComposite geoMapComposite;

	@Inject
	private MPart part;

	@Inject
	private IEventBroker eventBrowker;

	IGeoMapService geoMapService;

	private Consumer<EventObject> changeConsumer = (event) -> {
		if (geoMapComposite.isDisposed()) {
			return;
		}

//		geoMapComposite.getDisplay().asyncExec(() -> {
//			// TODO incremental update instead of full reload
//			geoMapComposite.reloadMap();
//		});

		boolean canUndo = geoMapService.canUndo();
		part.setDirty(canUndo);
		eventBrowker.post(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC, UIConsts.EDIT_UNDO);
		eventBrowker.post(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC, UIConsts.EDIT_REDO);
		eventBrowker.post(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC, UIConsts.FILE_SAVE);
		eventBrowker.post(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC, UIConsts.FILE_SAVE_AS);
	};

	private String alias;

	@Persist
	public void doSave(IProgressMonitor monitor) {
		SafeRunnable.run(new SafeRunnable() {

			@Override
			public void run() throws Exception {
				geoMapService.save();
				part.setDirty(false);
			}
		});
	}

	@PersistAs
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

	private void init(IEclipseContext context, IFile file) {
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
		context.set(IGeoMapService.class, geoMapService);
	}

	@PostConstruct
	public void createPartControl(Composite parent, IEclipseContext context) {
		IFile input = context.get(IFile.class);
		init(context, input);

		GridLayoutFactory.fillDefaults().numColumns(1).spacing(0, 1).applyTo(parent);
		Composite top = new Composite(parent, SWT.None);
		GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).applyTo(top);
		top.setLayout(new FillLayout());
		Composite center = new Composite(parent, SWT.None);
		GridDataFactory.fillDefaults().grab(true, true).align(SWT.FILL, SWT.FILL).applyTo(center);
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
		context.set(IGeoMapWidget.class, geoMapComposite);

		EditingUI editingUI = new EditingUI();
		IEclipseContext local = context.createChild();
		local.set(Composite.class, top);
		ContextInjectionFactory.inject(editingUI, local);
	}

	@Focus
	public void setFocus() {
		geoMapComposite.setFocus();
	}

	@PreDestroy
	public void dispose(IEclipseContext context) {
		ExtendedHttpService httpService = (ExtendedHttpService) context.get(HttpService.class);
		httpService.unregister(alias);

		geoMapComposite = null;
		geoMapService.shutdown();
	}
}
