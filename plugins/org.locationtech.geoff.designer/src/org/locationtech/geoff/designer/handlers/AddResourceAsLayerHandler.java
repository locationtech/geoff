package org.locationtech.geoff.designer.handlers;

import java.net.MalformedURLException;
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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.locationtech.geoff.Color;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.GeoffPackage;
import org.locationtech.geoff.designer.IEditingService;
import org.locationtech.geoff.layer.LayerPackage;
import org.locationtech.geoff.layer.Vector;
import org.locationtech.geoff.source.SourcePackage;
import org.locationtech.geoff.source.StaticVector;
import org.locationtech.geoff.style.Circle;
import org.locationtech.geoff.style.Fill;
import org.locationtech.geoff.style.Stroke;
import org.locationtech.geoff.style.Style;
import org.locationtech.geoff.style.StylePackage;

public class AddResourceAsLayerHandler {
	private Map<String, EClass> sourceMappings = new HashMap<String, EClass>();
	{
		sourceMappings.put("geojson", SourcePackage.Literals.GEO_JSON);
		sourceMappings.put("gpx", SourcePackage.Literals.GPX);
		sourceMappings.put("kml", SourcePackage.Literals.KML);
	}

	@Execute
	public void execute(IEditingService editingService, GeoMap geoMap,
			ISelection selection) {
		if (selection.isEmpty()) {
			return;
		}

		Object[] array = ((IStructuredSelection) selection).toArray();
		CompoundCommand cc = new CompoundCommand();

		for (Object o : array) {
			if (o instanceof IFile) {
				IFile file = (IFile) o;
				String fileExtension = file.getFileExtension();
				EClass sourceFormat = sourceMappings.get(fileExtension);

				if (sourceFormat != null) {
					StaticVector source = editingService
							.createInstance(sourceFormat);
					source.setProjection("EPSG:3857");
					String path = getPathUpToProject(file);
					source.setUrl(path);

					Vector layer = editingService
							.createInstance(LayerPackage.Literals.VECTOR);
					layer.setSource(source);
					populateDefaultStyles(editingService, layer);
					Command command = editingService.createAddCommand(geoMap,
							GeoffPackage.Literals.GEO_MAP__LAYERS, layer);
					cc.append(command);
				}
			}
		}

		if (!cc.isEmpty()) {
			editingService.execute(cc);
		}
	}

	private String getPathUpToProject(IFile file) {
		List<String> nodes = new ArrayList<String>();

		for (IResource node = file; node != null; node = node.getParent()) {
			if (node instanceof IProject) {
				break;
			}

			nodes.add(node.getName());
		}

		StringBuilder sb = new StringBuilder();

		for (int i = nodes.size() - 1; i >= 0; i--) {
			sb.append(nodes.get(i));

			if (i > 0) {
				sb.append("/");
			}
		}

		return sb.toString();
	}

	private void populateDefaultStyles(IEditingService es, Vector layer) {
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

	private Stroke createStroke(IEditingService es, float width, int r, int g,
			int b, float alpha) {
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
