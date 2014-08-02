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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.locationtech.geoff.GeoffPackage;

import org.locationtech.geoff.geom.GeomPackage;

import org.locationtech.geoff.geom.impl.GeomPackageImpl;

import org.locationtech.geoff.impl.GeoffPackageImpl;

import org.locationtech.geoff.layer.LayerPackage;

import org.locationtech.geoff.layer.impl.LayerPackageImpl;

import org.locationtech.geoff.source.SourcePackage;

import org.locationtech.geoff.source.impl.SourcePackageImpl;

import org.locationtech.geoff.style.Icon;
import org.locationtech.geoff.style.Image;
import org.locationtech.geoff.style.Style;
import org.locationtech.geoff.style.StyleFactory;
import org.locationtech.geoff.style.StylePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StylePackageImpl extends EPackageImpl implements StylePackage {
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
	private EClass styleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass imageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iconEClass = null;

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
	 * @see org.locationtech.geoff.style.StylePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private StylePackageImpl() {
		super(eNS_URI, StyleFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link StylePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static StylePackage init() {
		if (isInited)
			return (StylePackage) EPackage.Registry.INSTANCE
					.getEPackage(StylePackage.eNS_URI);

		// Obtain or create and register package
		StylePackageImpl theStylePackage = (StylePackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof StylePackageImpl ? EPackage.Registry.INSTANCE
				.get(eNS_URI) : new StylePackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		GeoffPackageImpl theGeoffPackage = (GeoffPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(GeoffPackage.eNS_URI) instanceof GeoffPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(GeoffPackage.eNS_URI) : GeoffPackage.eINSTANCE);
		LayerPackageImpl theLayerPackage = (LayerPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(LayerPackage.eNS_URI) instanceof LayerPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(LayerPackage.eNS_URI) : LayerPackage.eINSTANCE);
		SourcePackageImpl theSourcePackage = (SourcePackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(SourcePackage.eNS_URI) instanceof SourcePackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(SourcePackage.eNS_URI) : SourcePackage.eINSTANCE);
		GeomPackageImpl theGeomPackage = (GeomPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(GeomPackage.eNS_URI) instanceof GeomPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(GeomPackage.eNS_URI) : GeomPackage.eINSTANCE);

		// Create package meta-data objects
		theStylePackage.createPackageContents();
		theGeoffPackage.createPackageContents();
		theLayerPackage.createPackageContents();
		theSourcePackage.createPackageContents();
		theGeomPackage.createPackageContents();

		// Initialize created meta-data
		theStylePackage.initializePackageContents();
		theGeoffPackage.initializePackageContents();
		theLayerPackage.initializePackageContents();
		theSourcePackage.initializePackageContents();
		theGeomPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theStylePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(StylePackage.eNS_URI, theStylePackage);
		return theStylePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStyle() {
		return styleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStyle_Image() {
		return (EReference) styleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImage() {
		return imageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIcon() {
		return iconEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIcon_Src() {
		return (EAttribute) iconEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StyleFactory getStyleFactory() {
		return (StyleFactory) getEFactoryInstance();
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
		styleEClass = createEClass(STYLE);
		createEReference(styleEClass, STYLE__IMAGE);

		imageEClass = createEClass(IMAGE);

		iconEClass = createEClass(ICON);
		createEAttribute(iconEClass, ICON__SRC);
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
		GeoffPackage theGeoffPackage = (GeoffPackage) EPackage.Registry.INSTANCE
				.getEPackage(GeoffPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		styleEClass.getESuperTypes().add(theGeoffPackage.getIdentifiable());
		imageEClass.getESuperTypes().add(theGeoffPackage.getIdentifiable());
		iconEClass.getESuperTypes().add(this.getImage());

		// Initialize classes, features, and operations; add parameters
		initEClass(
				styleEClass,
				Style.class,
				"Style", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getStyle_Image(),
				this.getImage(),
				null,
				"image", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				imageEClass,
				Image.class,
				"Image", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				iconEClass,
				Icon.class,
				"Icon", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getIcon_Src(),
				ecorePackage.getEString(),
				"src", null, 1, 1, Icon.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	}

} //StylePackageImpl
