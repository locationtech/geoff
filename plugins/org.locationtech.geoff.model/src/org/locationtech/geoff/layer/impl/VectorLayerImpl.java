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
package org.locationtech.geoff.layer.impl;

import java.util.Map;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.locationtech.geoff.GeoffPackage;

import org.locationtech.geoff.impl.StyleEntryImpl;

import org.locationtech.geoff.layer.LayerPackage;
import org.locationtech.geoff.layer.VectorLayer;

import org.locationtech.geoff.style.Style;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Vector Layer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.locationtech.geoff.layer.impl.VectorLayerImpl#getStyles <em>Styles</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VectorLayerImpl extends LayerImpl implements VectorLayer {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = " Copyright (c) 2014 Erdal Karaca.\r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n \r\n Contributors:\r\n     Erdal Karaca - initial API and implementation\r\n"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getStyles() <em>Styles</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyles()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, Style> styles;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VectorLayerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LayerPackage.Literals.VECTOR_LAYER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, Style> getStyles() {
		if (styles == null) {
			styles = new EcoreEMap<String, Style>(GeoffPackage.Literals.STYLE_ENTRY, StyleEntryImpl.class, this,
					LayerPackage.VECTOR_LAYER__STYLES);
		}
		return styles.map();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case LayerPackage.VECTOR_LAYER__STYLES:
			return ((InternalEList<?>) ((EMap.InternalMapView<String, Style>) getStyles()).eMap()).basicRemove(otherEnd,
					msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case LayerPackage.VECTOR_LAYER__STYLES:
			if (coreType)
				return ((EMap.InternalMapView<String, Style>) getStyles()).eMap();
			else
				return getStyles();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case LayerPackage.VECTOR_LAYER__STYLES:
			((EStructuralFeature.Setting) ((EMap.InternalMapView<String, Style>) getStyles()).eMap()).set(newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case LayerPackage.VECTOR_LAYER__STYLES:
			getStyles().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case LayerPackage.VECTOR_LAYER__STYLES:
			return styles != null && !styles.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //VectorLayerImpl
