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
package org.locationtech.geoff.interaction.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.locationtech.geoff.GeoffPackage;

import org.locationtech.geoff.geom.GeomPackage;

import org.locationtech.geoff.geom.impl.GeomPackageImpl;

import org.locationtech.geoff.impl.GeoffPackageImpl;

import org.locationtech.geoff.interaction.EventCondition;
import org.locationtech.geoff.interaction.Interaction;
import org.locationtech.geoff.interaction.InteractionFactory;
import org.locationtech.geoff.interaction.InteractionPackage;
import org.locationtech.geoff.interaction.Select;

import org.locationtech.geoff.layer.LayerPackage;

import org.locationtech.geoff.layer.impl.LayerPackageImpl;

import org.locationtech.geoff.source.SourcePackage;

import org.locationtech.geoff.source.impl.SourcePackageImpl;

import org.locationtech.geoff.style.StylePackage;

import org.locationtech.geoff.style.impl.StylePackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class InteractionPackageImpl extends EPackageImpl implements InteractionPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = " Copyright (c) 2014 Erdal Karaca.\r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n \r\n Contributors:\r\n     Erdal Karaca - initial API and implementation\r\n"; //$NON-NLS-1$

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interactionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass selectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eventConditionEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.locationtech.geoff.interaction.InteractionPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private InteractionPackageImpl() {
		super(eNS_URI, InteractionFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link InteractionPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static InteractionPackage init() {
		if (isInited)
			return (InteractionPackage) EPackage.Registry.INSTANCE.getEPackage(InteractionPackage.eNS_URI);

		// Obtain or create and register package
		InteractionPackageImpl theInteractionPackage = (InteractionPackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof InteractionPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
						: new InteractionPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		GeoffPackageImpl theGeoffPackage = (GeoffPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(GeoffPackage.eNS_URI) instanceof GeoffPackageImpl
						? EPackage.Registry.INSTANCE.getEPackage(GeoffPackage.eNS_URI) : GeoffPackage.eINSTANCE);
		LayerPackageImpl theLayerPackage = (LayerPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(LayerPackage.eNS_URI) instanceof LayerPackageImpl
						? EPackage.Registry.INSTANCE.getEPackage(LayerPackage.eNS_URI) : LayerPackage.eINSTANCE);
		SourcePackageImpl theSourcePackage = (SourcePackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(SourcePackage.eNS_URI) instanceof SourcePackageImpl
						? EPackage.Registry.INSTANCE.getEPackage(SourcePackage.eNS_URI) : SourcePackage.eINSTANCE);
		GeomPackageImpl theGeomPackage = (GeomPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(GeomPackage.eNS_URI) instanceof GeomPackageImpl
						? EPackage.Registry.INSTANCE.getEPackage(GeomPackage.eNS_URI) : GeomPackage.eINSTANCE);
		StylePackageImpl theStylePackage = (StylePackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(StylePackage.eNS_URI) instanceof StylePackageImpl
						? EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI) : StylePackage.eINSTANCE);

		// Create package meta-data objects
		theInteractionPackage.createPackageContents();
		theGeoffPackage.createPackageContents();
		theLayerPackage.createPackageContents();
		theSourcePackage.createPackageContents();
		theGeomPackage.createPackageContents();
		theStylePackage.createPackageContents();

		// Initialize created meta-data
		theInteractionPackage.initializePackageContents();
		theGeoffPackage.initializePackageContents();
		theLayerPackage.initializePackageContents();
		theSourcePackage.initializePackageContents();
		theGeomPackage.initializePackageContents();
		theStylePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theInteractionPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(InteractionPackage.eNS_URI, theInteractionPackage);
		return theInteractionPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInteraction() {
		return interactionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSelect() {
		return selectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSelect_Condition() {
		return (EAttribute) selectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSelect_Multi() {
		return (EAttribute) selectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEventCondition() {
		return eventConditionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InteractionFactory getInteractionFactory() {
		return (InteractionFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		interactionEClass = createEClass(INTERACTION);

		selectEClass = createEClass(SELECT);
		createEAttribute(selectEClass, SELECT__CONDITION);
		createEAttribute(selectEClass, SELECT__MULTI);

		// Create enums
		eventConditionEEnum = createEEnum(EVENT_CONDITION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		GeoffPackage theGeoffPackage = (GeoffPackage) EPackage.Registry.INSTANCE.getEPackage(GeoffPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		interactionEClass.getESuperTypes().add(theGeoffPackage.getIdentifiable());
		selectEClass.getESuperTypes().add(this.getInteraction());

		// Initialize classes, features, and operations; add parameters
		initEClass(interactionEClass, Interaction.class, "Interaction", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(selectEClass, Select.class, "Select", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSelect_Condition(), this.getEventCondition(), "condition", null, 1, 1, Select.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSelect_Multi(), ecorePackage.getEBoolean(), "multi", null, 0, 1, Select.class, !IS_TRANSIENT, //$NON-NLS-1$
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(eventConditionEEnum, EventCondition.class, "EventCondition"); //$NON-NLS-1$
		addEEnumLiteral(eventConditionEEnum, EventCondition.SINGLE_CLICK);
		addEEnumLiteral(eventConditionEEnum, EventCondition.CLICK);
		addEEnumLiteral(eventConditionEEnum, EventCondition.HOVER);
	}

} //InteractionPackageImpl
