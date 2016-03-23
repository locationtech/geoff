package org.locationtech.geoff.ui.swt.internal;

import java.util.HashMap;
import java.util.Map;

import org.locationtech.geoff.Location;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.ui.swt.IGeoMapWidget;
import org.locationtech.geoff.ui.swt.IGeoMapWidget.Property;
import org.locationtech.geoff.ui.swt.IScriptable;

public class PropertyHandlers {
	private static final PropertyHandlers INSTANCE = new PropertyHandlers();

	private Map<Property, PropertyHandler<?>> propertyHandlers = new HashMap<Property, PropertyHandler<?>>();
	{
		propertyHandlers.put(Property.VIEW_ZOOM, new PropertyHandler<Integer>() {

			@Override
			public Integer map(Object[] args) {
				int zoom = ((Number) args[0]).intValue();
				return zoom;
			}

			@Override
			public void setValue(IGeoMapWidget geoMapComposite, Integer value) {
				IScriptable scriptable = (IScriptable) geoMapComposite;
				scriptable.execute("return geoff.ol3Map().getView().setZoom(" + value + ");");
			}

			@Override
			public Class<Integer> getValueType() {
				return Integer.class;
			}

		});

		propertyHandlers.put(Property.VIEW_CENTER, new PropertyHandler<Location>() {

			@Override
			public Location map(Object[] args) {
				Object[] locs = (Object[]) args[0];
				double x = ((Number) locs[0]).doubleValue();
				double y = ((Number) locs[1]).doubleValue();
				String projectionCode = (String) args[1];
				Location xyz = Geoff.xyLocation(x, y, projectionCode);
				return xyz;
			}

			@Override
			public Class<Location> getValueType() {
				return Location.class;
			}

			@Override
			public void setValue(IGeoMapWidget geoMapComposite, Location value) {

			}
		});
	}

	private PropertyHandlers() {
	}

	public static PropertyHandlers getInstance() {
		return INSTANCE;
	}

	private static Object[] execute(IGeoMapWidget geoMapComposite, Property prop) {
		IScriptable scriptable = (IScriptable) geoMapComposite;
		try {
			// call the appropriate event handler and signal that no events
			// should be re-triggered as we are only interested in the value
			String script = String.format("return geoff.eventHandlers['%s'](null,false)", prop.getEventName());
			return scriptable.execute(script);
		} catch (Exception e) {
			// may happen if the scriptable is not yet ready to execute
			return null;
		}
	}

	public PropertyHandler<?> getHandler(Property e) {
		PropertyHandler<?> propertyHandler = propertyHandlers.get(e);

		if (propertyHandler == null) {
			throw new IllegalArgumentException("No property handler found for event: " + e);
		}

		return propertyHandler;
	}

	public static interface PropertyHandler<T> {

		/**
		 * Maps the event value to the model value.
		 * 
		 * @param args
		 *            the event value that has been passed from the JS side
		 * @return the mapped event value
		 */
		T map(Object[] args);

		default T getValue(IGeoMapWidget geoMapComposite, Property prop) {
			// get the value of the property
			Object[] params = execute(geoMapComposite, prop);
			// map the result to the target value
			T mapped = map(params);
			return mapped;
		}

		void setValue(IGeoMapWidget geoMapComposite, T value);

		Class<T> getValueType();
	}
}
