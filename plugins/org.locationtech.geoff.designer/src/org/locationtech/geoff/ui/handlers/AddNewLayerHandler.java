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
package org.locationtech.geoff.ui.handlers;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.widgets.Shell;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.core.IGeoMapService;
import org.locationtech.geoff.layer.VectorLayer;
import org.locationtech.geoff.source.SourceFormat;
import org.locationtech.geoff.source.VectorSource;
import org.locationtech.geoff.ui.parts.LayersUI;

public class AddNewLayerHandler {

	@Execute
	public void execute(Shell shell, EPartService partService) {
		IGeoMapService geoMapService = LayersUI.getGeoMapService(partService);
		VectorSource source = Geoff.vectorSource();
		source.setFormat(SourceFormat.INTERNAL);
		VectorLayer layer = Geoff.vectorLayer(source);
		layer.setShortDescription("New Layer");
		Geoff.populateDefaultStyles(layer);
		geoMapService.addLayer(layer);
	}

	@CanExecute
	public boolean canExecute(EPartService partService) {
		return LayersUI.getGeoMapService(partService) != null;
	}
}
