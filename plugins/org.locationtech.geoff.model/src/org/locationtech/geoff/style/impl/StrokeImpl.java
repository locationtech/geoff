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
package org.locationtech.geoff.style.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.locationtech.geoff.Color;

import org.locationtech.geoff.style.Stroke;
import org.locationtech.geoff.style.StylePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stroke</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.locationtech.geoff.style.impl.StrokeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.impl.StrokeImpl#getColor <em>Color</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.impl.StrokeImpl#getLineCap <em>Line Cap</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.impl.StrokeImpl#getLineJoin <em>Line Join</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.impl.StrokeImpl#getMiterLimit <em>Miter Limit</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.impl.StrokeImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.impl.StrokeImpl#getLineDash <em>Line Dash</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StrokeImpl extends MinimalEObjectImpl.Container implements Stroke {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = " Copyright (c) 2014 Erdal Karaca.\r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n \r\n Contributors:\r\n     Erdal Karaca - initial API and implementation\r\n"; //$NON-NLS-1$

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getColor() <em>Color</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColor()
	 * @generated
	 * @ordered
	 */
	protected Color color;

	/**
	 * The default value of the '{@link #getLineCap() <em>Line Cap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineCap()
	 * @generated
	 * @ordered
	 */
	protected static final String LINE_CAP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLineCap() <em>Line Cap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineCap()
	 * @generated
	 * @ordered
	 */
	protected String lineCap = LINE_CAP_EDEFAULT;

	/**
	 * The default value of the '{@link #getLineJoin() <em>Line Join</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineJoin()
	 * @generated
	 * @ordered
	 */
	protected static final String LINE_JOIN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLineJoin() <em>Line Join</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineJoin()
	 * @generated
	 * @ordered
	 */
	protected String lineJoin = LINE_JOIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getMiterLimit() <em>Miter Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMiterLimit()
	 * @generated
	 * @ordered
	 */
	protected static final Double MITER_LIMIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMiterLimit() <em>Miter Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMiterLimit()
	 * @generated
	 * @ordered
	 */
	protected Double miterLimit = MITER_LIMIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected static final Double WIDTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected Double width = WIDTH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLineDash() <em>Line Dash</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineDash()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> lineDash;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StrokeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StylePackage.Literals.STROKE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					StylePackage.STROKE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetColor(Color newColor,
			NotificationChain msgs) {
		Color oldColor = color;
		color = newColor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, StylePackage.STROKE__COLOR, oldColor,
					newColor);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColor(Color newColor) {
		if (newColor != color) {
			NotificationChain msgs = null;
			if (color != null)
				msgs = ((InternalEObject) color).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - StylePackage.STROKE__COLOR,
						null, msgs);
			if (newColor != null)
				msgs = ((InternalEObject) newColor).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - StylePackage.STROKE__COLOR,
						null, msgs);
			msgs = basicSetColor(newColor, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					StylePackage.STROKE__COLOR, newColor, newColor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLineCap() {
		return lineCap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLineCap(String newLineCap) {
		String oldLineCap = lineCap;
		lineCap = newLineCap;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					StylePackage.STROKE__LINE_CAP, oldLineCap, lineCap));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLineJoin() {
		return lineJoin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLineJoin(String newLineJoin) {
		String oldLineJoin = lineJoin;
		lineJoin = newLineJoin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					StylePackage.STROKE__LINE_JOIN, oldLineJoin, lineJoin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getMiterLimit() {
		return miterLimit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMiterLimit(Double newMiterLimit) {
		Double oldMiterLimit = miterLimit;
		miterLimit = newMiterLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					StylePackage.STROKE__MITER_LIMIT, oldMiterLimit, miterLimit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getWidth() {
		return width;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWidth(Double newWidth) {
		Double oldWidth = width;
		width = newWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					StylePackage.STROKE__WIDTH, oldWidth, width));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getLineDash() {
		if (lineDash == null) {
			lineDash = new EDataTypeUniqueEList<Double>(Double.class, this,
					StylePackage.STROKE__LINE_DASH);
		}
		return lineDash;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case StylePackage.STROKE__COLOR:
			return basicSetColor(null, msgs);
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
		case StylePackage.STROKE__ID:
			return getId();
		case StylePackage.STROKE__COLOR:
			return getColor();
		case StylePackage.STROKE__LINE_CAP:
			return getLineCap();
		case StylePackage.STROKE__LINE_JOIN:
			return getLineJoin();
		case StylePackage.STROKE__MITER_LIMIT:
			return getMiterLimit();
		case StylePackage.STROKE__WIDTH:
			return getWidth();
		case StylePackage.STROKE__LINE_DASH:
			return getLineDash();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case StylePackage.STROKE__ID:
			setId((String) newValue);
			return;
		case StylePackage.STROKE__COLOR:
			setColor((Color) newValue);
			return;
		case StylePackage.STROKE__LINE_CAP:
			setLineCap((String) newValue);
			return;
		case StylePackage.STROKE__LINE_JOIN:
			setLineJoin((String) newValue);
			return;
		case StylePackage.STROKE__MITER_LIMIT:
			setMiterLimit((Double) newValue);
			return;
		case StylePackage.STROKE__WIDTH:
			setWidth((Double) newValue);
			return;
		case StylePackage.STROKE__LINE_DASH:
			getLineDash().clear();
			getLineDash().addAll((Collection<? extends Double>) newValue);
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
		case StylePackage.STROKE__ID:
			setId(ID_EDEFAULT);
			return;
		case StylePackage.STROKE__COLOR:
			setColor((Color) null);
			return;
		case StylePackage.STROKE__LINE_CAP:
			setLineCap(LINE_CAP_EDEFAULT);
			return;
		case StylePackage.STROKE__LINE_JOIN:
			setLineJoin(LINE_JOIN_EDEFAULT);
			return;
		case StylePackage.STROKE__MITER_LIMIT:
			setMiterLimit(MITER_LIMIT_EDEFAULT);
			return;
		case StylePackage.STROKE__WIDTH:
			setWidth(WIDTH_EDEFAULT);
			return;
		case StylePackage.STROKE__LINE_DASH:
			getLineDash().clear();
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
		case StylePackage.STROKE__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case StylePackage.STROKE__COLOR:
			return color != null;
		case StylePackage.STROKE__LINE_CAP:
			return LINE_CAP_EDEFAULT == null ? lineCap != null
					: !LINE_CAP_EDEFAULT.equals(lineCap);
		case StylePackage.STROKE__LINE_JOIN:
			return LINE_JOIN_EDEFAULT == null ? lineJoin != null
					: !LINE_JOIN_EDEFAULT.equals(lineJoin);
		case StylePackage.STROKE__MITER_LIMIT:
			return MITER_LIMIT_EDEFAULT == null ? miterLimit != null
					: !MITER_LIMIT_EDEFAULT.equals(miterLimit);
		case StylePackage.STROKE__WIDTH:
			return WIDTH_EDEFAULT == null ? width != null : !WIDTH_EDEFAULT
					.equals(width);
		case StylePackage.STROKE__LINE_DASH:
			return lineDash != null && !lineDash.isEmpty();
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
		result.append(" (id: "); //$NON-NLS-1$
		result.append(id);
		result.append(", lineCap: "); //$NON-NLS-1$
		result.append(lineCap);
		result.append(", lineJoin: "); //$NON-NLS-1$
		result.append(lineJoin);
		result.append(", miterLimit: "); //$NON-NLS-1$
		result.append(miterLimit);
		result.append(", width: "); //$NON-NLS-1$
		result.append(width);
		result.append(", lineDash: "); //$NON-NLS-1$
		result.append(lineDash);
		result.append(')');
		return result.toString();
	}

} //StrokeImpl
