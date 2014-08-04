package org.locationtech.geoff.designer.views;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class LayersView {

	@PostConstruct
	public void createUI(Composite parent) {
		parent.setLayout(new FillLayout());
		Label test = new Label(parent, SWT.None);
		test.setText("Layers View");
	}
}
