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
package org.locationtech.geoff.showcase.examples;

import static org.locationtech.geoff.core.Geoff.osmSource;
import static org.locationtech.geoff.core.Geoff.tileLayer;
import static org.locationtech.geoff.core.Geoff.trans;
import static org.locationtech.geoff.core.Geoff.view2d;

import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.showcase.AbstractExampleGeoMap;

public class GeoCodingExample extends AbstractExampleGeoMap {
	protected GeoMap doCreateMap() {
		Geoff g = Geoff
				.createMap(
						"Geo Coding Example",
						"Shows a Map with a marker layer whose POIs have been geo coded "
								+ "at run time using the geo coding service interface.");

		g.view(view2d(trans("EPSG:4326", "EPSG:3857", 8, 53), 3));
		g.addLayer(tileLayer(osmSource()));

		//
		// {
		// Markers markersLayer = LayersFactory.eINSTANCE.createMarkers();
		// markersLayer.setName("Cities");
		// map.getLayers().add(markersLayer);
		//
		// String[] cities = new String[] { "Berlin", "Paris", "London",
		// "Madrid", "Rom" };
		//
		// IGeocodingService geo = IGeocodingService.Util.getFirstFound();
		//
		// for (String city : cities) {
		// List<POI> results = geo.executeQuery(city);
		//
		// if (!results.isEmpty()) {
		// POI poi = results.get(0);
		// Marker marker = MarkersFactory.eINSTANCE.createMarker();
		// marker.setName(poi.getDescription());
		//
		// LonLat lonLat = TypesFactory.eINSTANCE.createLonLat();
		// lonLat.setLon((float) poi.getLatLon().getLon());
		// lonLat.setLat((float) poi.getLatLon().getLat());
		// marker.setLonLat(lonLat);
		// markersLayer.getMarkers().add(marker);
		// }
		// }
		// }

		return g.get();
	}
}
