package org.locationtech.geoff.ui.swt;

import java.util.function.Consumer;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.ui.swt.IGeoMapWidget.Property;

public interface IGeoMapWidget {
	enum EditingMode {
		NONE, POINT, LINE, POLYGON, CIRCLE, RECTANGLE
	}
	
	enum SelectionMode {
		POINT, RECTANGLE
	}

	class PropertyEvent {
		public final Property type;
		public final Object value;

		public PropertyEvent(Property type, Object value) {
			super();
			this.type = type;
			this.value = value;
		}
	}

	interface Listener extends Consumer<PropertyEvent> {
	}

	enum Property {
		EDITING_MODE("editingMode"), //
		SELECTION_MODE("selectionMode"), //
		VIEW_ZOOM("viewZoom"), //
		VIEW_CENTER("viewCenter");

		private String eventName;

		private Property(String eventName) {
			this.eventName = eventName;
		}

		public String getEventName() {
			return eventName;
		}

		public static Property byName(String evtName) {
			for (Property e : values()) {
				if (e.eventName.equals(evtName)) {
					return e;
				}
			}

			throw new IllegalArgumentException("No property found by name: " + evtName);
		}
	}

	/**
	 * Loads the provided map and install a change listener that will
	 * automatically handle model changes to update the UI state of the map.
	 * 
	 * @param map
	 *            the map to manage
	 */
	void loadMap(GeoMap map);

	void addListener(Property type, Listener listener);

	void removeListener(Property type, Listener listener);

	/**
	 * Creates a new {@link IObservableValue} to listen to UI events. Note that
	 * the value of this {@link IObservableValue} may not have been set
	 * initially, i.e. callers must be aware of its initial value to be
	 * <code>null</code>.
	 * 
	 * @param type
	 *            the property to observe
	 * @return a new {@link IObservableValue} that observes changes from its
	 *         source, the caller must dispose the observable once it is not in
	 *         use anymore
	 */
	IObservableValue observeValue(Property type);
}