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

import static org.locationtech.geoff.core.Geoff.mapQuestSource;
import static org.locationtech.geoff.core.Geoff.tileLayer;
import static org.locationtech.geoff.core.Geoff.xyLocation;

import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.RendererHint;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.showcase.AbstractExampleGeoMap;

public class MapQuestExample extends AbstractExampleGeoMap {
	protected GeoMap doCreateMap() {
		Geoff g = Geoff
				.createMap("MapQuest", "A simple MapQuest base layer example.",
						RendererHint.DOM)
				.view(xyLocation(0, 0, "EPSG:4326"), 2)
				.addLayer(tileLayer(mapQuestSource()));
		return g.get();
	}
}
