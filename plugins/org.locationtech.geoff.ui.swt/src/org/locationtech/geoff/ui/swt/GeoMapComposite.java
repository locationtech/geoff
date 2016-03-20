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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
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
import org.locationtech.geoff.ui.swt.internal.PropertyHandlers;
import org.locationtech.geoff.ui.swt.internal.PropertyHandlers.PropertyHandler;

public class GeoMapComposite extends Composite implements IGeoMapWidget {
	private static final String HTML_MAP_DIV_CONTAINER = "map";
	private static final boolean ENABLE_BROWSER_POPUP_MENU = false;
	private Browser browser;
	private Boolean complete = null;
	private boolean enableLocationChange = true;

	private List<BrowserFunction> browserFuncs = new ArrayList<>();
	private Queue<Runnable> pendingTasks = new LinkedList<Runnable>();
	private Map<Property, Set<Consumer<PropertyEvent>>> eventConsumers = new HashMap<GeoMapComposite.Property, Set<Consumer<PropertyEvent>>>();
	private GeoMap currentMap;

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
				complete = Boolean.TRUE;
				// after the content is setup, no change in location is allowed
				// else, the browser widget might open external pages within
				// itself
				// enableLocationChange = false;

				while (pendingTasks.peek() != null) {
					pendingTasks.poll().run();
				}
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

				if ("loadMap".equals(arguments[0])) {
					String xml;

					if (currentMap == null) {
						xml = EMPTY_MAP;
					} else {
						xml = Geoff.wrap(currentMap).toXML();
					}

					// System.out.println(xml);

					return xml;
				}

				if ("event".equals(arguments[0])) {
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

		if (complete != null) {
			run.run();
		} else {
			pendingTasks.add(run);
		}
	}

	@SuppressWarnings("unchecked")
	private <T> T execNow(String jsCode) {
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
		executeJavaSript(loadXML);
	}

	@Override
	public IObservableValue observeValue(Property type) {
		PropertyHandler<?> handler = PropertyHandlers.getInstance().getHandler(type);
		IObservableValue value = new WritableValue(handler.getValue(this), handler.getValueType());
		Listener listener = (e) -> {
			if (!value.isDisposed()) {
				value.setValue(e.value);
			}
		};
		addListener(type, listener);
		value.addDisposeListener((e) -> {
			removeListener(type, listener);
		});
		return value;
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
}
