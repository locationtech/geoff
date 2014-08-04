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
import org.locationtech.geoff.ol.ScriptUtil;

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
			String indexHtml = ScriptUtil.getIndexHtml();
			browser.setText(indexHtml);
			String geoffOl3Impl = ScriptUtil.getOL3ImplScript(true);
			executeJS(geoffOl3Impl);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

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

		String loadXML = String.format("geoff.loadFromXMLString(%s,%s)",
				"geoffSWTBridge('loadMap')", "'map'");
		executeJS(loadXML);
	}
}
