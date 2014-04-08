/*******************************************************************************
 *  Copyright (c) 2014 Erdal Karaca.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  
 *  Contributors:
 *      Erdal Karaca - initial API and implementation
 * 
 */
package org.locationtech.geoff.core;

import static org.locationtech.geoff.core.Geoff.osmSource;
import static org.locationtech.geoff.core.Geoff.tileLayer;
import static org.locationtech.geoff.core.Geoff.trans;
import static org.locationtech.geoff.core.Geoff.view2d;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.GeoffFactory;
import org.locationtech.geoff.Location;
import org.locationtech.geoff.RendererHint;
import org.locationtech.geoff.Transformation;
import org.locationtech.geoff.View;
import org.locationtech.geoff.View2D;
import org.locationtech.geoff.XYZLocation;
import org.locationtech.geoff.layer.LayerFactory;
import org.locationtech.geoff.layer.Tile;
import org.locationtech.geoff.source.BingMaps;
import org.locationtech.geoff.source.MapQuestOpenAerial;
import org.locationtech.geoff.source.OSM;
import org.locationtech.geoff.source.Source;
import org.locationtech.geoff.source.SourceFactory;
import org.locationtech.geoff.util.GeoffResourceFactoryImpl;

/**
 * A fluent API hiding EMF specifics to simplify GeoMap configuration.<br>
 * <br>
 * Example to construct an OpenStreetMap base layer centered at a specific
 * location:<br>
 * 
 * <pre>
 * Geoff.createMap(&quot;OpenStreetMap&quot;, &quot;A simple OSM base layer example.&quot;)
 * 		.view(view2d(trans(&quot;EPSG:4326&quot;, &quot;EPSG:3857&quot;, 8.2128d, 53.1403), 10))
 * 		.addLayer(tileLayer(osmSource()));
 * </pre>
 * 
 * @author Erdal Karaca
 * 
 */
public class Geoff {
	private GeoMap map;

	private Geoff() {
	}

	public Geoff addLayer(Tile first, Tile... optional) {
		map.getLayers().add(first);

		if (optional != null) {
			map.getLayers().addAll(Arrays.asList(optional));
		}

		return this;
	}

	public Geoff view(View view) {
		map.setView(view);
		return this;
	}

	public String toXML() {
		GeoMap mapCopy = EcoreUtil.copy(map);
		Resource resource = new GeoffResourceFactoryImpl().createResource(null);
		resource.getContents().add(mapCopy);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			resource.save(out, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	public GeoMap get() {
		return map;
	}

	public static Geoff createMap(String name, String description) {
		return createMap(name, description, null);
	}

	public static Geoff createMap(String name, String description,
			RendererHint rendererHint) {
		Geoff geoff = new Geoff();
		geoff.map = GeoffFactory.eINSTANCE.createGeoMap();
		geoff.map.setName(name);
		geoff.map.setDescription(description);

		if (rendererHint != null) {
			geoff.map.setRendererHint(rendererHint);
		}

		return geoff;
	}

	public static Geoff wrap(GeoMap map) {
		Geoff geoff = new Geoff();
		geoff.map = map;
		return geoff;
	}

	public static View2D view2d(Location center, int zoom) {
		View2D view = GeoffFactory.eINSTANCE.createView2D();
		view.setCenter(center);
		view.setZoom(zoom);
		return view;
	}

	public static OSM osmSource() {
		return SourceFactory.eINSTANCE.createOSM();
	}

	public static BingMaps bingSource(String key, String imagerySet) {
		BingMaps bingMaps = SourceFactory.eINSTANCE.createBingMaps();
		bingMaps.setKey(key);
		bingMaps.setImagerySet(imagerySet);
		return bingMaps;
	}

	public static MapQuestOpenAerial mapquestSource() {
		return SourceFactory.eINSTANCE.createMapQuestOpenAerial();
	}

	public static Tile tileLayer(Source source) {
		Tile tile = LayerFactory.eINSTANCE.createTile();
		tile.setSource(source);
		return tile;
	}

	public static XYZLocation xyLocation(double x, double y) {
		XYZLocation xyzLocation = GeoffFactory.eINSTANCE.createXYZLocation();
		xyzLocation.setX(x);
		xyzLocation.setY(y);
		return xyzLocation;
	}

	public static Transformation trans(String sourceProjection,
			String targetProjection, double... coords) {
		Transformation transformation = GeoffFactory.eINSTANCE
				.createTransformation();
		transformation.setSourceProjection(sourceProjection);
		transformation.setTargetProjection(targetProjection);

		if (coords != null) {
			if (coords.length > 0) {
				transformation.setX(coords[0]);
			}

			if (coords.length > 1) {
				transformation.setY(coords[1]);
			}

			if (coords.length > 2) {
				transformation.setZ(coords[2]);
			}
		}

		return transformation;
	}
}
