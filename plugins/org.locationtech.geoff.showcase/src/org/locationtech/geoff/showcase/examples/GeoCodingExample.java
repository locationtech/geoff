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
package org.locationtech.geoff.showcase.examples;

import static org.locationtech.geoff.core.Geoff.feature;
import static org.locationtech.geoff.core.Geoff.icon;
import static org.locationtech.geoff.core.Geoff.osmSource;
import static org.locationtech.geoff.core.Geoff.pointGeom;
import static org.locationtech.geoff.core.Geoff.style;
import static org.locationtech.geoff.core.Geoff.tileLayer;
import static org.locationtech.geoff.core.Geoff.vectorLayer;
import static org.locationtech.geoff.core.Geoff.vectorSource;
import static org.locationtech.geoff.core.Geoff.xyLocation;

import java.util.List;

import org.locationtech.geoff.Feature;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.geocoding.IGeocodingService;
import org.locationtech.geoff.geocoding.POI;
import org.locationtech.geoff.geocoding.POI.LatLon;
import org.locationtech.geoff.geom.Geometry;
import org.locationtech.geoff.showcase.AbstractExampleGeoMap;
import org.locationtech.geoff.source.VectorSource;

public class GeoCodingExample extends AbstractExampleGeoMap {
	protected GeoMap doCreateMap() {
		Geoff g = Geoff.createMap("Geo Coding Example",
				"Shows a Map with a marker layer whose POIs have been geo coded "
						+ "at run time using the geocoding service interface.");

		g.view(xyLocation(8, 53, Geoff.EPSG4326_WGS84), 3);
		g.addLayer(tileLayer(osmSource()));

		{
			VectorSource vectorSource = vectorSource();
			g.addLayer(vectorLayer(vectorSource));

			String[] cities = new String[] { "Berlin", "Paris", "London",
					"Madrid", "Rom" };

			IGeocodingService geo = IGeocodingService.Util.getFirstFound();

			for (String city : cities) {
				List<POI> results = geo.executeQuery(city);

				if (!results.isEmpty()) {
					POI poi = results.get(0);
					LatLon latLon = poi.getLatLon();
					Geometry geometry = pointGeom(xyLocation(
							(float) latLon.getLon(), (float) latLon.getLat(),
							Geoff.EPSG4326_WGS84));
					Feature feature = feature(geometry,
							style(icon("/org.locationtech.geoff.showcase/resources/marker.gif")));
					vectorSource.getFeatures().add(feature);
				}
			}
		}

		return g.get();
	}
}
