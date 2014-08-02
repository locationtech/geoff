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

import org.eclipse.swt.browser.Browser;
import org.locationtech.geoff.ui.AbstractMapController;

public class SWTMapController extends AbstractMapController<Browser> {
	public void prepareBrowser(Browser browser) {
		String mapProviderURL = getMapProviderURL();
		browser.setUrl(mapProviderURL);
	}
}
