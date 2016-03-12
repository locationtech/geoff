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
