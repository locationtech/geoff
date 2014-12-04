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

import org.locationtech.geoff.Identifiable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Text</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.locationtech.geoff.style.Text#getFill <em>Fill</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.Text#getFont <em>Font</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.Text#getOffsetX <em>Offset X</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.Text#getOffsetY <em>Offset Y</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.Text#getRotation <em>Rotation</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.Text#getScale <em>Scale</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.Text#getStroke <em>Stroke</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.Text#getText <em>Text</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.Text#getTextAlign <em>Text Align</em>}</li>
 *   <li>{@link org.locationtech.geoff.style.Text#getTextBaseLine <em>Text Base Line</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.locationtech.geoff.style.StylePackage#getText()
 * @model
 * @generated
 */
public interface Text extends Identifiable {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = " Copyright (c) 2014 Erdal Karaca.\r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n \r\n Contributors:\r\n     Erdal Karaca - initial API and implementation\r\n"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Fill</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fill</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fill</em>' containment reference.
	 * @see #setFill(Fill)
	 * @see org.locationtech.geoff.style.StylePackage#getText_Fill()
	 * @model containment="true"
	 * @generated
	 */
	Fill getFill();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.style.Text#getFill <em>Fill</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fill</em>' containment reference.
	 * @see #getFill()
	 * @generated
	 */
	void setFill(Fill value);

	/**
	 * Returns the value of the '<em><b>Font</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Font</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Font</em>' attribute.
	 * @see #setFont(String)
	 * @see org.locationtech.geoff.style.StylePackage#getText_Font()
	 * @model
	 * @generated
	 */
	String getFont();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.style.Text#getFont <em>Font</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Font</em>' attribute.
	 * @see #getFont()
	 * @generated
	 */
	void setFont(String value);

	/**
	 * Returns the value of the '<em><b>Offset X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Offset X</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Offset X</em>' attribute.
	 * @see #setOffsetX(double)
	 * @see org.locationtech.geoff.style.StylePackage#getText_OffsetX()
	 * @model
	 * @generated
	 */
	double getOffsetX();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.style.Text#getOffsetX <em>Offset X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Offset X</em>' attribute.
	 * @see #getOffsetX()
	 * @generated
	 */
	void setOffsetX(double value);

	/**
	 * Returns the value of the '<em><b>Offset Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Offset Y</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Offset Y</em>' attribute.
	 * @see #setOffsetY(double)
	 * @see org.locationtech.geoff.style.StylePackage#getText_OffsetY()
	 * @model
	 * @generated
	 */
	double getOffsetY();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.style.Text#getOffsetY <em>Offset Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Offset Y</em>' attribute.
	 * @see #getOffsetY()
	 * @generated
	 */
	void setOffsetY(double value);

	/**
	 * Returns the value of the '<em><b>Rotation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rotation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rotation</em>' attribute.
	 * @see #setRotation(Double)
	 * @see org.locationtech.geoff.style.StylePackage#getText_Rotation()
	 * @model
	 * @generated
	 */
	Double getRotation();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.style.Text#getRotation <em>Rotation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rotation</em>' attribute.
	 * @see #getRotation()
	 * @generated
	 */
	void setRotation(Double value);

	/**
	 * Returns the value of the '<em><b>Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scale</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scale</em>' attribute.
	 * @see #setScale(Double)
	 * @see org.locationtech.geoff.style.StylePackage#getText_Scale()
	 * @model
	 * @generated
	 */
	Double getScale();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.style.Text#getScale <em>Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scale</em>' attribute.
	 * @see #getScale()
	 * @generated
	 */
	void setScale(Double value);

	/**
	 * Returns the value of the '<em><b>Stroke</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stroke</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stroke</em>' containment reference.
	 * @see #setStroke(Stroke)
	 * @see org.locationtech.geoff.style.StylePackage#getText_Stroke()
	 * @model containment="true"
	 * @generated
	 */
	Stroke getStroke();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.style.Text#getStroke <em>Stroke</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stroke</em>' containment reference.
	 * @see #getStroke()
	 * @generated
	 */
	void setStroke(Stroke value);

	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text</em>' attribute.
	 * @see #setText(String)
	 * @see org.locationtech.geoff.style.StylePackage#getText_Text()
	 * @model
	 * @generated
	 */
	String getText();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.style.Text#getText <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' attribute.
	 * @see #getText()
	 * @generated
	 */
	void setText(String value);

	/**
	 * Returns the value of the '<em><b>Text Align</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text Align</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text Align</em>' attribute.
	 * @see #setTextAlign(String)
	 * @see org.locationtech.geoff.style.StylePackage#getText_TextAlign()
	 * @model
	 * @generated
	 */
	String getTextAlign();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.style.Text#getTextAlign <em>Text Align</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text Align</em>' attribute.
	 * @see #getTextAlign()
	 * @generated
	 */
	void setTextAlign(String value);

	/**
	 * Returns the value of the '<em><b>Text Base Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text Base Line</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text Base Line</em>' attribute.
	 * @see #setTextBaseLine(String)
	 * @see org.locationtech.geoff.style.StylePackage#getText_TextBaseLine()
	 * @model
	 * @generated
	 */
	String getTextBaseLine();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.style.Text#getTextBaseLine <em>Text Base Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text Base Line</em>' attribute.
	 * @see #getTextBaseLine()
	 * @generated
	 */
	void setTextBaseLine(String value);

} // Text
