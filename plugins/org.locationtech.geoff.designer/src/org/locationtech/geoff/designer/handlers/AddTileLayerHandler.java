package org.locationtech.geoff.designer.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.common.command.CompoundCommand;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.designer.IEditingService;

public class AddTileLayerHandler {
	@Execute
	public void execute(IEditingService editingService, GeoMap geoMap) {
		CompoundCommand cc = new CompoundCommand();

		if (!cc.isEmpty()) {
			editingService.execute(cc);
		}
	}
}
