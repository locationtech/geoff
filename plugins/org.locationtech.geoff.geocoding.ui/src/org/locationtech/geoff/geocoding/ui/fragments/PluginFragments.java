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
package org.locationtech.geoff.geocoding.ui.fragments;

import org.locationtech.geoff.e4.utils.expr.IExpressionProvider;
import org.locationtech.geoff.e4.utils.fragments.FragmentBuilder;
import org.locationtech.geoff.e4.utils.fragments.ModelFragmentsProvider;
import org.locationtech.geoff.geocoding.ui.GeocodingPart;

public class PluginFragments extends ModelFragmentsProvider implements IExpressionProvider {

	public void fragmentPartDescriptors(FragmentBuilder b) {
		b.customize(f -> {
			f.setParentElementId(ANY_APPLICATION);
			f.setFeaturename("descriptors");
		}).element(FDESC::createPartDescriptor, (pdesc, pdescBuilder) -> {
			pdesc.setElementId(GeocodingPart.class.getName());
			pdesc.setDirtyable(false);
			pdesc.setCloseable(true);
			pdesc.setContributionURI(toBundleclassURI(GeocodingPart.class));
			pdesc.setLabel("Geocoding");
			pdesc.setIconURI(toPlatformURI("icons/marker-blue.png"));
		});
	}
}
