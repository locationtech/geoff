package org.locationtech.geoff.designer.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.locationtech.geoff.Color;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.GeoffPackage;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.designer.DesignerUtil;
import org.locationtech.geoff.designer.IEditingService;
import org.locationtech.geoff.layer.VectorLayer;
import org.locationtech.geoff.source.SourceFormat;
import org.locationtech.geoff.source.VectorSource;
import org.locationtech.geoff.style.Circle;
import org.locationtech.geoff.style.Fill;
import org.locationtech.geoff.style.Stroke;
import org.locationtech.geoff.style.Style;
import org.locationtech.geoff.style.StylePackage;

public class AddResourceAsLayerHandler {
	private Map<String, SourceFormat> formatsMappings = new HashMap<String, SourceFormat>();

	{
		formatsMappings.put("geojson", SourceFormat.GEO_JSON);
		formatsMappings.put("gpx", SourceFormat.GPX);
		formatsMappings.put("kml", SourceFormat.KML);
	}

	@Execute
	public void execute(IEditingService editingService, GeoMap geoMap, ISelection selection) {
		if (selection.isEmpty()) {
			return;
		}

		Object[] array = ((IStructuredSelection) selection).toArray();
		CompoundCommand cc = new CompoundCommand();

		for (Object o : array) {
			if (!(o instanceof IFile)) {
				continue;
			}

			IFile file = (IFile) o;
			String fileExtension = file.getFileExtension();
			SourceFormat sourceFormat = formatsMappings.get(fileExtension);

			if (sourceFormat == null) {
				continue;
			}

			VectorSource source = Geoff.vectorSource();
			source.setFormat(sourceFormat);
			source.setProjection(Geoff.EPSG3857_WEB_MERCATOR);
			String path = DesignerUtil.toRelativeURL(file);
			source.setUrl(path);

			VectorLayer layer = Geoff.vectorLayer(source);
			populateDefaultStyles(editingService, layer);
			Command command = editingService.createAddCommand(geoMap, GeoffPackage.Literals.GEO_MAP__LAYERS, layer);
			cc.append(command);
		}

		if (!cc.isEmpty()) {
			editingService.execute(cc);
		}
	}

	private void populateDefaultStyles(IEditingService es, VectorLayer layer) {
		{
			Style style = createPointStyle(es);
			layer.getStyles().put("Point", style);
		}
		{
			Style style = createPointStyle(es);
			layer.getStyles().put("MultiPoint", style);
		}

		{
			Style style = createPolygonStyle(es);
			layer.getStyles().put("Polygon", style);
		}
		{
			Style style = createPolygonStyle(es);
			layer.getStyles().put("MultiPolygon", style);
		}

		{
			Style style = createLineStringStyle(es);
			layer.getStyles().put("LineString", style);
		}
		{
			Style style = createLineStringStyle(es);
			layer.getStyles().put("MultiLineString", style);
		}
	}

	private Style createLineStringStyle(IEditingService es) {
		Stroke stroke = createStroke(es, 1.0f, 0, 0, 255, 1.0f);
		Fill fill = createFill(es, 0, 0, 255, 0.1f);
		Style style = es.createInstance(StylePackage.Literals.STYLE);
		style.setFill(fill);
		style.setStroke(stroke);
		return style;
	}

	private Style createPolygonStyle(IEditingService es) {
		Stroke stroke = createStroke(es, 1.0f, 0, 0, 255, 1.0f);
		Fill fill = createFill(es, 0, 0, 255, 0.05f);
		Style style = es.createInstance(StylePackage.Literals.STYLE);
		style.setFill(fill);
		style.setStroke(stroke);
		return style;
	}

	private Style createPointStyle(IEditingService es) {
		Fill fill = createFill(es, 0, 0, 255, 0.5f);
		Stroke stroke = createStroke(es, 5.0f, 0, 0, 255, 1.0f);
		Circle circle = es.createInstance(StylePackage.Literals.CIRCLE);
		circle.setRadius(3);
		circle.setStroke(stroke);
		circle.setFill(fill);

		Style style = es.createInstance(StylePackage.Literals.STYLE);
		style.setImage(circle);
		return style;
	}

	private Fill createFill(IEditingService es, int r, int g, int b, float alpha) {
		Fill fill = es.createInstance(StylePackage.Literals.FILL);
		{
			Color color = es.createInstance(GeoffPackage.Literals.COLOR);
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

	private Stroke createStroke(IEditingService es, float width, int r, int g, int b, float alpha) {
		Stroke stroke = es.createInstance(StylePackage.Literals.STROKE);
		{
			stroke.setWidth((double) width);

			Color color = es.createInstance(GeoffPackage.Literals.COLOR);
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
}
