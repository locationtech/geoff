/*******************************************************************************
 * Copyright (c) 2014 Erdal Karaca.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Erdal Karaca - initial API and implementation
 ******************************************************************************/
package org.locationtech.geoff.ui.swt;

import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.ui.IGeoMapRenderer;
import org.locationtech.geoff.ui.IRenderSettings;

@SuppressWarnings("serial")
public class GeoMapComposite extends Composite implements IGeoMapRenderer {
	private static final boolean ENABLE_BROWSER_POPUP_MENU = false;
	private Browser browser;
	private boolean complete = false;

	private Queue<Runnable> pendingTasks = new LinkedList<Runnable>();
	private GeoMap currentMap;
	private Adapter changeListener = new EContentAdapter() {
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification);

			if (isDisposed()) {
				getTarget().eAdapters().remove(this);
				return;
			}

			switch (notification.getEventType()) {
			case Notification.ADD:
			case Notification.REMOVE:
				getDisplay().asyncExec(new Runnable() {

					@Override
					public void run() {
						triggerLoadingOfMap();
					}
				});
			}
		}
	};
	private BrowserFunction bridgeFunc;
	private BrowserFunction alertFunc;

	public GeoMapComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		browser = new Browser(this, SWT.None);

		browser.addMenuDetectListener(new MenuDetectListener() {
			public void menuDetected(MenuDetectEvent e) {
				e.doit = ENABLE_BROWSER_POPUP_MENU;
			}
		});

		browser.addDisposeListener(new DisposeListener() {

			public void widgetDisposed(DisposeEvent e) {
				unregisterFunctions();
			}
		});

		browser.addLocationListener(new LocationListener() {

			@Override
			public void changing(LocationEvent event) {
				complete = false;
			}

			@Override
			public void changed(LocationEvent event) {
				unregisterFunctions();
				registerFunctions();
				complete = true;

				while (pendingTasks.peek() != null) {
					pendingTasks.poll().run();
				}
			}
		});

		setupOpenLayers();
	}

	protected void unregisterFunctions() {
		if (bridgeFunc != null) {
			bridgeFunc.dispose();
			bridgeFunc = null;
		}

		if (alertFunc != null) {
			alertFunc.dispose();
			alertFunc = null;
		}
	}

	protected void registerFunctions() {
		unregisterFunctions();

		bridgeFunc = new BrowserFunction(browser, "geoffSWTBridge") {
			@Override
			public Object function(Object[] arguments) {
				if (arguments != null && arguments.length > 0) {
					if ("loadMap".equals(arguments[0])) {
						if (currentMap != null) {
							String xml = Geoff.wrap(currentMap).toXML();
							System.out.println(xml);
							return xml;
						}
					}
				}

				throw new UnsupportedOperationException("Invalid bridge parameters: " + arguments);
			}
		};

		alertFunc = new BrowserFunction(browser, "alert") {
			@Override
			public Object function(Object[] arguments) {
				for (Object object : arguments) {
					System.err.print(object);
				}
				System.err.println();
				return null;
			}
		};
	}

	private void setupOpenLayers() {
		String httpPort = System.getProperty("org.osgi.service.http.port", "8080");
		String httpHost = System.getProperty("org.osgi.service.http.host", "localhost");
		String url = String.format("http://%s:%s/%s/%s", httpHost, httpPort, "org.locationtech.geoff.ol",
				"resources/index-fullmap.html");
		browser.setUrl(url);
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

		if (complete) {
			run.run();
		} else {
			pendingTasks.add(run);
		}
	}

	public void loadMap(final GeoMap map) {
		if (currentMap != null) {
			currentMap.eAdapters().remove(changeListener);
		}

		currentMap = map;
		currentMap.eAdapters().add(changeListener);

		triggerLoadingOfMap();
	}

	private void triggerLoadingOfMap() {
		// we are using BrowserFunction as call back to get the serialized map
		// i.e., the JavaScript side will call a function "geoffSWTBridge"
		// which is defined as part of the Browser instance initialization
		String loadXML = String.format("geoff.loadFromXMLString(%s,%s)", "geoffSWTBridge('loadMap')", "'map'");
		executeJavaSript(loadXML);
	}

	public void setBaseUrl(URL baseUrl) {
		if (baseUrl == null) {
			throw new IllegalArgumentException("The base URL must not be null.");
		}

		StringBuilder sb = new StringBuilder();
		sb.append("var b=document.getElementsByTagName('base');");
		sb.append("var base=null;");
		sb.append("if(b.length>0) base=b[0];");
		sb.append("if(base == null){base=document.createElement('base');");
		sb.append("document.getElementsByTagName('head')[0].appendChild(base);}");
		sb.append("base.setAttribute('href', '").append(baseUrl.toString()).append("');");
		executeJavaSript(sb.toString());
	}

	@Override
	public void render(GeoMap geoMap, IRenderSettings settings) {
		if (settings.baseURL() != null) {
			// setBaseUrl(settings.baseURL());
		}

		loadMap(geoMap);
	}
}
