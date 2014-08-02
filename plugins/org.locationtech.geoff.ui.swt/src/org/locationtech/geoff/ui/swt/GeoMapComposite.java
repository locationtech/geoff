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

import java.util.LinkedList;
import java.util.Queue;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.ol.GeoMapProvidersRegistry;
import org.locationtech.geoff.ol.IGeoMapProvider;

@SuppressWarnings("serial")
public class GeoMapComposite extends Composite {
	private static final boolean ENABLE_BROWSER_POPUP_MENU = false;
	private Browser browser;
	private SWTMapController renderer = new SWTMapController();
	private boolean complete = false;

	private Queue<Runnable> pendingTasks = new LinkedList<Runnable>();

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

		renderer.prepareBrowser(browser);
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

	public void loadMap(String registryToken) {
		// FIXME clean existing map on JavaScript side
		renderer.prepareBrowser(browser);
		String loadScript = String.format("window.geoff.loadFromServer('%s')",
				registryToken);
		executeJS(loadScript);
	}

	public void loadMap(final GeoMap map) {
		String token = GeoMapProvidersRegistry.INSTANCE
				.registerProvider(new IGeoMapProvider() {

					public GeoMap getMap() {
						return map;
					}
				});
		loadMap(token);
	}
}
