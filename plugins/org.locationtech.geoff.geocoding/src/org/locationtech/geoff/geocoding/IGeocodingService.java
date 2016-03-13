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
package org.locationtech.geoff.geocoding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

/**
 * A geocoding service interface to query for geographical locations by names,
 * for example, street addresses.
 * 
 * @author Erdal Karaca
 * 
 */
public interface IGeocodingService {
	/**
	 * Queries the underlying service implementation to obtain geographical
	 * locations providing search criteria. It is up to the implementation to
	 * parse/interpret the provided query string.
	 * 
	 * @param query
	 *            the search criteria which the implementing geocoding service
	 *            can understand to decode to a geographical location
	 * @return a list of matching points of interest or an empty list if the
	 *         service could not assign any geographical locations
	 */
	List<POI> executeQuery(String query);

	/**
	 * A helper class to obtain a geocoding service.
	 * 
	 * @author Erdal Karaca
	 * 
	 */
	class Util {
		/**
		 * Queries the platform to return the first found implementation of the
		 * {@link IGeocodingService} interface.
		 * 
		 * @return the 'first found geocoding service'
		 */
		public static IGeocodingService getFirstFound() {
			List<IGeocodingService> list = getService(IGeocodingService.class);
			return list.isEmpty() ? null : list.get(0);
		}

		/**
		 * Queries the platform to return a composition of all found
		 * implementations of the {@link IGeocodingService} interface. The
		 * composed instance will query all available implementations
		 * sequentially (in no defined order) and quit as soon as the first
		 * encountered geocoding service returns a match of some query.
		 * 
		 * @return the composition of all available {@link IGeocodingService}
		 *         implementations
		 */
		public static IGeocodingService getComposed() {
			return new IGeocodingService() {
				@Override
				public List<POI> executeQuery(String query) {
					List<IGeocodingService> list = getService(IGeocodingService.class);

					for (IGeocodingService service : list) {
						List<POI> ret = service.executeQuery(query);

						if (!ret.isEmpty()) {
							return ret;
						}
					}

					return Collections.emptyList();
				}
			};
		}

		/**
		 * A helper method to query the OSGi service registry for instances of
		 * the provided type.
		 * 
		 * @param type
		 *            the type to query an implementation for
		 * @return all instances of the provided type found in the OSGi service
		 *         registry
		 */
		public static <T> List<T> getService(Class<T> type) {
			List<T> ret = new ArrayList<T>();
			BundleContext bundleContext = FrameworkUtil.getBundle(type)
					.getBundleContext();

			try {
				Collection<ServiceReference<T>> refs = bundleContext
						.getServiceReferences(type, null);

				for (ServiceReference<T> ref : refs) {
					ret.add(bundleContext.getService(ref));
				}
			} catch (InvalidSyntaxException e) {
				// assume no services available
			}

			return ret;
		}
	}
}
