/**
 *  Copyright (c) 2014 Erdal Karaca.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  
 *  Contributors:
 *      Erdal Karaca - initial API and implementation
 * 
 */
package org.locationtech.geoff.geom;

import java.util.List;

import org.locationtech.geoff.Location;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Line String</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.locationtech.geoff.geom.LineString#getCoordinates <em>Coordinates</em>}</li>
 * </ul>
 *
 * @see org.locationtech.geoff.geom.GeomPackage#getLineString()
 * @model
 * @generated
 */
public interface LineString extends SimpleGeometry {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = " Copyright (c) 2014 Erdal Karaca.\r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n \r\n Contributors:\r\n     Erdal Karaca - initial API and implementation\r\n"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Coordinates</b></em>' containment reference list.
	 * The list contents are of type {@link org.locationtech.geoff.Location}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Coordinates</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Coordinates</em>' containment reference list.
	 * @see org.locationtech.geoff.geom.GeomPackage#getLineString_Coordinates()
	 * @model containment="true" lower="2"
	 * @generated
	 */
	List<Location> getCoordinates();

} // LineString
