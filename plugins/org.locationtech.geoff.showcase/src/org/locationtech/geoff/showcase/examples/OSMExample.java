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

public class OSMExample extends AbstractExampleGeoMap {
	protected GeoMap doCreateMap() {
		Geoff g = Geoff
				.createMap("OpenStreetMap", "A simple OSM base layer example.")
				.view(view2d(trans("EPSG:4326", "EPSG:3857", 8.2128d, 53.1403),
						10)).addLayer(tileLayer(osmSource()));
		return g.get();
	}
}
