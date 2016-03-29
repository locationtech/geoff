package org.locationtech.geoff.ui.handlers;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.locationtech.geoff.core.IGeoMapService;
import org.locationtech.geoff.ui.parts.LayersUI;

public class RedoHandler {
	@Execute
	public void run(EPartService partService) {
		IGeoMapService geoMapService = LayersUI.getGeoMapService(partService);
		geoMapService.redo();
	}

	@CanExecute
	public boolean isHandled(EPartService partService, MWindow window) {
		IGeoMapService geoMapService = LayersUI.getGeoMapService(partService);
		return geoMapService == null ? false : geoMapService.canRedo();
	}
}