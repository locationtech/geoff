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
package org.locationtech.geoff.ui.handlers;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.ecore.EClass;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.core.IGeoMapService;
import org.locationtech.geoff.layer.TileLayer;
import org.locationtech.geoff.source.SourcePackage;
import org.locationtech.geoff.source.TileSource;

public class AddTileLayerHandler {
	private Map<String, EClass> tileProvidersMap = new HashMap<String, EClass>();

	{
		tileProvidersMap.put("OSM", SourcePackage.Literals.OSM);
		tileProvidersMap.put("BingMaps", SourcePackage.Literals.BING_MAPS);
		tileProvidersMap.put("MapQuest", SourcePackage.Literals.MAP_QUEST);
	}

	@Execute
	public void execute(IGeoMapService geoMapService, @Named("tileProvider") String tileProviderKey) {
		EClass eClass = tileProvidersMap.get(tileProviderKey);

		if (eClass == null) {
			return;
		}

		TileSource tileSource = Geoff.instance(eClass);
		TileLayer tileLayer = Geoff.tileLayer(tileSource);
		geoMapService.addLayer(tileLayer);
	}
}
