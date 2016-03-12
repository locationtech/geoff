package org.locationtech.geoff.ui.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.core.IGeoMapService;
import org.locationtech.geoff.layer.Layer;
import org.locationtech.geoff.layer.LayerPackage;
import org.locationtech.geoff.layer.TileLayer;
import org.locationtech.geoff.layer.VectorLayer;
import org.locationtech.geoff.source.Source;
import org.locationtech.geoff.source.SourcePackage;

public class AddNewLayerHandler {

	@Execute
	public void execute(IGeoMapService geoMapService, Shell shell) {
		Collection<Source> sources = (Collection<Source>) Geoff.samplesOf(SourcePackage.Literals.SOURCE);
		AdapterFactory af = geoMapService.adaptTo(AdapterFactory.class);
		AdapterFactoryLabelProvider lp = new AdapterFactoryLabelProvider(af);
		ElementListSelectionDialog diag = new ElementListSelectionDialog(shell, lp);
		diag.setMessage("Choose a source to create a new layer");
		diag.setMultipleSelection(false);
		diag.setBlockOnOpen(true);
		diag.setElements(sources.toArray());
		diag.open();
		Object[] result = diag.getResult();
		lp.dispose();

		if (result != null) {
			List<Layer> layers = new ArrayList<Layer>();

			for (Object object : result) {
				EClass type = null;

				if (object instanceof TileLayer) {
					type = LayerPackage.Literals.TILE_LAYER;
				} else if (object instanceof VectorLayer) {
					type = LayerPackage.Literals.VECTOR_LAYER;
				}

				if (type == null) {
					continue;
				}

				Source source = (Source) object;
				Layer layer = Geoff.instance(type);
				layer.setSource(source);
			}

			geoMapService.batchChanges(() -> {
				layers.forEach((l) -> geoMapService.addLayer(l));
			});
		}
	}

}
