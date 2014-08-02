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
 * An interface that can handle a platform specific (SWT, JavaFX) browser
 * widget.
 * 
 * @author Erdal Karaca
 * 
 * @param <B>
 *            the browser generic type (for example, the Browser widget in SWT
 *            or the WebView control in JavaFX)
 */
public interface IMapController<B> {
	void prepareBrowser(B browser);
}
