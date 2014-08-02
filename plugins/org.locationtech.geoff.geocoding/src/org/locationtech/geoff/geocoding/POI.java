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
package org.locationtech.geoff.geocoding;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple class for representing a geographical location (often called 'point
 * of interest').
 * 
 * @author Erdal Karaca
 * 
 */
public class POI {
	private String description;
	private LatLon latLon;
	private Map<String, Object> props = new HashMap<String, Object>();

	/**
	 * @return the description of this POI
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description of this POI
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the {@link LatLon} object which contains latitude/longitude
	 *         values
	 */
	public LatLon getLatLon() {
		return latLon;
	}

	/**
	 * @param latLon
	 *            the {@link LatLon} object which contains latitude/longitude
	 *            values
	 */
	public void setLatLon(LatLon latLon) {
		this.latLon = latLon;
	}

	/**
	 * @param key
	 *            the key of the service specific property assigned to this POI
	 * @return the service specific key value assigned to this POI, may be
	 *         <code>null</code>
	 */
	@SuppressWarnings("unchecked")
	public <T> T getProperty(String key) {
		return (T) props.get(key);
	}

	/**
	 * @param key
	 *            the key of the service specific property assigned to this POI
	 * @param value
	 *            the service specific key value to assign to this POI, may be
	 *            <code>null</code>
	 */
	public void setProperty(String key, Object value) {
		props.put(key, value);
	}

	/**
	 * A generic class that contains latitude/longitude values along with its
	 * EPSG projection code.
	 * 
	 * @author Erdal Karaca
	 * 
	 */
	public static class LatLon {
		private double lat;
		private double lon;

		public LatLon(double lat, double lon) {
			super();
			this.lat = lat;
			this.lon = lon;
		}

		public double getLat() {
			return lat;
		}

		public void setLat(double lat) {
			this.lat = lat;
		}

		public double getLon() {
			return lon;
		}

		public void setLon(double lon) {
			this.lon = lon;
		}
	}
}
