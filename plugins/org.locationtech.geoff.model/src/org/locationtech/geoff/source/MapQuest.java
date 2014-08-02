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
 * A representation of the model object '<em><b>Map Quest Open Aerial</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.locationtech.geoff.source.MapQuest#getLayer <em>Layer</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.locationtech.geoff.source.SourcePackage#getMapQuest()
 * @model
 * @generated
 */
public interface MapQuest extends XYZ {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = " Copyright (c) 2014 Erdal Karaca.\r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n \r\n Contributors:\r\n     Erdal Karaca - initial API and implementation\r\n"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Layer</b></em>' attribute.
	 * The default value is <code>"osm"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Layer</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Layer</em>' attribute.
	 * @see #setLayer(String)
	 * @see org.locationtech.geoff.source.SourcePackage#getMapQuest_Layer()
	 * @model default="osm"
	 * @generated
	 */
	String getLayer();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.source.MapQuest#getLayer <em>Layer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Layer</em>' attribute.
	 * @see #getLayer()
	 * @generated
	 */
	void setLayer(String value);

} // MapQuestOpenAerial
