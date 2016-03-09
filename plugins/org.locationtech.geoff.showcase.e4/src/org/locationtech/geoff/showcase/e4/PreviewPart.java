/*******************************************************************************
 * Copyright (c) 2014 Erdal Karaca.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Erdal Karaca - initial API and implementation
 ******************************************************************************/
package org.locationtech.geoff.showcase.e4;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.locationtech.geoff.showcase.IExampleGeoMap;
import org.locationtech.geoff.showcase.swt.widgets.PreviewComposite;

public class PreviewPart {
	private PreviewComposite previewComposite;

	@PostConstruct
	public void createComposite(Composite parent) {
		parent.setLayout(new FillLayout());
		previewComposite = new PreviewComposite(parent, SWT.None);
	}

	@Inject
	public void setSelection(@Optional IExampleGeoMap mapProvider) {
		if (mapProvider == null) {
			return;
		}

		previewComposite.setMapProvider(mapProvider);
	}

	@Focus
	public void setFocus() {
		previewComposite.setFocus();
	}
}
