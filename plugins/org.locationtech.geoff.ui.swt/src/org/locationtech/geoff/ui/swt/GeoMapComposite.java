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
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
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
import org.locationtech.geoff.ui.swt.events.EventsDispatcher;

public class GeoMapComposite extends Composite {
	private static final String HTML_MAP_DIV_CONTAINER = "map";
	private static final boolean ENABLE_BROWSER_POPUP_MENU = false;
	private Browser browser;
	private Boolean complete = null;
	private boolean enableLocationChange = true;

	private List<BrowserFunction> browserFuncs = new ArrayList<>();
	private boolean processNotifications;
	private AtomicInteger notificationsCount = new AtomicInteger(0);

	private Queue<Runnable> pendingTasks = new LinkedList<Runnable>();
	private EventsDispatcher dispatcher = new EventsDispatcher();

	private GeoMap currentMap;
	private Adapter changeListener = new EContentAdapter() {

		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification);

			if (dispatcher.isDispatching()) {
				return;
			}

			if (notification.isTouch()) {
				// return;
			}

			if (isDisposed()) {
				getTarget().eAdapters().remove(this);
				return;
			}

			if (!processNotifications) {
				notificationsCount.incrementAndGet();
				return;
			}

			switch (notification.getEventType()) {
			case Notification.ADD:
			case Notification.REMOVE:
			case Notification.SET:
				getDisplay().asyncExec(new Runnable() {

					@Override
					public void run() {
						triggerLoadingOfMap();
					}
				});
			}
		}
	};

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

	public EventsDispatcher getDispatcher() {
		return dispatcher;
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

//					System.out.println(xml);

					return xml;
				}

				if ("event".equals(arguments[0])) {
					String evtName = (String) arguments[1];
					Object[] params = (Object[]) arguments[2];
					dispatcher.dispatchEvent(evtName, currentMap, params);
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

	public void executeJavaSript(final String jsCode) {
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

	/**
	 * Loads the provided map and install a change listener that will
	 * automatically handle model changes to update the UI state of the map.
	 * 
	 * @param map
	 *            the map to manage
	 */
	public void loadMap(final GeoMap map) {
		if (currentMap != null) {
			Geoff.toEObject(currentMap).eAdapters().remove(changeListener);
		}

		currentMap = map;
		Geoff.toEObject(currentMap).eAdapters().add(changeListener);
		triggerLoadingOfMap();
	}

	private void triggerLoadingOfMap() {
		// we are using BrowserFunction as call back to get the serialized map
		// i.e., the JavaScript side will call a function "geoffSWTBridge"
		// which is defined as part of the Browser instance initialization
		String loadXML = String.format("geoff.loadFromXMLString(%s,'%s')", "geoffSWTBridge('loadMap')",
				HTML_MAP_DIV_CONTAINER);
		executeJavaSript(loadXML);
	}

	public void setNotifications(boolean processNotifications) {
		this.processNotifications = processNotifications;

		if (processNotifications) {
			int count = notificationsCount.getAndSet(0);

			if (count > 0) {
				triggerLoadingOfMap();
			}
		}
	}

	public void groupModelChanges(Runnable runnable) {
		setNotifications(false);
		try {
			runnable.run();
		} finally {
			setNotifications(true);
		}
	}

}
