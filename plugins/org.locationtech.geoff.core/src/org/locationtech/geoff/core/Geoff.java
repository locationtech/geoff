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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.locationtech.geoff.Color;
import org.locationtech.geoff.Feature;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.GeoffFactory;
import org.locationtech.geoff.GeoffPackage;
import org.locationtech.geoff.Identifiable;
import org.locationtech.geoff.Location;
import org.locationtech.geoff.RendererHint;
import org.locationtech.geoff.View;
import org.locationtech.geoff.XYZLocation;
import org.locationtech.geoff.geom.GeomFactory;
import org.locationtech.geoff.geom.Geometry;
import org.locationtech.geoff.geom.LineString;
import org.locationtech.geoff.geom.Point;
import org.locationtech.geoff.geom.Polygon;
import org.locationtech.geoff.geom.SimpleGeometry;
import org.locationtech.geoff.impl.GeoffFactoryImpl;
import org.locationtech.geoff.impl.StringToStringMapEntryImpl;
import org.locationtech.geoff.layer.Layer;
import org.locationtech.geoff.layer.LayerFactory;
import org.locationtech.geoff.layer.TileLayer;
import org.locationtech.geoff.layer.VectorLayer;
import org.locationtech.geoff.source.BingMaps;
import org.locationtech.geoff.source.MapQuest;
import org.locationtech.geoff.source.OSM;
import org.locationtech.geoff.source.Source;
import org.locationtech.geoff.source.SourceFactory;
import org.locationtech.geoff.source.VectorSource;
import org.locationtech.geoff.style.Circle;
import org.locationtech.geoff.style.Fill;
import org.locationtech.geoff.style.Icon;
import org.locationtech.geoff.style.Image;
import org.locationtech.geoff.style.Stroke;
import org.locationtech.geoff.style.Style;
import org.locationtech.geoff.style.StyleFactory;
import org.locationtech.geoff.style.StylePackage;
import org.locationtech.geoff.util.GeoffResourceFactoryImpl;

/**
 * A fluent API hiding EMF specifics to simplify GeoMap configuration.<br>
 * <br>
 * Example to construct an OpenStreetMap base layer centered at a specific
 * location:<br>
 * 
 * <pre>
 * Geoff.createMap(&quot;OpenStreetMap&quot;, &quot;A simple OSM base layer example.&quot;)
 * 		.view(view(trans(&quot;EPSG:4326&quot;, &quot;EPSG:3857&quot;, 8.2128d, 53.1403), 10)).addLayer(tileLayer(osmSource()));
 * </pre>
 * 
 * @author Erdal Karaca
 * 
 */
public class Geoff {
	private static final GeoffFactoryImpl GEOFF_IMPL = (GeoffFactoryImpl) GeoffFactory.eINSTANCE;
	private GeoMap map;
	public static final String EPSG3857_WEB_MERCATOR = "EPSG:3857";
	public static final String EPSG4326_WGS84 = "EPSG:4326";

	private Geoff() {
	}

	public Geoff addLayer(Layer first, Layer... optional) {
		map.getLayers().add(first);

		if (optional != null) {
			map.getLayers().addAll(Arrays.asList(optional));
		}

		return this;
	}

	public Geoff view(Location center, int zoom) {
		View view = GeoffFactory.eINSTANCE.createView();
		view.setCenter(center);
		view.setZoom(zoom);
		map.setView(view);
		return this;
	}

	/**
	 * Serializes the wrapped {@link GeoMap} instance using the model specific
	 * resource factory {@link GeoffResourceFactoryImpl}.
	 * 
	 * @return the XML representation of the wrapped {@link GeoMap} instance or
	 *         <code>null</code> if the {@link GeoMap} instance could not be
	 *         serialized
	 */
	public String toXML() {
		GeoMap mapCopy = (GeoMap) EcoreUtil.copy(toEObject(map));
		Resource resource = new GeoffResourceFactoryImpl().createResource(null);
		resource.getContents().add((EObject) mapCopy);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			resource.save(out, null);
		} catch (IOException e) {
			return null;
		}

		return out.toString();
	}

	/**
	 * Loads the provided URI and grabs the first found {@link GeoMap} instance.
	 * Note that the {@link GeoMap} will be attached to a new resource using the
	 * provided {@link URI}.
	 * 
	 * @param uri
	 *            the URI to load the {@link GeoMap} instance from
	 * @return the first {@link GeoMap} instance found at the specified
	 *         {@link URI}, or <code>null</code> if location does not contain a
	 *         valid {@link GeoMap} instance
	 */
	public static GeoMap fromURI(URI uri) {
		org.eclipse.emf.common.util.URI emfURI = org.eclipse.emf.common.util.URI.createURI(uri.toString());
		Resource resource = new GeoffResourceFactoryImpl().createResource(emfURI);
		try {
			resource.load(null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (resource.getContents().isEmpty()) {
			return null;
		}

		if (resource.getContents().get(0) instanceof GeoMap) {
			GeoMap map = (GeoMap) resource.getContents().get(0);
			return map;
		}

		return null;
	}

	public GeoMap get() {
		return map;
	}

	public static Geoff createMap(String name, String description) {
		return createMap(name, description, null);
	}

	public static Geoff createMap(String name, String description, RendererHint rendererHint) {
		Geoff geoff = new Geoff();
		geoff.map = GeoffFactory.eINSTANCE.createGeoMap();
		geoff.map.setShortDescription(name);
		geoff.map.setLongDescription(description);

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

	public static OSM osmSource() {
		return SourceFactory.eINSTANCE.createOSM();
	}

	public static VectorSource vectorSource(Feature... optional) {
		VectorSource vector = SourceFactory.eINSTANCE.createVectorSource();

		if (optional != null) {
			vector.getFeatures().addAll(Arrays.asList(optional));
		}

		return vector;
	}

	public static Feature feature(Geometry geometry, Style style) {
		Feature feature = GeoffFactory.eINSTANCE.createFeature();
		feature.setGeometry(geometry);

		if (style != null) {
			feature.setStyle(style);
		}

		return feature;
	}

	public static Point pointGeom(Location location) {
		Point point = GeomFactory.eINSTANCE.createPoint();
		point.setCoordinates(location);
		return point;
	}

	public static LineString lineStringGeom(Location... coords) {
		LineString lineString = GeomFactory.eINSTANCE.createLineString();
		lineString.getCoordinates().addAll(Arrays.asList(coords));
		return lineString;
	}

	public static Polygon polygonGeom(Location... coords) {
		Polygon polygon = GeomFactory.eINSTANCE.createPolygon();
		polygon.getCoordinates().addAll(Arrays.asList(coords));
		return polygon;
	}

	public static BingMaps bingSource(String key, String imagerySet) {
		BingMaps bingMaps = SourceFactory.eINSTANCE.createBingMaps();
		bingMaps.setKey(key);
		bingMaps.setImagerySet(imagerySet);
		return bingMaps;
	}

	public static MapQuest mapQuestSource() {
		return SourceFactory.eINSTANCE.createMapQuest();
	}

	public static TileLayer tileLayer(Source source) {
		TileLayer tile = LayerFactory.eINSTANCE.createTileLayer();
		tile.setSource(source);
		return tile;
	}

	public static VectorLayer vectorLayer(Source source) {
		VectorLayer layer = LayerFactory.eINSTANCE.createVectorLayer();
		layer.setSource(source);
		return layer;
	}

	public static XYZLocation xyLocation(double x, double y) {
		return xyLocation(x, y, null);
	}

	public static XYZLocation xyLocation(double x, double y, String projectionCode) {
		XYZLocation xyzLocation = GeoffFactory.eINSTANCE.createXYZLocation();
		xyzLocation.setX(x);
		xyzLocation.setY(y);

		if (projectionCode != null) {
			xyzLocation.setProjectionCode(projectionCode);
		}

		return xyzLocation;
	}

	public static Style style(Image imageStyle) {
		Style style = StyleFactory.eINSTANCE.createStyle();
		style.setImage(imageStyle);
		return style;
	}

	public static Icon icon(String src) {
		Icon icon = StyleFactory.eINSTANCE.createIcon();
		icon.setSrc(src);
		return icon;
	}

	public static Geoff createMap() {
		return createMap("", "");
	}

	public static Map.Entry<String, String> featureProperty(String key, String value) {
		StringToStringMapEntryImpl entry = (StringToStringMapEntryImpl) GEOFF_IMPL.createStringToStringMapEntry();
		entry.setKey(key);
		entry.setValue(value);
		return entry;
	}

	@SuppressWarnings("unchecked")
	public static <T> T instance(EClass eClass) {
		return (T) EcoreUtil.create(eClass);
	}

	/**
	 * Maps an {@link Identifiable} to an {@link EObject}. This is necessary to
	 * prevent users from manually casting to {@link EObject} as the interfaces
	 * do not outline EMF internals.
	 * 
	 * @param id
	 *            the Geoff domain object
	 * @return the {@link EObject} instance of the provided {@link Identifiable}
	 */
	public static EObject toEObject(Identifiable id) {
		return (EObject) id;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Identifiable> T toId(EObject eo) {
		return (T) eo;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Identifiable> Collection<T> samplesOf(EClass eclass) {
		EPackage root = eclass.getEPackage();
		EPackage current = root;

		while ((current = root.getESuperPackage()) != null) {
			root = current;
		}

		Collection<Identifiable> ret = new ArrayList<Identifiable>();
		Collection<EClass> eclasses = new ArrayList<EClass>();
		collectSubtypes(eclasses, eclass, root);

		for (EClass ec : eclasses) {
			if (!GeoffPackage.Literals.IDENTIFIABLE.isSuperTypeOf(ec)) {
				continue;
			}

			Identifiable eo = (Identifiable) EcoreUtil.create(ec);
			ret.add(eo);
		}

		return (Collection<T>) ret;
	}

	private static void collectSubtypes(Collection<EClass> eclasses, EClass eclass, EPackage root) {
		for (EClassifier eClassifier : root.getEClassifiers()) {
			if (!(eClassifier instanceof EClass)) {
				continue;
			}

			EClass subType = (EClass) eClassifier;

			if (subType.isAbstract()) {
				continue;
			}

			if (eclass.isSuperTypeOf(subType)) {
				eclasses.add(subType);
			}
		}

		for (EPackage subPackage : root.getESubpackages()) {
			collectSubtypes(eclasses, eclass, subPackage);
		}
	}

	public static void populateDefaultStyles(VectorLayer layer) {
		{
			Style style = createPointStyle();
			layer.getStyles().put("Point", style);
		}
		{
			Style style = createPointStyle();
			layer.getStyles().put("MultiPoint", style);
		}

		{
			Style style = createPolygonStyle();
			layer.getStyles().put("Polygon", style);
		}
		{
			Style style = createPolygonStyle();
			layer.getStyles().put("MultiPolygon", style);
		}

		{
			Style style = createLineStringStyle();
			layer.getStyles().put("LineString", style);
		}
		{
			Style style = createLineStringStyle();
			layer.getStyles().put("MultiLineString", style);
		}
	}

	public static Style createLineStringStyle() {
		Stroke stroke = createStroke(1.0f, 0, 0, 255, 1.0f);
		Fill fill = createFill(0, 0, 255, 0.1f);
		Style style = Geoff.instance(StylePackage.Literals.STYLE);
		style.setFill(fill);
		style.setStroke(stroke);
		return style;
	}

	public static Style createPolygonStyle() {
		Stroke stroke = createStroke(1.0f, 0, 0, 255, 1.0f);
		Fill fill = createFill(0, 0, 255, 0.05f);
		Style style = Geoff.instance(StylePackage.Literals.STYLE);
		style.setFill(fill);
		style.setStroke(stroke);
		return style;
	}

	public static Style createPointStyle() {
		Fill fill = createFill(0, 0, 255, 0.5f);
		Stroke stroke = createStroke(5.0f, 0, 0, 255, 1.0f);
		Circle circle = Geoff.instance(StylePackage.Literals.CIRCLE);
		circle.setRadius(3);
		circle.setStroke(stroke);
		circle.setFill(fill);

		Style style = Geoff.instance(StylePackage.Literals.STYLE);
		style.setImage(circle);
		return style;
	}

	private static Fill createFill(int r, int g, int b, float alpha) {
		Fill fill = Geoff.instance(StylePackage.Literals.FILL);
		{
			Color color = Geoff.instance(GeoffPackage.Literals.COLOR);
			{
				color.setRed(r);
				color.setGreen(g);
				color.setBlue(b);
				color.setAlpha(alpha);
			}

			fill.setColor(color);
		}

		return fill;
	}

	private static Stroke createStroke(float width, int r, int g, int b, float alpha) {
		Stroke stroke = Geoff.instance(StylePackage.Literals.STROKE);
		{
			stroke.setWidth((double) width);

			Color color = Geoff.instance(GeoffPackage.Literals.COLOR);
			{
				color.setRed(r);
				color.setGreen(g);
				color.setBlue(b);
				color.setAlpha(alpha);
			}

			stroke.setColor(color);
		}

		return stroke;
	}

	public static SimpleGeometry fromWKT(String wkt) {
		if (wkt == null || "".equals(wkt.trim().toString())) {
			return null;
		}

		// for now, assume simple syntax
		Pattern pattern = Pattern.compile("^(\\w+)\\(+([0-9 \\.,+-]+)\\)+$");
		Matcher matcher = pattern.matcher(wkt);

		if (!matcher.matches()) {
			return null;
		}

		String geomType = matcher.group(1);
		String coordsStr = matcher.group(2);
		List<Location> coords = parseLocations(coordsStr);

		SimpleGeometry ret = null;

		if ("POINT".equals(geomType)) {
			ret = pointGeom(coords.get(0));
		} else if ("LINESTRING".equals(geomType)) {
			ret = lineStringGeom(coords.toArray(new Location[coords.size()]));
		} else if ("POLYGON".equals(geomType)) {
			ret = polygonGeom(coords.toArray(new Location[coords.size()]));
		}

		return ret;
	}

	private static List<Location> parseLocations(String coordsStr) {
		List<Location> ret = new ArrayList<>();
		String[] coordPairs = coordsStr.split(",");

		for (String coordPair : coordPairs) {
			String[] coords = coordPair.split("\\s+");
			double x = Double.parseDouble(coords[0]);
			double y = Double.parseDouble(coords[1]);
			XYZLocation xyLocation = xyLocation(x, y);
			ret.add(xyLocation);
		}

		return ret;
	}
}
