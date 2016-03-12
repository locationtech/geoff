package org.locationtech.geoff.core;

import java.io.File;

import org.locationtech.geoff.core.internal.GeoMapService;

public class GeoMapServiceFactory {

	public static IGeoMapService create(File file) {
		return new GeoMapService(file);
	}

}
