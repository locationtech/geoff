package org.locationtech.geoff.designer.views;

import javax.annotation.PostConstruct;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class GeocodingUI {

	@PostConstruct
	public void createUI(Composite container) {
		GridLayoutFactory.fillDefaults().numColumns(1).applyTo(container);
		Text text = new Text(container, SWT.V_SCROLL | SWT.WRAP);

		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(text);

	}
}
