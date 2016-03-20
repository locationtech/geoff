/*******************************************************************************
 * Copyright (c) 2016 Erdal Karaca and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Erdal Karaca - initial API and implementation
 *******************************************************************************/
package org.locationtech.geoff.core;

import java.io.File;

import org.locationtech.geoff.core.internal.ChangeRecorderGeoMapServiceImpl;

public class GeoMapServiceFactory {

	public static IGeoMapService create(File file) {
		return new ChangeRecorderGeoMapServiceImpl(file);
	}

}
