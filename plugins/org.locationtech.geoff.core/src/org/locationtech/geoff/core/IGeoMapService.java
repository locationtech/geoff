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
package org.locationtech.geoff.core;

import java.util.List;

import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.layer.Layer;

public interface IGeoMapService extends IChangeSupport {
	List<Layer> listLayers();

	void addLayer(Layer l);

	void removeLayer(Layer l);

	GeoMap getGeoMap();

	void shutdown();

	<T> T adaptTo(Class<T> type);

}
