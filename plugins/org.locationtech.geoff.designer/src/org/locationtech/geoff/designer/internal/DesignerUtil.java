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
package org.locationtech.geoff.designer.internal;

import java.net.URL;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class DesignerUtil {
	public static final String ICONS_GEOFF_48_PNG = "icons/geoff-48.png";
	public static final String ICONS_GEOFF_16_PNG = "icons/geoff-16.png";
	public static final String ICONS_GEOFF_WIZBAN_PNG = "icons/geoff-wizban.png";

	private static ImageRegistry IMG_REG = new ImageRegistry();

	public static ImageDescriptor getImageDescriptor(String imageFilePath) {
		Bundle bundle = FrameworkUtil.getBundle(DesignerUtil.class);
		ImageDescriptor descriptor = IMG_REG.getDescriptor(imageFilePath);

		if (descriptor == null) {
			URL entry = bundle.getEntry(imageFilePath);
			descriptor = ImageDescriptor.createFromURL(entry);
			IMG_REG.put(imageFilePath, descriptor);
		}

		return descriptor;
	}

	public static ComposedAdapterFactory createComposedAdapterFactor() {
		return new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
	}
}
