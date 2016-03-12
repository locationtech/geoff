package org.locationtech.geoff.ui.handlers;

import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.locationtech.geoff.core.IGeoMapService;
import org.locationtech.geoff.layer.Layer;

public class DeleteLayerHandler {
	@Execute
	public void execute(IGeoMapService geoMapService, IStructuredSelection selection) {
		List<Layer> list = selection.toList();

		geoMapService.batchChanges(() -> {
			list.forEach((l) -> geoMapService.removeLayer(l));
		});
	}

}
