package org.locationtech.geoff.ui;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.locationtech.geoff.core.IGeoMapService;
import org.locationtech.geoff.layer.Layer;

public class DNDUtil {
	public static void setupDND(StructuredViewer viewer, IGeoMapService gms) {
		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
		Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance(), LocalSelectionTransfer.getTransfer(),
				FileTransfer.getInstance() };
		viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(viewer));
		viewer.addDropSupport(dndOperations, transfers, new ViewerDropAdapter(viewer) {

			@Override
			public boolean validateDrop(Object target, int operation, TransferData transferType) {
				Object selectedObject = getSelectedObject();
				return getCurrentTarget() instanceof Layer && selectedObject instanceof Layer;
			}

			@Override
			public boolean performDrop(Object data) {
				int op = getCurrentOperation();

				if (op == DND.DROP_MOVE) {
					Object target = getCurrentTarget();
					List<Layer> layers = gms.getGeoMap().getLayers();
					EList<Layer> ecoreLayers = (EList<Layer>) layers;
					int indexOf = layers.indexOf(target);
					int pos = getCurrentLocation();
					int currentIndex = layers.indexOf(getSelectedObject());
					int targetIndex = pos == ViewerDropAdapter.LOCATION_BEFORE ? indexOf : indexOf + 1;
					gms.execTX("Move layer", () -> {
						ecoreLayers.move(targetIndex, currentIndex);
					});
					return true;
				}

				return false;
			}
		});
	}
}
