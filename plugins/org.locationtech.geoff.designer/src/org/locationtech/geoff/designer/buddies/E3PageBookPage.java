package org.locationtech.geoff.designer.buddies;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.Page;
import org.locationtech.geoff.GeoMap;

public abstract class E3PageBookPage extends Page {
	private Control control;
	private DataBindingContext dataBindingContext = doCreateDataBindingContext();

	@Override
	public final void createControl(Composite parent) {
		control = doCreateControl(parent);
	}

	protected DataBindingContext doCreateDataBindingContext() {
		return new DataBindingContext();
	}

	public DataBindingContext getDataBindingContext() {
		return dataBindingContext;
	}

	protected abstract Control doCreateControl(Composite parent);

	@Override
	public final Control getControl() {
		return control;
	}

	@Override
	public void setFocus() {
		if (control != null && !control.isDisposed()) {
			control.setFocus();
		}
	}

	@Override
	public final void makeContributions(IMenuManager menuManager,
			IToolBarManager toolBarManager, IStatusLineManager statusLineManager) {
		// managers are available via @Inject
	}

	@Override
	public final void setActionBars(IActionBars actionBars) {
		super.setActionBars(actionBars);
	}
}