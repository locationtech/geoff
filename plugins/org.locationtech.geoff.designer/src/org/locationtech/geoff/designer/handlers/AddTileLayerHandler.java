package org.locationtech.geoff.designer.handlers;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.GeoffPackage;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.designer.IEditingService;
import org.locationtech.geoff.designer.e3x.E3Handler;
import org.locationtech.geoff.layer.TileLayer;
import org.locationtech.geoff.source.SourcePackage;
import org.locationtech.geoff.source.TileSource;

@SuppressWarnings("rawtypes")
public class AddTileLayerHandler {
	private Map<String, EClass> tileProvidersMap = new HashMap<String, EClass>();

	{
		tileProvidersMap.put("OSM", SourcePackage.Literals.OSM);
		tileProvidersMap.put("BingMaps", SourcePackage.Literals.OSM);
		tileProvidersMap.put("MapQuest", SourcePackage.Literals.OSM);
	}

	@Execute
	public void execute(IEditingService editingService, GeoMap geoMap, @Named(E3Handler.PARAM_PARAMETERS) Map params) {
		String tileProviderKey = (String) params.get("tileProvider");

		if (tileProviderKey == null) {
			return;
		}

		EClass eClass = tileProvidersMap.get(tileProviderKey);

		if (eClass == null) {
			return;
		}

		TileSource tileSource = Geoff.instance(eClass);
		TileLayer tileLayer = Geoff.tileLayer(tileSource);
		AddCommand addLayer = (AddCommand) editingService.createAddCommand(geoMap,
				GeoffPackage.Literals.GEO_MAP__LAYERS, tileLayer);
		addLayer.setLabel("Add layer: " + tileProviderKey);
		editingService.execute(addLayer);
	}
}
