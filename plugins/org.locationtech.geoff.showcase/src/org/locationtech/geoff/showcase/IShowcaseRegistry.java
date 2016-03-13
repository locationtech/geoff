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
package org.locationtech.geoff.showcase;

import java.util.LinkedHashSet;
import java.util.Set;

import org.locationtech.geoff.showcase.examples.BingMapsExample;
import org.locationtech.geoff.showcase.examples.GeoCodingExample;
import org.locationtech.geoff.showcase.examples.MapQuestExample;
import org.locationtech.geoff.showcase.examples.OSMExample;

public interface IShowcaseRegistry {
	IShowcaseRegistry DEFAULT = new ShowcaseRegistry();

	Set<IExampleGeoMap> getExamples();

	class ShowcaseRegistry implements IShowcaseRegistry {
		private Set<IExampleGeoMap> examples = new LinkedHashSet<IExampleGeoMap>();
		{
			examples.add(new OSMExample());
			examples.add(new BingMapsExample());
			examples.add(new MapQuestExample());
			examples.add(new GeoCodingExample());
		}

		public Set<IExampleGeoMap> getExamples() {
			return examples;
		}
	}
}
