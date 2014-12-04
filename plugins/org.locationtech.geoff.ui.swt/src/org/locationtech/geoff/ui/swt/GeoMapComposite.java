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

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
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
import org.locationtech.geoff.ol.ResourcesUtil;
import org.locationtech.geoff.ui.IBrowserBridge;
import org.locationtech.geoff.ui.IGeoMapRenderer;
import org.locationtech.geoff.ui.IRenderSettings;

@SuppressWarnings("serial")
public class GeoMapComposite extends Composite implements IGeoMapRenderer,
		IBrowserBridge {
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

		browser.addProgressListener(new ProgressListener() {
			public void completed(ProgressEvent event) {
				complete = true;

				while (pendingTasks.peek() != null) {
					pendingTasks.poll().run();
				}
			}

			public void changed(ProgressEvent event) {
				complete = false;
			}
		});

		registerFunctions();
		browser.addDisposeListener(new DisposeListener() {

			public void widgetDisposed(DisposeEvent e) {
				unregisterFunctions();
			}
		});
	}

	protected void unregisterFunctions() {
		if (bridgeFunc != null) {
			bridgeFunc.dispose();
		}

		if (alertFunc != null) {
			alertFunc.dispose();
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

				throw new UnsupportedOperationException(
						"Invalid bridge parameters: " + arguments);
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

	public void loadHtmlByUrl(String url) {
		browser.setUrl(url);
	}

	public void loadHtmlContents() {
		try {
			// load template file and inline the OL3 CSS file contents
			{
				String indexHtml = ResourcesUtil
						.readResource(ResourcesUtil.INDEX_TEMPLATE_HTML);
				String ol3Css = ResourcesUtil
						.readResource(ResourcesUtil.OL_CSS);
				indexHtml = indexHtml.replace("{{OL3-CSS}}", ol3Css);
				loadHTML(indexHtml);
			}

			// load jQuery and queue pre req check
			{
				String lib = ResourcesUtil
						.readResource(ResourcesUtil.JQUERY_MIN_JS);
				executeJavaSript(lib);
				String checkPreReq = "if (!window.jQuery) {$('body').append("
						+ "'<h1>the JQuery library is not loaded</h1>'" + ");}";
				executeJavaSript(checkPreReq);
			}

			// load OL3 and queue pre req check
			{
				String lib = ResourcesUtil.readResource(ResourcesUtil.OL_JS);
				executeJavaSript(lib);
				String checkPreReq = "if (!window.ol) {$('body').append("
						+ "'<h1>the OpenLayers library is not loaded</h1>'"
						+ ");}";
				executeJavaSript(checkPreReq);
			}

			// queue geoff OL3 implementation
			{
				String geoffOl3Impl = ResourcesUtil
						.readResource(ResourcesUtil.GEOFF_OL_JS);
				executeJavaSript(geoffOl3Impl);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
		String loadXML = String.format("geoff.loadFromXMLString(%s,%s)",
				"geoffSWTBridge('loadMap')", "'map'");
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
		sb.append("base.setAttribute('href', '").append(baseUrl.toString())
				.append("');");
		executeJavaSript(sb.toString());
	}

	@Override
	public void render(GeoMap geoMap, IRenderSettings settings) {
		if (settings.baseURL() != null) {
			setBaseUrl(settings.baseURL());
		}

		loadMap(geoMap);
	}

	@Override
	public void loadHTML(String html) {
		browser.setText(html);
	}
}
