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
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

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
import org.locationtech.geoff.ol.ResourcesUtil;

@SuppressWarnings("serial")
public class GeoMapComposite extends Composite {
	private static final boolean ENABLE_BROWSER_POPUP_MENU = false;
	private Browser browser;
	private boolean complete = false;

	private Queue<Runnable> pendingTasks = new LinkedList<Runnable>();
	private GeoMap currentMap;

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

		try {
			// load template file and inline the OL3 CSS file contents
			{
				String indexHtml = ResourcesUtil
						.readResource("index.template.html");
				String ol3Css = ResourcesUtil.readResource("ol3-g3.css");
				indexHtml = indexHtml.replace("{{OL3-CSS}}", ol3Css);
				browser.setText(indexHtml);
			}

			// load jQuery and queue pre req check
			{
				String lib = ResourcesUtil.readResource("jquery-1.10.2.min.js");
				executeJS(lib);
				String checkPreReq = "if (!window.jQuery) {$('body').append("
						+ "'<h1>the JQuery library is not loaded</h1>'" + ");}";
				executeJS(checkPreReq);
			}

			// load OL3 and queue pre req check
			{
				String lib = ResourcesUtil.readResource("ol3-g3.js");
				executeJS(lib);
				String checkPreReq = "if (!window.ol) {$('body').append("
						+ "'<h1>the OpenLayers library is not loaded</h1>'"
						+ ");}";
				executeJS(checkPreReq);
			}

			// queue geoff OL3 implementation
			{
				String geoffOl3Impl = ResourcesUtil
						.readResource("geoff-ol3.js");
				executeJS(geoffOl3Impl);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// this is to transport serialized maps to the JavaScript side
		// to circumvent escaping strings when executing
		// Browser#evaluate(String)
		final BrowserFunction func = new BrowserFunction(browser,
				"geoffSWTBridge") {
			@Override
			public Object function(Object[] arguments) {
				if (arguments != null && arguments.length > 0) {
					if ("loadMap".equals(arguments[0])) {
						if (currentMap != null) {
							String xml = Geoff.wrap(currentMap).toXML();
							return xml;
						}
					}
				}

				throw new UnsupportedOperationException(
						"Invalid bridge parameters: " + arguments);
			}
		};

		browser.addDisposeListener(new DisposeListener() {

			public void widgetDisposed(DisposeEvent e) {
				func.dispose();
			}
		});
	}

	public Browser getBrowser() {
		return browser;
	}

	private void executeJS(final String jsCode) {
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
		// TODO attach listener to map to auto-update changes
		this.currentMap = map;

		// we are using BrowserFunction as call back to get the serialized map
		// i.e., the JavaScript side will call a function "geoffSWTBridge"
		// which is defined as part of the Browser instance initialization
		String loadXML = String.format("geoff.loadFromXMLString(%s,%s)",
				"geoffSWTBridge('loadMap')", "'map'");
		executeJS(loadXML);
	}

	public void setBaseUrl(URL baseUrl) {
		StringBuilder sb = new StringBuilder();
		sb.append("var newBase = document.createElement('base');");
		sb.append("newBase.setAttribute('href', '").append(baseUrl.toString())
				.append("');");
		sb.append("document.getElementsByTagName('head')[0].appendChild(newBase);");
		// sb.append("alert(document.getElementsByTagName('base')[0].getAttribute('href'));");
		executeJS(sb.toString());
	}
}
