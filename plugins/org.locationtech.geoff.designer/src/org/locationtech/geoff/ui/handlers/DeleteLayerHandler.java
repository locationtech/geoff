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
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.locationtech.geoff.core.IGeoMapService;
import org.locationtech.geoff.layer.Layer;
import org.locationtech.geoff.ui.parts.LayersUI;

public class DeleteLayerHandler {
	@Execute
	public void run(EPartService partService) {
		IGeoMapService geoMapService = LayersUI.getGeoMapService(partService);
		Layer layer = LayersUI.getLayerSelection(partService);
		geoMapService.removeLayer(layer);
	}

	@CanExecute
	public boolean isHandled(EPartService partService, MWindow window) {
		IGeoMapService geoMapService = LayersUI.getGeoMapService(partService);
		return geoMapService != null && LayersUI.getLayerSelection(partService) != null;
	}
}
