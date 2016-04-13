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
package org.locationtech.geoff.ui.swt;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.ui.swt.internal.IScriptable;
import org.locationtech.geoff.ui.swt.internal.PropertyHandlers;
import org.locationtech.geoff.ui.swt.internal.PropertyHandlers.PropertyHandler;

public class GeoMapComposite extends Composite implements IGeoMapWidget, IScriptable, IPrintable {
	private static final String HTML_MAP_DIV_CONTAINER = "map";
	private static final boolean ENABLE_BROWSER_POPUP_MENU = false;
	private Browser browser;
	private boolean complete = false;
	private boolean enableLocationChange = true;

	private List<BrowserFunction> browserFuncs = new ArrayList<>();
	private Queue<Runnable> pendingTasks = new LinkedList<Runnable>();
	private Map<Property, Set<Consumer<PropertyEvent>>> eventConsumers = new HashMap<GeoMapComposite.Property, Set<Consumer<PropertyEvent>>>();
	private GeoMap currentMap;
	private Map<String, Consumer<?>> pendingResults = new HashMap<>();

	private IObservableValue centerObservable = observeValue(Property.VIEW_CENTER);
	private IObservableValue zoomObservable = observeValue(Property.VIEW_ZOOM);

	public GeoMapComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		browser = new Browser(this, SWT.None);

		setupMenus();

		browser.addProgressListener(new ProgressAdapter() {

			@Override
			public void completed(ProgressEvent event) {
				unregisterFunctions();
				registerFunctions();
				// after the content is setup, no change in location is allowed
				// else, the browser widget might open external pages within
				// itself
				// enableLocationChange = false;

				while (getDisplay().readAndDispatch())
					continue;
				complete = true;
				dispatchScripts();
				fireAllEvents();
			}
		});

		browser.addLocationListener(new LocationAdapter() {

			@Override
			public void changing(LocationEvent event) {
				event.doit = enableLocationChange;
			}
		});

		doSetupOpenLayers();
	}

	private void setupMenus() {
		Menu menu = new Menu(browser);
		browser.setMenu(menu);
	}

	@Override
	public void dispose() {
		unregisterFunctions();
		currentMap = null;
		pendingTasks.clear();
		centerObservable.dispose();
		zoomObservable.dispose();
		super.dispose();
	}

	protected void unregisterFunctions() {
		browserFuncs.forEach((bf) -> bf.dispose());
		browserFuncs.clear();
	}

	protected void registerFunctions() {
		unregisterFunctions();

		BrowserFunction bridgeFunc = new BrowserFunction(browser, "geoffSWTBridge") {
			private final String EMPTY_MAP = Geoff.createMap().toXML();

			@Override
			public Object function(Object[] arguments) {
				if (arguments == null || arguments.length == 0) {
					return null;
				}

				String command = (String) arguments[0];

				if ("loadMap".equals(command)) {
					String xml;

					if (currentMap == null) {
						xml = EMPTY_MAP;
					} else {
						xml = Geoff.wrap(currentMap).toXML();
					}

					// System.out.println(xml);

					return xml;
				}

				if ("event".equals(command)) {
					String evtName = (String) arguments[1];
					Object[] params = (Object[]) arguments[2];
					processEvent(evtName, params);
					return null;
				}

				throw new UnsupportedOperationException("Invalid bridge parameters: " + arguments);
			}
		};
		browserFuncs.add(bridgeFunc);

		BrowserFunction alertFunc = new BrowserFunction(browser, "alert") {
			@Override
			public Object function(Object[] arguments) {
				for (Object object : arguments) {
					System.err.print(object);
				}
				System.err.println();
				return null;
			}
		};
		browserFuncs.add(alertFunc);
	}

	private void processEvent(String evtName, Object[] params) {
		if ("mapPrinted".equals(evtName)) {
			String token = (String) params[0];
			String data = (String) params[1];
			byte[] base64Part = data.substring(data.indexOf(',') + 1).getBytes();
			byte[] decode = Base64.getDecoder().decode(base64Part);
			@SuppressWarnings("unchecked")
			Consumer<InputStream> consumer = (Consumer<InputStream>) pendingResults.remove(token);
			consumer.accept(new ByteArrayInputStream(decode));
			return;
		}

		Property e = Property.byName(evtName);
		PropertyHandler<?> propertyHandler = PropertyHandlers.getInstance().getHandler(e);
		Object result = propertyHandler.map(params);
		fireEvent(e, result);
	}

	protected void doSetupOpenLayers() {
		String resourcesPath = doGetResourcesPath();
		String base = doGetServerBase();
		String url = String.format("%s/%s", base, resourcesPath);
		browser.setUrl(url);

		// register client to server callback mechanism
		// this is used to listen to events
		executeJavaSript("geoff.eventTriggered=function(evtName,evtParams){geoffSWTBridge('event',evtName,evtParams)}");
	}

	protected String doGetResourcesPath() {
		return String.format("%s/%s", "org.locationtech.geoff.ol", "resources/index-fullmap.html");
	}

	protected String doGetServerBase() {
		String httpPort = System.getProperty("org.osgi.service.http.port", "8080");
		String httpHost = System.getProperty("org.osgi.service.http.host", "localhost");
		String url = String.format("http://%s:%s", httpHost, httpPort);
		return url;
	}

	public Browser getBrowser() {
		return browser;
	}

	private void executeJavaSript(final String jsCode) {

		Runnable run = new Runnable() {
			public void run() {
				browser.evaluate(jsCode);
			}
		};

		if (complete) {
			dispatchScripts();
			run.run();
		} else {
			pendingTasks.add(run);
		}
	}

	private void dispatchScripts() {
		while (pendingTasks.peek() != null) {
			pendingTasks.poll().run();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T execute(String jsCode) {
		Object result = browser.evaluate(jsCode);
		return (T) result;
	}

	@Override
	public void loadMap(final GeoMap map) {
		currentMap = map;
		reloadMap();
	}

	public void reloadMap() {
		// we are using BrowserFunction as call back to get the serialized map
		// i.e., the JavaScript side will call a function "geoffSWTBridge"
		// which is defined as part of the Browser instance initialization
		String loadXML = String.format("geoff.loadFromXMLString(%s,'%s')", "geoffSWTBridge('loadMap')",
				HTML_MAP_DIV_CONTAINER);

		// remember current values of view center and zoom to be restored after
		// reload
		Object centerValue = centerObservable.getValue();
		Object zoomValue = zoomObservable.getValue();

		executeJavaSript(loadXML);

		// restore view center and zoom
		{
			if (centerValue != null) {
				centerObservable.setValue(centerValue);
			}

			if (zoomValue != null) {
				zoomObservable.setValue(zoomValue);
			}
		}
	}

	@Override
	public IObservableValue observeValue(Property type) {
		@SuppressWarnings("unchecked")
		PropertyHandler<Object> handler = (PropertyHandler<Object>) PropertyHandlers.getInstance().getHandler(type);
		// the initial value can only be retrieved if this widget is marked
		// "complete"
		IObservableValue value = new WritableValue(complete ? handler.getValue(this, type) : null,
				handler.getValueType());
		// guard to be safe against recursive calls
		AtomicBoolean settingValue = new AtomicBoolean(false);
		Listener listener = (e) -> {
			if (!value.isDisposed()) {
				try {
					settingValue.set(true);
					value.setValue(e.value);
				} finally {
					settingValue.set(false);
				}
			}
		};
		addListener(type, listener);

		// if observable is disposed by caller, then remove it from
		// notifications list
		value.addDisposeListener((e) -> {
			removeListener(type, listener);
		});
		value.addValueChangeListener((e) -> {
			// check for recursive calls
			if (!isDisposed() && !settingValue.get()) {
				handler.setValue(GeoMapComposite.this, e.diff.getNewValue());
			}
		});
		// dispose observable once the widget is disposed itself
		// as we do not manage a list of all created observables, each
		// observable is registered with a new dispose listener
		addDisposeListener(evt -> {
			value.dispose();
		});
		return value;
	}

	/**
	 * This is used to notify all observables to update their value as there is
	 * no guarantee that the internal Browser may have been functional at the
	 * moment the observables have been created.
	 */
	private void fireAllEvents() {
		for (Property prop : Property.values()) {
			PropertyHandler<?> handler = PropertyHandlers.getInstance().getHandler(prop);
			Object result = handler.getValue(GeoMapComposite.this, prop);
			fireEvent(prop, result);
		}
	}

	private void fireEvent(Property type, Object result) {
		Set<Consumer<PropertyEvent>> set = eventConsumers.get(type);

		if (set == null) {
			return;
		}

		PropertyEvent edata = new PropertyEvent(type, result);

		set.forEach(c -> {
			c.accept(edata);
		});
	}

	@Override
	public synchronized void addListener(Property type, Listener listener) {
		Set<Consumer<PropertyEvent>> consumers = eventConsumers.get(type);

		if (consumers == null) {
			consumers = new HashSet<>();
			eventConsumers.put(type, consumers);
		}

		consumers.add(listener);
	}

	@Override
	public void removeListener(Property type, Listener listener) {
		Set<Consumer<PropertyEvent>> consumers = eventConsumers.get(type);

		if (consumers != null) {
			// should be synchronized
			consumers.remove(consumers);
		}
	}

	@Override
	public void print(Format format, Consumer<InputStream> consumer) {
		String token = String.valueOf(System.currentTimeMillis() + 1);
		String jsCode = String.format("geoff.printMap('%s')", token);
		pendingResults.put(token, consumer);
		executeJavaSript(jsCode);
	}
}
