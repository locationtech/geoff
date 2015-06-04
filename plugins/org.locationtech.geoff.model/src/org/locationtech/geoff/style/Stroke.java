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
package org.locationtech.geoff.style;

import org.eclipse.emf.common.util.EList;

import org.locationtech.geoff.Color;
import org.locationtech.geoff.Identifiable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stroke</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.locationtech.geoff.style.Stroke#getColor <em>Color</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.Stroke#getLineCap <em>Line Cap</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.Stroke#getLineJoin <em>Line Join</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.Stroke#getMiterLimit <em>Miter Limit</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.Stroke#getWidth <em>Width</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.Stroke#getLineDash <em>Line Dash</em>}</li>
 * </ul>
 *
 * @see org.locationtech.geoff.style.StylePackage#getStroke()
 * @model
 * @generated
 */
public interface Stroke extends Identifiable {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = " Copyright (c) 2014 Erdal Karaca.\r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n \r\n Contributors:\r\n     Erdal Karaca - initial API and implementation\r\n"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Color</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Color</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color</em>' containment reference.
	 * @see #setColor(Color)
	 * @see org.locationtech.geoff.style.StylePackage#getStroke_Color()
	 * @model containment="true"
	 * @generated
	 */
	Color getColor();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.style.Stroke#getColor <em>Color</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' containment reference.
	 * @see #getColor()
	 * @generated
	 */
	void setColor(Color value);

	/**
	 * Returns the value of the '<em><b>Line Cap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Line Cap</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Cap</em>' attribute.
	 * @see #setLineCap(String)
	 * @see org.locationtech.geoff.style.StylePackage#getStroke_LineCap()
	 * @model
	 * @generated
	 */
	String getLineCap();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.style.Stroke#getLineCap <em>Line Cap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Cap</em>' attribute.
	 * @see #getLineCap()
	 * @generated
	 */
	void setLineCap(String value);

	/**
	 * Returns the value of the '<em><b>Line Join</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Line Join</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Join</em>' attribute.
	 * @see #setLineJoin(String)
	 * @see org.locationtech.geoff.style.StylePackage#getStroke_LineJoin()
	 * @model
	 * @generated
	 */
	String getLineJoin();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.style.Stroke#getLineJoin <em>Line Join</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Join</em>' attribute.
	 * @see #getLineJoin()
	 * @generated
	 */
	void setLineJoin(String value);

	/**
	 * Returns the value of the '<em><b>Miter Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Miter Limit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Miter Limit</em>' attribute.
	 * @see #setMiterLimit(Double)
	 * @see org.locationtech.geoff.style.StylePackage#getStroke_MiterLimit()
	 * @model
	 * @generated
	 */
	Double getMiterLimit();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.style.Stroke#getMiterLimit <em>Miter Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Miter Limit</em>' attribute.
	 * @see #getMiterLimit()
	 * @generated
	 */
	void setMiterLimit(Double value);

	/**
	 * Returns the value of the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Width</em>' attribute.
	 * @see #setWidth(Double)
	 * @see org.locationtech.geoff.style.StylePackage#getStroke_Width()
	 * @model
	 * @generated
	 */
	Double getWidth();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.style.Stroke#getWidth <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Width</em>' attribute.
	 * @see #getWidth()
	 * @generated
	 */
	void setWidth(Double value);

	/**
	 * Returns the value of the '<em><b>Line Dash</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Line Dash</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Dash</em>' attribute list.
	 * @see org.locationtech.geoff.style.StylePackage#getStroke_LineDash()
	 * @model
	 * @generated
	 */
	EList<Double> getLineDash();

} // Stroke
