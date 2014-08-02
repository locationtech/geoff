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
package org.locationtech.geoff.ol;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class GeoMapProvidersRegistry {
	public static final GeoMapProvidersRegistry INSTANCE = new GeoMapProvidersRegistry();

	public Map<String, IGeoMapProvider> providers = new LinkedHashMap<String, IGeoMapProvider>();

	private GeoMapProvidersRegistry() {
	}

	public String registerProvider(IGeoMapProvider callBack) {
		String token = UUID.randomUUID().toString();
		providers.put(token, callBack);
		return token;
	}

	public IGeoMapProvider unregisterProvider(String token) {
		return providers.remove(token);
	}

	public IGeoMapProvider getProvider(String token) {
		return providers.get(token);
	}
}
