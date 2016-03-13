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
package org.locationtech.geoff.geocoding.google;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.locationtech.geoff.geocoding.AbstractJsonGeocodingServiceImpl;
import org.locationtech.geoff.geocoding.IGeocodingService;
import org.osgi.service.component.annotations.Component;

@Component(service = IGeocodingService.class)
public class GoogleGeocodingAPIService extends AbstractJsonGeocodingServiceImpl {
	@Override
	protected double getPOILat(JSONObject object) throws JSONException {
		return object.getJSONObject("geometry").getJSONObject("location")
				.getDouble("lng");
	}

	@Override
	protected double getPOILon(JSONObject object) throws JSONException {
		return object.getJSONObject("geometry").getJSONObject("location")
				.getDouble("lat");
	}

	@Override
	protected String doGetPOIDescription(JSONObject object)
			throws JSONException {
		return object.getString("formatted_address");
	}

	@Override
	protected JSONArray doGetResultsArrayFromRoot(Object rootObject)
			throws JSONException {
		return ((JSONObject) rootObject).getJSONArray("results");
	}

	@Override
	protected String doGetQueryUrl(String encodedQuery) {
		return String
				.format("http://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=%s",
						encodedQuery);
	}
}
