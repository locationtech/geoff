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

import org.eclipse.emf.common.util.EList;
import org.locationtech.geoff.layer.Layer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Geo Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.locationtech.geoff.GeoMap#getLayers <em>Layers</em>}</li>
 *   <li>{@link org.locationtech.geoff.GeoMap#getView <em>View</em>}</li>
 *   <li>{@link org.locationtech.geoff.GeoMap#getName <em>Name</em>}</li>
 *   <li>{@link org.locationtech.geoff.GeoMap#getDescription <em>Description</em>}</li>
 *   <li>{@link org.locationtech.geoff.GeoMap#getRendererHint <em>Renderer Hint</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.locationtech.geoff.GeoffPackage#getGeoMap()
 * @model
 * @generated
 */
public interface GeoMap extends Identifiable {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = " Copyright (c) 2014 Erdal Karaca.\r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n \r\n Contributors:\r\n     Erdal Karaca - initial API and implementation\r\n"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Layers</b></em>' containment reference list.
	 * The list contents are of type {@link org.locationtech.geoff.layer.Layer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Layers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Layers</em>' containment reference list.
	 * @see org.locationtech.geoff.GeoffPackage#getGeoMap_Layers()
	 * @model containment="true"
	 *        extendedMetaData="name='layer' kind='element'"
	 * @generated
	 */
	EList<Layer> getLayers();

	/**
	 * Returns the value of the '<em><b>View</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View</em>' containment reference.
	 * @see #setView(View)
	 * @see org.locationtech.geoff.GeoffPackage#getGeoMap_View()
	 * @model containment="true"
	 * @generated
	 */
	View getView();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.GeoMap#getView <em>View</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>View</em>' containment reference.
	 * @see #getView()
	 * @generated
	 */
	void setView(View value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.locationtech.geoff.GeoffPackage#getGeoMap_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.GeoMap#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.locationtech.geoff.GeoffPackage#getGeoMap_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.GeoMap#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Renderer Hint</b></em>' attribute.
	 * The literals are from the enumeration {@link org.locationtech.geoff.RendererHint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Renderer Hint</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Renderer Hint</em>' attribute.
	 * @see org.locationtech.geoff.RendererHint
	 * @see #setRendererHint(RendererHint)
	 * @see org.locationtech.geoff.GeoffPackage#getGeoMap_RendererHint()
	 * @model
	 * @generated
	 */
	RendererHint getRendererHint();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.GeoMap#getRendererHint <em>Renderer Hint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Renderer Hint</em>' attribute.
	 * @see org.locationtech.geoff.RendererHint
	 * @see #getRendererHint()
	 * @generated
	 */
	void setRendererHint(RendererHint value);

} // GeoMap
