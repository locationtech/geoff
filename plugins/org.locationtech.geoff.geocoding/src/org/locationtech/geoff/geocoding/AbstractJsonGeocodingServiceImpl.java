/*******************************************************************************
 * Copyright (c) 2014 Erdal Karaca.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Erdal Karaca - initial API and implementation
 *******************************************************************************/
package org.locationtech.geoff.geocoding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.locationtech.geoff.geocoding.POI.LatLon;

/**
 * An abstract {@link IGeocodingService} implementation that is intended to be
 * used for all JSON based geocoding services.
 * 
 * @author Erdal Karaca
 * 
 */
public abstract class AbstractJsonGeocodingServiceImpl implements
		IGeocodingService {
	@Override
	public List<POI> executeQuery(String query) {
		List<POI> ret = new ArrayList<POI>();

		try {
			String encodedQuery = URLEncoder.encode(new String(
					query.getBytes(), doGetQueryEncoding()), getURLEncoding());
			URL url = new URL(doGetQueryUrl(encodedQuery));
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			JSONTokener t = new JSONTokener(in);
			Object nextValue = t.nextValue();

			JSONArray arr = doGetResultsArrayFromRoot(nextValue);

			if (arr != null) {
				for (int i = 0; i < arr.length(); i++) {
					JSONObject object = (JSONObject) arr.get(i);
					POI poi = new POI();
					String poiDescription = doGetPOIDescription(object);
					poi.setDescription(poiDescription);
					LatLon latLon = new LatLon(getPOILon(object),
							getPOILat(object));
					poi.setLatLon(latLon);

					Map<String, Object> props = new HashMap<String, Object>();
					doPopulateProperties(object, props);

					for (Entry<String, Object> e : props.entrySet()) {
						poi.setProperty(e.getKey(), e.getValue());
					}

					ret.add(poi);
				}
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	/**
	 * @return the encoding to use to construct the query to send to the target
	 *         service, default is UTF-8
	 */
	protected String doGetQueryEncoding() {
		return "UTF-8";
	}

	/**
	 * @return the encoding to use to construct the URL to send to the target
	 *         service, default is ISO-8859-1
	 */
	protected String getURLEncoding() {
		return "ISO-8859-1";
	}

	/**
	 * A callback for the implementing class to signal populating service
	 * specific properties. The properties will be available to the service
	 * consumer. The default does nothing.
	 * 
	 * @param object
	 *            the {@link JSONObject} that was returned by the target JSON
	 *            service
	 * @param props
	 *            the properties map to populate
	 */
	protected void doPopulateProperties(JSONObject object,
			Map<String, Object> props) {
	}

	/**
	 * @param object
	 *            the {@link JSONObject} to extract the latitude value from
	 * @return the latitude value extracted from the {@link JSONObject} returned
	 *         by the target service
	 * @throws JSONException
	 *             if any errors occurred while accessing the {@link JSONObject}
	 */
	protected abstract double getPOILat(JSONObject object) throws JSONException;

	/**
	 * @param object
	 *            the {@link JSONObject} to extract the longitude value from
	 * @return the longitude value extracted from the {@link JSONObject}
	 *         returned by the target service
	 * @throws JSONException
	 *             if any errors occurred while accessing the {@link JSONObject}
	 */
	protected abstract double getPOILon(JSONObject object) throws JSONException;

	/**
	 * @param object
	 *            the {@link JSONObject} to extract the description of the POI
	 *            to construct from
	 * @return the description extracted from the {@link JSONObject} returned by
	 *         the target service
	 * @throws JSONException
	 *             if any errors occurred while accessing the {@link JSONObject}
	 */
	protected abstract String doGetPOIDescription(JSONObject object)
			throws JSONException;

	/**
	 * The base implementation assumes that the target JSON service will return
	 * a {@link JSONArray} as the root result. Implementors are expected to
	 * extract that {@link JSONArray} from the provided object.
	 * 
	 * @param rootObject
	 *            the root object returned by the target service, the
	 *            implementor must be able to handle this object
	 * @return the root {@link JSONArray} extracted from the provided root
	 *         object provided by the target service
	 * @throws JSONException
	 *             if any errors occurred while accessing the root object
	 */
	protected abstract JSONArray doGetResultsArrayFromRoot(Object rootObject)
			throws JSONException;

	/**
	 * Callback method that signals implementors to construct a query URL from
	 * the encoded query string.
	 * 
	 * @param encodedQuery
	 *            the encoded query provided by the service consumer
	 * @return a string representation of the URL that can be used to send to
	 *         the target service
	 */
	protected abstract String doGetQueryUrl(String encodedQuery);
}
