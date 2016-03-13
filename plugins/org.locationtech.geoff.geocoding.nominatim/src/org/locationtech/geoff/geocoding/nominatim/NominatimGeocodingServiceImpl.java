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
package org.locationtech.geoff.geocoding.nominatim;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.locationtech.geoff.geocoding.AbstractJsonGeocodingServiceImpl;
import org.locationtech.geoff.geocoding.IGeocodingService;
import org.osgi.service.component.annotations.Component;

@Component(service = IGeocodingService.class)
public class NominatimGeocodingServiceImpl extends
		AbstractJsonGeocodingServiceImpl {
	protected void doPopulateProperties(JSONObject object,
			Map<String, Object> props) {
		for (String name : JSONObject.getNames(object)) {
			try {
				Object value = object.get(name);
				props.put(name, value);
			} catch (JSONException e) {
				;
			}
		}
	}

	protected double getPOILat(JSONObject object) throws JSONException {
		return Double.parseDouble(object.getString("lon"));
	}

	protected double getPOILon(JSONObject object) throws JSONException {
		return Double.parseDouble(object.getString("lat"));
	}

	protected String doGetPOIDescription(JSONObject object)
			throws JSONException {
		return object.getString("display_name");
	}

	protected JSONArray doGetResultsArrayFromRoot(Object rootObject) {
		return (JSONArray) rootObject;
	}

	protected String doGetQueryUrl(String encodedQuery) {
		return String.format(
				"http://nominatim.openstreetmap.org/search?format=json&q=%s",
				encodedQuery);
	}
}
