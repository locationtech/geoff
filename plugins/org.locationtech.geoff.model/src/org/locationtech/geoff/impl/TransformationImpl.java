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
package org.locationtech.geoff.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.locationtech.geoff.GeoffPackage;
import org.locationtech.geoff.Transformation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transformation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.locationtech.geoff.impl.TransformationImpl#getSourceProjection <em>Source Projection</em>}</li>
 *   <li>{@link org.locationtech.geoff.impl.TransformationImpl#getTargetProjection <em>Target Projection</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransformationImpl extends XYZLocationImpl implements
		Transformation {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = " Copyright (c) 2014 Erdal Karaca.\r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n \r\n Contributors:\r\n     Erdal Karaca - initial API and implementation\r\n"; //$NON-NLS-1$

	/**
	 * The default value of the '{@link #getSourceProjection() <em>Source Projection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceProjection()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_PROJECTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceProjection() <em>Source Projection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceProjection()
	 * @generated
	 * @ordered
	 */
	protected String sourceProjection = SOURCE_PROJECTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetProjection() <em>Target Projection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetProjection()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_PROJECTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetProjection() <em>Target Projection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetProjection()
	 * @generated
	 * @ordered
	 */
	protected String targetProjection = TARGET_PROJECTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransformationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeoffPackage.Literals.TRANSFORMATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSourceProjection() {
		return sourceProjection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceProjection(String newSourceProjection) {
		String oldSourceProjection = sourceProjection;
		sourceProjection = newSourceProjection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					GeoffPackage.TRANSFORMATION__SOURCE_PROJECTION,
					oldSourceProjection, sourceProjection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetProjection() {
		return targetProjection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetProjection(String newTargetProjection) {
		String oldTargetProjection = targetProjection;
		targetProjection = newTargetProjection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					GeoffPackage.TRANSFORMATION__TARGET_PROJECTION,
					oldTargetProjection, targetProjection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GeoffPackage.TRANSFORMATION__SOURCE_PROJECTION:
			return getSourceProjection();
		case GeoffPackage.TRANSFORMATION__TARGET_PROJECTION:
			return getTargetProjection();
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
		case GeoffPackage.TRANSFORMATION__SOURCE_PROJECTION:
			setSourceProjection((String) newValue);
			return;
		case GeoffPackage.TRANSFORMATION__TARGET_PROJECTION:
			setTargetProjection((String) newValue);
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
		case GeoffPackage.TRANSFORMATION__SOURCE_PROJECTION:
			setSourceProjection(SOURCE_PROJECTION_EDEFAULT);
			return;
		case GeoffPackage.TRANSFORMATION__TARGET_PROJECTION:
			setTargetProjection(TARGET_PROJECTION_EDEFAULT);
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
		case GeoffPackage.TRANSFORMATION__SOURCE_PROJECTION:
			return SOURCE_PROJECTION_EDEFAULT == null ? sourceProjection != null
					: !SOURCE_PROJECTION_EDEFAULT.equals(sourceProjection);
		case GeoffPackage.TRANSFORMATION__TARGET_PROJECTION:
			return TARGET_PROJECTION_EDEFAULT == null ? targetProjection != null
					: !TARGET_PROJECTION_EDEFAULT.equals(targetProjection);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (sourceProjection: "); //$NON-NLS-1$
		result.append(sourceProjection);
		result.append(", targetProjection: "); //$NON-NLS-1$
		result.append(targetProjection);
		result.append(')');
		return result.toString();
	}

} //TransformationImpl
