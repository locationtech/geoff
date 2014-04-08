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
package org.locationtech.geoff.ui;

public abstract class AbstractMapController<B> implements IMapController<B> {
	public static IResourcesServerResolver resourcesServerAddressResolver = new SyspropsResourceServerResolver();

	public String getMapProviderURL() {
		String baseUrl = doGetBaseUrl();
		String contextPath = doGetContextPath();

		if (contextPath == null) {
			contextPath = "";
		} else {
			if (!contextPath.endsWith("/") && !"".equals(contextPath.trim())) {
				contextPath += "/";
			}
		}

		String ret = String.format("%s%swww-ol/index.html?t=%s", baseUrl,
				contextPath, "" + System.currentTimeMillis());
		return ret;
	}

	protected String doGetContextPath() {
		return resourcesServerAddressResolver.getContextPath();
	}

	protected String doGetBaseUrl() {
		return resourcesServerAddressResolver.getBaseUrl();
	}
}
