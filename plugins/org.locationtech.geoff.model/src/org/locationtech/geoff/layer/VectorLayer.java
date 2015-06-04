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
package org.locationtech.geoff.layer;

import org.eclipse.emf.common.util.EMap;

import org.locationtech.geoff.style.Style;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vector Layer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.locationtech.geoff.layer.VectorLayer#getStyles <em>Styles</em>}</li>
 * </ul>
 *
 * @see org.locationtech.geoff.layer.LayerPackage#getVectorLayer()
 * @model
 * @generated
 */
public interface VectorLayer extends Layer {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = " Copyright (c) 2014 Erdal Karaca.\r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n \r\n Contributors:\r\n     Erdal Karaca - initial API and implementation\r\n"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Styles</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.locationtech.geoff.style.Style},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Styles</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Styles</em>' map.
	 * @see org.locationtech.geoff.layer.LayerPackage#getVectorLayer_Styles()
	 * @model mapType="org.locationtech.geoff.StyleEntry<org.eclipse.emf.ecore.EString, org.locationtech.geoff.style.Style>"
	 *        extendedMetaData="name='style' kind='element'"
	 * @generated
	 */
	EMap<String, Style> getStyles();

} // VectorLayer
