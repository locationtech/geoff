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
package org.locationtech.geoff.geom.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.locationtech.geoff.GeoffPackage;

import org.locationtech.geoff.geom.GeomFactory;
import org.locationtech.geoff.geom.GeomPackage;
import org.locationtech.geoff.geom.Geometry;
import org.locationtech.geoff.geom.Point;
import org.locationtech.geoff.geom.SimpleGeometry;

import org.locationtech.geoff.impl.GeoffPackageImpl;

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
public class GeomPackageImpl extends EPackageImpl implements GeomPackage {
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
	private EClass geometryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass simpleGeometryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pointEClass = null;

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
	 * @see org.locationtech.geoff.geom.GeomPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private GeomPackageImpl() {
		super(eNS_URI, GeomFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link GeomPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static GeomPackage init() {
		if (isInited)
			return (GeomPackage) EPackage.Registry.INSTANCE
					.getEPackage(GeomPackage.eNS_URI);

		// Obtain or create and register package
		GeomPackageImpl theGeomPackage = (GeomPackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof GeomPackageImpl ? EPackage.Registry.INSTANCE
				.get(eNS_URI) : new GeomPackageImpl());

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
		StylePackageImpl theStylePackage = (StylePackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(StylePackage.eNS_URI) instanceof StylePackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(StylePackage.eNS_URI) : StylePackage.eINSTANCE);

		// Create package meta-data objects
		theGeomPackage.createPackageContents();
		theGeoffPackage.createPackageContents();
		theLayerPackage.createPackageContents();
		theSourcePackage.createPackageContents();
		theStylePackage.createPackageContents();

		// Initialize created meta-data
		theGeomPackage.initializePackageContents();
		theGeoffPackage.initializePackageContents();
		theLayerPackage.initializePackageContents();
		theSourcePackage.initializePackageContents();
		theStylePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theGeomPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(GeomPackage.eNS_URI, theGeomPackage);
		return theGeomPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGeometry() {
		return geometryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSimpleGeometry() {
		return simpleGeometryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPoint() {
		return pointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPoint_Coordinates() {
		return (EReference) pointEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeomFactory getGeomFactory() {
		return (GeomFactory) getEFactoryInstance();
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
		geometryEClass = createEClass(GEOMETRY);

		simpleGeometryEClass = createEClass(SIMPLE_GEOMETRY);

		pointEClass = createEClass(POINT);
		createEReference(pointEClass, POINT__COORDINATES);
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
		geometryEClass.getESuperTypes().add(theGeoffPackage.getIdentifiable());
		simpleGeometryEClass.getESuperTypes().add(this.getGeometry());
		pointEClass.getESuperTypes().add(this.getSimpleGeometry());

		// Initialize classes, features, and operations; add parameters
		initEClass(
				geometryEClass,
				Geometry.class,
				"Geometry", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				simpleGeometryEClass,
				SimpleGeometry.class,
				"SimpleGeometry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				pointEClass,
				Point.class,
				"Point", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getPoint_Coordinates(),
				theGeoffPackage.getLocation(),
				null,
				"coordinates", null, 1, 1, Point.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	}

} //GeomPackageImpl
