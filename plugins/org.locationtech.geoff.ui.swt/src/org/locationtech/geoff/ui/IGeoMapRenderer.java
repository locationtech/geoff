package org.locationtech.geoff.ui;

import org.locationtech.geoff.GeoMap;

/**
 * An interface which can be implemented to render a {@link GeoMap} instance in
 * a specific environment, for example SWT or JavaFX.
 */
public interface IGeoMapRenderer {
	/**
	 * Render the provided {@link GeoMap} instance.
	 * 
	 * @param geoMap
	 *            the map to render
	 * @param baseUrl
	 * 
	 */
	void render(GeoMap geoMap, IRenderSettings settings);

}