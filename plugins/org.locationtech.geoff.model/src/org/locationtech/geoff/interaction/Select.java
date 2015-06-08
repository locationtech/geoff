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
package org.locationtech.geoff.interaction;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Select</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.locationtech.geoff.interaction.Select#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.locationtech.geoff.interaction.Select#isMulti <em>Multi</em>}</li>
 * </ul>
 *
 * @see org.locationtech.geoff.interaction.InteractionPackage#getSelect()
 * @model
 * @generated
 */
public interface Select extends Interaction {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = " Copyright (c) 2014 Erdal Karaca.\r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n \r\n Contributors:\r\n     Erdal Karaca - initial API and implementation\r\n"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' attribute.
	 * The literals are from the enumeration {@link org.locationtech.geoff.interaction.EventCondition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' attribute.
	 * @see org.locationtech.geoff.interaction.EventCondition
	 * @see #setCondition(EventCondition)
	 * @see org.locationtech.geoff.interaction.InteractionPackage#getSelect_Condition()
	 * @model required="true"
	 * @generated
	 */
	EventCondition getCondition();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.interaction.Select#getCondition <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' attribute.
	 * @see org.locationtech.geoff.interaction.EventCondition
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(EventCondition value);

	/**
	 * Returns the value of the '<em><b>Multi</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multi</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multi</em>' attribute.
	 * @see #setMulti(boolean)
	 * @see org.locationtech.geoff.interaction.InteractionPackage#getSelect_Multi()
	 * @model
	 * @generated
	 */
	boolean isMulti();

	/**
	 * Sets the value of the '{@link org.locationtech.geoff.interaction.Select#isMulti <em>Multi</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multi</em>' attribute.
	 * @see #isMulti()
	 * @generated
	 */
	void setMulti(boolean value);

} // Select
