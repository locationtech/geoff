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

import org.eclipse.emf.common.util.EList;

import org.locationtech.geoff.Feature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vector Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.locationtech.geoff.source.VectorSource#getFeatures <em>Features</em>}</li>
 *   <li>{@link org.locationtech.geoff.source.VectorSource#getUrl <em>Url</em>}</li>
 *   <li>{@link org.locationtech.geoff.source.VectorSource#getProjection <em>Projection</em>}</li>
 *   <li>{@link org.locationtech.geoff.source.VectorSource#getFormat <em>Format</em>}</li>
 * </ul>
 *
 * @see org.locationtech.geoff.source.SourcePackage#getVectorSource()
 * @model
 * @generated
 */
public interface VectorSource extends Source {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = " Copyright (c) 2014 Erdal Karaca.\r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n \r\n Contributors:\r\n     Erdal Karaca - initial API and implementation\r\n"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Features</b></em>' containment reference list.
	 * The list contents are of type {@link org.locationtech.geoff.Feature}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Features</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Features</em>' containment reference list.
	 * @see org.locationtech.geoff.source.SourcePackage#getVectorSource_Features()
	 * @model containment="true"
	 *        extendedMetaData="name='feature' kind='element'"
	 * @generated
	 */
	EList<Feature> getFeatures();

	/**
	 * Returns the value of the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Url</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(String)
	 * @see org.locationtech.geoff.source.SourcePackage#getVectorSource_Url()
	 * @model required="true"
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.source.VectorSource#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

	/**
	 * Returns the value of the '<em><b>Projection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Projection</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Projection</em>' attribute.
	 * @see #setProjection(String)
	 * @see org.locationtech.geoff.source.SourcePackage#getVectorSource_Projection()
	 * @model
	 * @generated
	 */
	String getProjection();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.source.VectorSource#getProjection <em>Projection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Projection</em>' attribute.
	 * @see #getProjection()
	 * @generated
	 */
	void setProjection(String value);

	/**
	 * Returns the value of the '<em><b>Format</b></em>' attribute.
	 * The literals are from the enumeration {@link org.locationtech.geoff.source.SourceFormat}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Format</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Format</em>' attribute.
	 * @see org.locationtech.geoff.source.SourceFormat
	 * @see #setFormat(SourceFormat)
	 * @see org.locationtech.geoff.source.SourcePackage#getVectorSource_Format()
	 * @model
	 * @generated
	 */
	SourceFormat getFormat();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.source.VectorSource#getFormat <em>Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Format</em>' attribute.
	 * @see org.locationtech.geoff.source.SourceFormat
	 * @see #getFormat()
	 * @generated
	 */
	void setFormat(SourceFormat value);

} // VectorSource
