package org.locationtech.geoff.designer.editors;

import java.net.URI;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.commands.ActionHandler;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.services.IServiceScopes;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IPublishListener;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.ServerUtil;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.designer.DesignerUtil;
import org.locationtech.geoff.designer.IEditingService;
import org.locationtech.geoff.designer.internal.EditingServiceImpl;
import org.locationtech.geoff.ui.swt.GeoMapComposite;

public class GeoMapEditor extends EditorPart implements IEditingDomainProvider {
	private AdapterFactory adapterFactory;
	private AdapterFactoryEditingDomain editingDomain;
	private GeoMapComposite geoMapComposite;
	private BasicCommandStack commandStack;
	private ResourceSet resourceSet;

	private GeoMap geoMap;
	private IHandlerActivation undoHandlerActivation;
	private IHandlerActivation redoHandlerActivation;
	private boolean canSave = false;
	private IEditingService editingService;
	private String url;

	@Override
	public void doSave(IProgressMonitor monitor) {
		SafeRunnable.run(new SafeRunnable() {

			@Override
			public void run() throws Exception {
				geoMap.eResource().save(null);
				canSave = false;
				FileEditorInput ei = (FileEditorInput) getEditorInput();
				IProject project = ei.getFile().getProject();
				project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
				firePropertyChange(PROP_DIRTY);
			}
		});
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if (input instanceof FileEditorInput) {
			FileEditorInput fInput = (FileEditorInput) input;
			URI uri = fInput.getURI();
			geoMap = Geoff.fromURI(uri);
			IFile file = fInput.getFile();
			IProject project = file.getProject();
			url = DesignerUtil.toWSTServerUrl(project);

			IModule module = ServerUtil.getModule(project);
			IServer[] serversByModule = ServerUtil.getServersByModule(module, new NullProgressMonitor());

			if (serversByModule != null && serversByModule.length > 0) {
				serversByModule[0].addPublishListener(new IPublishListener() {

					@Override
					public void publishStarted(IServer arg0) {
					}

					@Override
					public void publishFinished(IServer arg0, IStatus arg1) {
						geoMapComposite.loadHtmlByUrl(url);
					}
				});
			}
		} else {
			throw new UnsupportedOperationException("Provided editor input not supported: " + input);
		}

		setSite(site);
		setInput(input);

		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		commandStack = new BasicCommandStack();
		editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack);
		resourceSet = new ResourceSetImpl();
		resourceSet.eAdapters().add(new EditingDomainAdapter());
		resourceSet.getResources().add(geoMap.eResource());
		geoMap.eResource().eAdapters().add(new EditingDomainAdapter());
		geoMap.eAdapters().add(new EditingDomainAdapter());

		commandStack.addCommandStackListener(new CommandStackListener() {

			@Override
			public void commandStackChanged(EventObject event) {
				ICommandService cs = (ICommandService) getSite().getService(ICommandService.class);
				Map<String, Object> filter = new HashMap<>();
				filter.put(IServiceScopes.WINDOW_SCOPE, getSite().getPage().getWorkbenchWindow());
				cs.refreshElements(IWorkbenchCommandConstants.EDIT_UNDO, filter);
				cs.refreshElements(IWorkbenchCommandConstants.EDIT_REDO, filter);

				canSave = commandStack.canUndo();
				firePropertyChange(PROP_DIRTY);
			}
		});

		IHandlerService hs = (IHandlerService) getSite().getWorkbenchWindow().getService(IHandlerService.class);
		{
			ActionHandler handler = new ActionHandler(new Action() {
				@Override
				public void run() {
					commandStack.undo();
				}

				@Override
				public boolean isHandled() {
					return commandStack.canUndo();
				}
			});
			undoHandlerActivation = hs.activateHandler(IWorkbenchCommandConstants.EDIT_UNDO, handler);
		}
		{
			ActionHandler handler = new ActionHandler(new Action() {
				@Override
				public void run() {
					commandStack.redo();
				}

				@Override
				public boolean isHandled() {
					return commandStack.canRedo();
				}
			});
			redoHandlerActivation = hs.activateHandler(IWorkbenchCommandConstants.EDIT_REDO, handler);
		}

		IEclipseContext context = (IEclipseContext) getSite().getService(IEclipseContext.class);
		context.set(AdapterFactory.class, adapterFactory);
		context.set(EditingDomain.class, editingDomain);
		context.set(GeoMap.class, geoMap);

		editingService = ContextInjectionFactory.make(EditingServiceImpl.class, context);
		context.set(IEditingService.class, editingService);
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (IEditingService.class.equals(adapter)) {
			return editingService;
		}

		return super.getAdapter(adapter);
	}

	@Override
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	@Override
	public boolean isDirty() {
		return canSave;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		geoMapComposite = new GeoMapComposite(parent, SWT.None);
		geoMapComposite.loadHtmlByUrl(url);
	}

	@Override
	public void setFocus() {
		geoMapComposite.setFocus();
		IEclipseContext context = (IEclipseContext) getSite().getService(IEclipseContext.class);
		context.getParent().set(GeoMap.class, geoMap);
	}

	private class EditingDomainAdapter extends AdapterImpl implements IEditingDomainProvider {
		@Override
		public boolean isAdapterForType(Object type) {
			return IEditingDomainProvider.class.equals(type);
		}

		@Override
		public EditingDomain getEditingDomain() {
			return editingDomain;
		}
	}

	@Override
	public void dispose() {
		undoHandlerActivation.getHandlerService().deactivateHandler(undoHandlerActivation);
		undoHandlerActivation = null;
		redoHandlerActivation.getHandlerService().deactivateHandler(redoHandlerActivation);
		redoHandlerActivation = null;

		adapterFactory = null;
		commandStack = null;
		editingDomain = null;
		geoMap = null;
		geoMapComposite = null;
		resourceSet = null;

		super.dispose();
	}
}
