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
package org.locationtech.geoff.source;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bing Maps</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.locationtech.geoff.source.BingMaps#getKey <em>Key</em>}</li>
 *   <li>{@link org.locationtech.geoff.source.BingMaps#getImagerySet <em>Imagery Set</em>}</li>
 * </ul>
 *
 * @see org.locationtech.geoff.source.SourcePackage#getBingMaps()
 * @model
 * @generated
 */
public interface BingMaps extends XYZ {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = " Copyright (c) 2014 Erdal Karaca.\r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n \r\n Contributors:\r\n     Erdal Karaca - initial API and implementation\r\n"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key</em>' attribute.
	 * @see #setKey(String)
	 * @see org.locationtech.geoff.source.SourcePackage#getBingMaps_Key()
	 * @model required="true"
	 * @generated
	 */
	String getKey();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.source.BingMaps#getKey <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' attribute.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(String value);

	/**
	 * Returns the value of the '<em><b>Imagery Set</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imagery Set</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imagery Set</em>' attribute.
	 * @see #setImagerySet(String)
	 * @see org.locationtech.geoff.source.SourcePackage#getBingMaps_ImagerySet()
	 * @model required="true"
	 * @generated
	 */
	String getImagerySet();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.source.BingMaps#getImagerySet <em>Imagery Set</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Imagery Set</em>' attribute.
	 * @see #getImagerySet()
	 * @generated
	 */
	void setImagerySet(String value);

} // BingMaps
