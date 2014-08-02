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
package org.locationtech.geoff.showcase.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.locationtech.geoff.showcase.IExampleGeoMap;
import org.locationtech.geoff.ui.swt.GeoMapComposite;

public class PreviewComposite extends Composite {
	private GeoMapComposite mapComposite;

	public PreviewComposite(Composite parent, int style) {
		super(parent, style);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		setLayout(layout);

		mapComposite = new GeoMapComposite(this, SWT.None);
		mapComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	public GeoMapComposite getMapComposite() {
		return mapComposite;
	}

	public void setMapProvider(IExampleGeoMap mapProvider) {
		mapComposite.loadMap(mapProvider.getRegistryToken());
	}
}
