package org.locationtech.geoff.ui.swt.internal;

import java.util.HashMap;
import java.util.Map;

import org.locationtech.geoff.Location;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.ui.swt.IGeoMapWidget;
import org.locationtech.geoff.ui.swt.IGeoMapWidget.Property;

public class PropertyHandlers {
	private static final PropertyHandlers INSTANCE = new PropertyHandlers();

	private Map<Property, PropertyHandler<?>> propertyHandlers = new HashMap<Property, PropertyHandler<?>>();
	{
		propertyHandlers.put(Property.VIEW_ZOOM, new PropertyHandler<Integer>() {

			@Override
			public Object map(Object[] args) {
				int zoom = ((Number) args[0]).intValue();
				return zoom;
			}

			@Override
			public Object getValue(IGeoMapWidget geoMapComposite) {
				return 100;
			}

			@Override
			public Class<Integer> getValueType() {
				return Integer.class;
			}
		});

		propertyHandlers.put(Property.VIEW_CENTER, new PropertyHandler<Location>() {

			@Override
			public Object map(Object[] args) {
				Object[] locs = (Object[]) args[0];
				double x = ((Number) locs[0]).doubleValue();
				double y = ((Number) locs[1]).doubleValue();
				String projectionCode = (String) args[1];
				Location xyz = Geoff.xyLocation(x, y, projectionCode);
				return xyz;
			}

			@Override
			public Object getValue(IGeoMapWidget geoMapComposite) {
				return null;
			}

			@Override
			public Class<Location> getValueType() {
				return Location.class;
			}
		});
	}

	private PropertyHandlers() {
	}

	public static PropertyHandlers getInstance() {
		return INSTANCE;
	}

	public PropertyHandler<?> getHandler(Property e) {
		PropertyHandler<?> propertyHandler = propertyHandlers.get(e);

		if (propertyHandler == null) {
			throw new IllegalArgumentException("No property handler found by event: " + e);
		}

		return propertyHandler;
	}

	public static interface PropertyHandler<T> {

		Object map(Object[] args);

		Object getValue(IGeoMapWidget geoMapComposite);

		Class<T> getValueType();
	}
}
