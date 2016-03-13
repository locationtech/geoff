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
package org.locationtech.geoff.ui.swt.events;

import java.util.HashMap;
import java.util.Map;

import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.Location;
import org.locationtech.geoff.core.Geoff;

public class HandlersManager {
	private static final HandlersManager INSTANCE = new HandlersManager();
	private Map<String, EventConsumer> handlers = new HashMap<String, EventConsumer>();

	private HandlersManager() {
		handlers.put("viewZoom", (map, args) -> {
			int zoom = ((Number) args[0]).intValue();
			map.getView().setZoom(zoom);
		});

		handlers.put("viewCenter", (map, args) -> {
			Object[] locs = (Object[]) args[0];
			double x = ((Number) locs[0]).doubleValue();
			double y = ((Number) locs[1]).doubleValue();
			String projectionCode = (String) args[1];
			Location xyz = Geoff.xyLocation(x, y, projectionCode);
			map.getView().setCenter(xyz);
		});
	}

	public static HandlersManager getInstance() {
		return INSTANCE;
	}

	public static interface EventConsumer {
		void consume(GeoMap geoMap, Object[] args);
	}

	public EventConsumer get(String evtName) {
		return handlers.get(evtName);
	}
}
