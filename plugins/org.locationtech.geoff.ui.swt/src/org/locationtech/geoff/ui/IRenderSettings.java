package org.locationtech.geoff.ui;

import java.net.URL;

/**
 *
 */
public interface IRenderSettings {
	/**
	 * @return an {@link URL} that is used as source for external resources,
	 *         for example, if the geo map references icons or images, then
	 *         this URL denotes the base {@link URL}, can be
	 *         <code>null</code> to signal that no external resources are
	 *         used or if all resources in the geo map are referenced by an
	 *         absolute {@link URL}
	 */
	URL baseURL();
}