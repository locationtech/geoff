package org.locationtech.geoff.designer.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
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

public class GeoMapEditor extends EditorPart {
	private GeoMap geoMap;
	private GeoMapComposite geoMapComposite;

	@Override
	public void doSave(IProgressMonitor monitor) {

	}

	@Override
	public void doSaveAs() {

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);

		if (input instanceof FileEditorInput) {
			FileEditorInput fInput = (FileEditorInput) input;
			geoMap = Geoff.fromURI(fInput.getURI());
		}

		site.setSelectionProvider(new ISelectionProvider() {

			@Override
			public void setSelection(ISelection selection) {
				// not supported
			}

			@Override
			public void removeSelectionChangedListener(
					ISelectionChangedListener listener) {
				// TODO Auto-generated method stub
			}

			@Override
			public ISelection getSelection() {
				return new StructuredSelection(geoMap);
			}

			@Override
			public void addSelectionChangedListener(
					ISelectionChangedListener listener) {
				// TODO Auto-generated method stub
			}
		});
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
	}
}
