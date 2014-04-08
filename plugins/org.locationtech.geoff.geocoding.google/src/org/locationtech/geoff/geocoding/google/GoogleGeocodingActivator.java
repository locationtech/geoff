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
package org.locationtech.geoff.geocoding.google;

import org.locationtech.geoff.geocoding.IGeocodingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class GoogleGeocodingActivator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		context.registerService(IGeocodingService.class,
				new GoogleGeocodingAPIService(), null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {

	}
}
