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

import static org.locationtech.geoff.core.Geoff.mapquestSource;
import static org.locationtech.geoff.core.Geoff.tileLayer;
import static org.locationtech.geoff.core.Geoff.trans;
import static org.locationtech.geoff.core.Geoff.view2d;

import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.RendererHint;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.showcase.AbstractExampleGeoMap;

public class MapQuestOpenAerialExample extends AbstractExampleGeoMap {
	protected GeoMap doCreateMap() {
		Geoff g = Geoff
				.createMap("MapQuestOpenAerial",
						"A simple MapQuestOpenAerial base layer example.",
						RendererHint.DOM)
				.view(view2d(trans("EPSG:4326", "EPSG:3857", 0, 0), 2))
				.addLayer(tileLayer(mapquestSource()));
		return g.get();
	}
}
