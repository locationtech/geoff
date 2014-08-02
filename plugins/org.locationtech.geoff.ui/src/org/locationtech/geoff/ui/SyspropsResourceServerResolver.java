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

/**
 * A resource resolver that can be used in Eclipse RCP environments. This
 * implementation queries system properties to obtain parameters to the
 * resources server.
 * 
 * @author Erdal Karaca
 * 
 */
public class SyspropsResourceServerResolver implements IResourcesServerResolver {
	@Override
	public String getBaseUrl() {
		String server = System.getProperty("geoff.resources.server");
		return String.format("http://%s/", server);
	}

	@Override
	public String getContextPath() {
		return null;
	}
}
