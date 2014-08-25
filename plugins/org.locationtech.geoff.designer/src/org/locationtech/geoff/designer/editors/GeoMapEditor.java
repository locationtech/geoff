package org.locationtech.geoff.designer.editors;

import java.net.URI;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.ui.swt.GeoMapComposite;

public class GeoMapEditor extends EditorPart implements IEditingDomainProvider {

	private AdapterFactory adapterFactory;
	private AdapterFactoryEditingDomain editingDomain;
	private GeoMapComposite geoMapComposite;
	private BasicCommandStack commandStack;
	private ResourceSet resourceSet;

	private GeoMap geoMap;

	@Override
	public void doSave(IProgressMonitor monitor) {

	}

	@Override
	public void doSaveAs() {

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		if (input instanceof FileEditorInput) {
			FileEditorInput fInput = (FileEditorInput) input;
			URI uri = fInput.getURI();
			geoMap = Geoff.fromURI(uri);
		} else {
			throw new UnsupportedOperationException(
					"Provided editor input not supported: " + input);
		}

		setSite(site);
		setInput(input);

		adapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		commandStack = new BasicCommandStack();
		editingDomain = new AdapterFactoryEditingDomain(adapterFactory,
				commandStack);
		resourceSet = new ResourceSetImpl();
		resourceSet.eAdapters().add(new EditingDomainAdapter());
		resourceSet.getResources().add(geoMap.eResource());
		geoMap.eResource().eAdapters().add(new EditingDomainAdapter());
		geoMap.eAdapters().add(new EditingDomainAdapter());
	}

	@Override
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		geoMapComposite = new GeoMapComposite(parent, SWT.None);
		geoMapComposite.loadMap(geoMap);
	}

	@Override
	public void setFocus() {
		geoMapComposite.setFocus();
		IEclipseContext context = (IEclipseContext) getSite().getService(
				IEclipseContext.class);
		context.getParent().set(GeoMap.class, geoMap);
	}

	private class EditingDomainAdapter extends AdapterImpl implements
			IEditingDomainProvider {
		@Override
		public boolean isAdapterForType(Object type) {
			return IEditingDomainProvider.class.equals(type);
		}

		@Override
		public EditingDomain getEditingDomain() {
			return editingDomain;
		}
	}
}
