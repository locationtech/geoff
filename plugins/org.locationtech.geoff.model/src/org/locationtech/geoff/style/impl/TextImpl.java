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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.locationtech.geoff.style.Fill;
import org.locationtech.geoff.style.Stroke;
import org.locationtech.geoff.style.StylePackage;
import org.locationtech.geoff.style.Text;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Text</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.locationtech.geoff.style.impl.TextImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.impl.TextImpl#getFill <em>Fill</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.impl.TextImpl#getFont <em>Font</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.impl.TextImpl#getOffsetX <em>Offset X</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.impl.TextImpl#getOffsetY <em>Offset Y</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.impl.TextImpl#getRotation <em>Rotation</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.impl.TextImpl#getScale <em>Scale</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.impl.TextImpl#getStroke <em>Stroke</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.impl.TextImpl#getText <em>Text</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.impl.TextImpl#getTextAlign <em>Text Align</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.impl.TextImpl#getTextBaseLine <em>Text Base Line</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TextImpl extends MinimalEObjectImpl.Container implements Text {
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
	 * The cached value of the '{@link #getFill() <em>Fill</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFill()
	 * @generated
	 * @ordered
	 */
	protected Fill fill;

	/**
	 * The default value of the '{@link #getFont() <em>Font</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFont()
	 * @generated
	 * @ordered
	 */
	protected static final String FONT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFont() <em>Font</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFont()
	 * @generated
	 * @ordered
	 */
	protected String font = FONT_EDEFAULT;

	/**
	 * The default value of the '{@link #getOffsetX() <em>Offset X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOffsetX()
	 * @generated
	 * @ordered
	 */
	protected static final double OFFSET_X_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getOffsetX() <em>Offset X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOffsetX()
	 * @generated
	 * @ordered
	 */
	protected double offsetX = OFFSET_X_EDEFAULT;

	/**
	 * The default value of the '{@link #getOffsetY() <em>Offset Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOffsetY()
	 * @generated
	 * @ordered
	 */
	protected static final double OFFSET_Y_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getOffsetY() <em>Offset Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOffsetY()
	 * @generated
	 * @ordered
	 */
	protected double offsetY = OFFSET_Y_EDEFAULT;

	/**
	 * The default value of the '{@link #getRotation() <em>Rotation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRotation()
	 * @generated
	 * @ordered
	 */
	protected static final Double ROTATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRotation() <em>Rotation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRotation()
	 * @generated
	 * @ordered
	 */
	protected Double rotation = ROTATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getScale() <em>Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScale()
	 * @generated
	 * @ordered
	 */
	protected static final Double SCALE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getScale() <em>Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScale()
	 * @generated
	 * @ordered
	 */
	protected Double scale = SCALE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStroke() <em>Stroke</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStroke()
	 * @generated
	 * @ordered
	 */
	protected Stroke stroke;

	/**
	 * The default value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
	protected static final String TEXT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
	protected String text = TEXT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTextAlign() <em>Text Align</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTextAlign()
	 * @generated
	 * @ordered
	 */
	protected static final String TEXT_ALIGN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTextAlign() <em>Text Align</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTextAlign()
	 * @generated
	 * @ordered
	 */
	protected String textAlign = TEXT_ALIGN_EDEFAULT;

	/**
	 * The default value of the '{@link #getTextBaseLine() <em>Text Base Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTextBaseLine()
	 * @generated
	 * @ordered
	 */
	protected static final String TEXT_BASE_LINE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTextBaseLine() <em>Text Base Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTextBaseLine()
	 * @generated
	 * @ordered
	 */
	protected String textBaseLine = TEXT_BASE_LINE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StylePackage.Literals.TEXT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.TEXT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Fill getFill() {
		return fill;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFill(Fill newFill, NotificationChain msgs) {
		Fill oldFill = fill;
		fill = newFill;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StylePackage.TEXT__FILL,
					oldFill, newFill);
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
	public void setFill(Fill newFill) {
		if (newFill != fill) {
			NotificationChain msgs = null;
			if (fill != null)
				msgs = ((InternalEObject) fill).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StylePackage.TEXT__FILL,
						null, msgs);
			if (newFill != null)
				msgs = ((InternalEObject) newFill).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StylePackage.TEXT__FILL,
						null, msgs);
			msgs = basicSetFill(newFill, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.TEXT__FILL, newFill, newFill));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFont() {
		return font;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFont(String newFont) {
		String oldFont = font;
		font = newFont;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.TEXT__FONT, oldFont, font));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getOffsetX() {
		return offsetX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOffsetX(double newOffsetX) {
		double oldOffsetX = offsetX;
		offsetX = newOffsetX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.TEXT__OFFSET_X, oldOffsetX, offsetX));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getOffsetY() {
		return offsetY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOffsetY(double newOffsetY) {
		double oldOffsetY = offsetY;
		offsetY = newOffsetY;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.TEXT__OFFSET_Y, oldOffsetY, offsetY));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getRotation() {
		return rotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRotation(Double newRotation) {
		Double oldRotation = rotation;
		rotation = newRotation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.TEXT__ROTATION, oldRotation, rotation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getScale() {
		return scale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScale(Double newScale) {
		Double oldScale = scale;
		scale = newScale;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.TEXT__SCALE, oldScale, scale));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Stroke getStroke() {
		return stroke;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStroke(Stroke newStroke, NotificationChain msgs) {
		Stroke oldStroke = stroke;
		stroke = newStroke;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StylePackage.TEXT__STROKE,
					oldStroke, newStroke);
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
	public void setStroke(Stroke newStroke) {
		if (newStroke != stroke) {
			NotificationChain msgs = null;
			if (stroke != null)
				msgs = ((InternalEObject) stroke).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - StylePackage.TEXT__STROKE, null, msgs);
			if (newStroke != null)
				msgs = ((InternalEObject) newStroke).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - StylePackage.TEXT__STROKE, null, msgs);
			msgs = basicSetStroke(newStroke, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.TEXT__STROKE, newStroke, newStroke));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText() {
		return text;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setText(String newText) {
		String oldText = text;
		text = newText;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.TEXT__TEXT, oldText, text));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTextAlign() {
		return textAlign;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTextAlign(String newTextAlign) {
		String oldTextAlign = textAlign;
		textAlign = newTextAlign;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.TEXT__TEXT_ALIGN, oldTextAlign,
					textAlign));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTextBaseLine() {
		return textBaseLine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTextBaseLine(String newTextBaseLine) {
		String oldTextBaseLine = textBaseLine;
		textBaseLine = newTextBaseLine;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.TEXT__TEXT_BASE_LINE, oldTextBaseLine,
					textBaseLine));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case StylePackage.TEXT__FILL:
			return basicSetFill(null, msgs);
		case StylePackage.TEXT__STROKE:
			return basicSetStroke(null, msgs);
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
		case StylePackage.TEXT__ID:
			return getId();
		case StylePackage.TEXT__FILL:
			return getFill();
		case StylePackage.TEXT__FONT:
			return getFont();
		case StylePackage.TEXT__OFFSET_X:
			return getOffsetX();
		case StylePackage.TEXT__OFFSET_Y:
			return getOffsetY();
		case StylePackage.TEXT__ROTATION:
			return getRotation();
		case StylePackage.TEXT__SCALE:
			return getScale();
		case StylePackage.TEXT__STROKE:
			return getStroke();
		case StylePackage.TEXT__TEXT:
			return getText();
		case StylePackage.TEXT__TEXT_ALIGN:
			return getTextAlign();
		case StylePackage.TEXT__TEXT_BASE_LINE:
			return getTextBaseLine();
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
		case StylePackage.TEXT__ID:
			setId((String) newValue);
			return;
		case StylePackage.TEXT__FILL:
			setFill((Fill) newValue);
			return;
		case StylePackage.TEXT__FONT:
			setFont((String) newValue);
			return;
		case StylePackage.TEXT__OFFSET_X:
			setOffsetX((Double) newValue);
			return;
		case StylePackage.TEXT__OFFSET_Y:
			setOffsetY((Double) newValue);
			return;
		case StylePackage.TEXT__ROTATION:
			setRotation((Double) newValue);
			return;
		case StylePackage.TEXT__SCALE:
			setScale((Double) newValue);
			return;
		case StylePackage.TEXT__STROKE:
			setStroke((Stroke) newValue);
			return;
		case StylePackage.TEXT__TEXT:
			setText((String) newValue);
			return;
		case StylePackage.TEXT__TEXT_ALIGN:
			setTextAlign((String) newValue);
			return;
		case StylePackage.TEXT__TEXT_BASE_LINE:
			setTextBaseLine((String) newValue);
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
		case StylePackage.TEXT__ID:
			setId(ID_EDEFAULT);
			return;
		case StylePackage.TEXT__FILL:
			setFill((Fill) null);
			return;
		case StylePackage.TEXT__FONT:
			setFont(FONT_EDEFAULT);
			return;
		case StylePackage.TEXT__OFFSET_X:
			setOffsetX(OFFSET_X_EDEFAULT);
			return;
		case StylePackage.TEXT__OFFSET_Y:
			setOffsetY(OFFSET_Y_EDEFAULT);
			return;
		case StylePackage.TEXT__ROTATION:
			setRotation(ROTATION_EDEFAULT);
			return;
		case StylePackage.TEXT__SCALE:
			setScale(SCALE_EDEFAULT);
			return;
		case StylePackage.TEXT__STROKE:
			setStroke((Stroke) null);
			return;
		case StylePackage.TEXT__TEXT:
			setText(TEXT_EDEFAULT);
			return;
		case StylePackage.TEXT__TEXT_ALIGN:
			setTextAlign(TEXT_ALIGN_EDEFAULT);
			return;
		case StylePackage.TEXT__TEXT_BASE_LINE:
			setTextBaseLine(TEXT_BASE_LINE_EDEFAULT);
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
		case StylePackage.TEXT__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case StylePackage.TEXT__FILL:
			return fill != null;
		case StylePackage.TEXT__FONT:
			return FONT_EDEFAULT == null ? font != null : !FONT_EDEFAULT.equals(font);
		case StylePackage.TEXT__OFFSET_X:
			return offsetX != OFFSET_X_EDEFAULT;
		case StylePackage.TEXT__OFFSET_Y:
			return offsetY != OFFSET_Y_EDEFAULT;
		case StylePackage.TEXT__ROTATION:
			return ROTATION_EDEFAULT == null ? rotation != null : !ROTATION_EDEFAULT.equals(rotation);
		case StylePackage.TEXT__SCALE:
			return SCALE_EDEFAULT == null ? scale != null : !SCALE_EDEFAULT.equals(scale);
		case StylePackage.TEXT__STROKE:
			return stroke != null;
		case StylePackage.TEXT__TEXT:
			return TEXT_EDEFAULT == null ? text != null : !TEXT_EDEFAULT.equals(text);
		case StylePackage.TEXT__TEXT_ALIGN:
			return TEXT_ALIGN_EDEFAULT == null ? textAlign != null : !TEXT_ALIGN_EDEFAULT.equals(textAlign);
		case StylePackage.TEXT__TEXT_BASE_LINE:
			return TEXT_BASE_LINE_EDEFAULT == null ? textBaseLine != null
					: !TEXT_BASE_LINE_EDEFAULT.equals(textBaseLine);
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
		result.append(", font: "); //$NON-NLS-1$
		result.append(font);
		result.append(", offsetX: "); //$NON-NLS-1$
		result.append(offsetX);
		result.append(", offsetY: "); //$NON-NLS-1$
		result.append(offsetY);
		result.append(", rotation: "); //$NON-NLS-1$
		result.append(rotation);
		result.append(", scale: "); //$NON-NLS-1$
		result.append(scale);
		result.append(", text: "); //$NON-NLS-1$
		result.append(text);
		result.append(", textAlign: "); //$NON-NLS-1$
		result.append(textAlign);
		result.append(", textBaseLine: "); //$NON-NLS-1$
		result.append(textBaseLine);
		result.append(')');
		return result.toString();
	}

} //TextImpl
