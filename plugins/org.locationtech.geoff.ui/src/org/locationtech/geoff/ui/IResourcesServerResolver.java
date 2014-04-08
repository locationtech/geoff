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
 * A resolver interface used to provide paths to resources. In a typical Eclipse
 * RCP this can be used to redirect to an embedded web server. In RAP this can
 * be used to delegate to the web application server the application itself is
 * running in.
 * 
 * @author Erdal Karaca
 * 
 */
public interface IResourcesServerResolver {
	String getBaseUrl();

	String getContextPath();
}
