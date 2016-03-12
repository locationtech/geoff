package org.locationtech.geoff.designer.internal;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.locationtech.geoff.Color;
import org.locationtech.geoff.GeoffPackage;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.layer.VectorLayer;
import org.locationtech.geoff.style.Circle;
import org.locationtech.geoff.style.Fill;
import org.locationtech.geoff.style.Stroke;
import org.locationtech.geoff.style.Style;
import org.locationtech.geoff.style.StylePackage;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class DesignerUtil {
	public static final String ICONS_GEOFF_48_PNG = "icons/geoff-48.png";
	public static final String ICONS_GEOFF_16_PNG = "icons/geoff-16.png";
	public static final String ICONS_GEOFF_WIZBAN_PNG = "icons/geoff-wizban.png";

	public static ImageDescriptor getImageDescriptor(String imageFilePath) {
		Bundle bundle = FrameworkUtil.getBundle(DesignerUtil.class);
		String symbolicName = bundle.getSymbolicName();
		ImageDescriptor imageDescriptorFromPlugin = DesignerActivator.imageDescriptorFromPlugin(symbolicName,
				imageFilePath);
		return imageDescriptorFromPlugin;
	}

	public static ComposedAdapterFactory createComposedAdapterFactor() {
		return new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
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
}
