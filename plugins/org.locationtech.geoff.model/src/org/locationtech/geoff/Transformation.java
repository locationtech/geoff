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
package org.locationtech.geoff;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transformation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.locationtech.geoff.Transformation#getSourceProjection <em>Source Projection</em>}</li>
 *   <li>{@link org.locationtech.geoff.Transformation#getTargetProjection <em>Target Projection</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.locationtech.geoff.GeoffPackage#getTransformation()
 * @model
 * @generated
 */
public interface Transformation extends XYZLocation {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = " Copyright (c) 2014 Erdal Karaca.\r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n \r\n Contributors:\r\n     Erdal Karaca - initial API and implementation\r\n"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Source Projection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Projection</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Projection</em>' attribute.
	 * @see #setSourceProjection(String)
	 * @see org.locationtech.geoff.GeoffPackage#getTransformation_SourceProjection()
	 * @model required="true"
	 * @generated
	 */
	String getSourceProjection();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.Transformation#getSourceProjection <em>Source Projection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Projection</em>' attribute.
	 * @see #getSourceProjection()
	 * @generated
	 */
	void setSourceProjection(String value);

	/**
	 * Returns the value of the '<em><b>Target Projection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Projection</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Projection</em>' attribute.
	 * @see #setTargetProjection(String)
	 * @see org.locationtech.geoff.GeoffPackage#getTransformation_TargetProjection()
	 * @model required="true"
	 * @generated
	 */
	String getTargetProjection();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.Transformation#getTargetProjection <em>Target Projection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Projection</em>' attribute.
	 * @see #getTargetProjection()
	 * @generated
	 */
	void setTargetProjection(String value);

} // Transformation
