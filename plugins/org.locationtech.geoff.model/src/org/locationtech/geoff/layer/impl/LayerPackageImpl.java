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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.locationtech.geoff.GeoffPackage;
import org.locationtech.geoff.geom.GeomPackage;
import org.locationtech.geoff.geom.impl.GeomPackageImpl;
import org.locationtech.geoff.impl.GeoffPackageImpl;
import org.locationtech.geoff.layer.Layer;
import org.locationtech.geoff.layer.LayerFactory;
import org.locationtech.geoff.layer.LayerPackage;
import org.locationtech.geoff.layer.TileLayer;
import org.locationtech.geoff.layer.VectorLayer;
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
public class LayerPackageImpl extends EPackageImpl implements LayerPackage {
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
	private EClass layerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tileLayerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vectorLayerEClass = null;

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
	 * @see org.locationtech.geoff.layer.LayerPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private LayerPackageImpl() {
		super(eNS_URI, LayerFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link LayerPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static LayerPackage init() {
		if (isInited)
			return (LayerPackage) EPackage.Registry.INSTANCE.getEPackage(LayerPackage.eNS_URI);

		// Obtain or create and register package
		LayerPackageImpl theLayerPackage = (LayerPackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof LayerPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
						: new LayerPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		GeoffPackageImpl theGeoffPackage = (GeoffPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(GeoffPackage.eNS_URI) instanceof GeoffPackageImpl
						? EPackage.Registry.INSTANCE.getEPackage(GeoffPackage.eNS_URI) : GeoffPackage.eINSTANCE);
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
		theLayerPackage.createPackageContents();
		theGeoffPackage.createPackageContents();
		theSourcePackage.createPackageContents();
		theGeomPackage.createPackageContents();
		theStylePackage.createPackageContents();

		// Initialize created meta-data
		theLayerPackage.initializePackageContents();
		theGeoffPackage.initializePackageContents();
		theSourcePackage.initializePackageContents();
		theGeomPackage.initializePackageContents();
		theStylePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theLayerPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(LayerPackage.eNS_URI, theLayerPackage);
		return theLayerPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLayer() {
		return layerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLayer_Source() {
		return (EReference) layerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTileLayer() {
		return tileLayerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVectorLayer() {
		return vectorLayerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVectorLayer_Styles() {
		return (EReference) vectorLayerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LayerFactory getLayerFactory() {
		return (LayerFactory) getEFactoryInstance();
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
		layerEClass = createEClass(LAYER);
		createEReference(layerEClass, LAYER__SOURCE);

		tileLayerEClass = createEClass(TILE_LAYER);

		vectorLayerEClass = createEClass(VECTOR_LAYER);
		createEReference(vectorLayerEClass, VECTOR_LAYER__STYLES);
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
		SourcePackage theSourcePackage = (SourcePackage) EPackage.Registry.INSTANCE.getEPackage(SourcePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		layerEClass.getESuperTypes().add(theGeoffPackage.getIdentifiable());
		layerEClass.getESuperTypes().add(theGeoffPackage.getDescriptive());
		tileLayerEClass.getESuperTypes().add(this.getLayer());
		vectorLayerEClass.getESuperTypes().add(this.getLayer());

		// Initialize classes, features, and operations; add parameters
		initEClass(layerEClass, Layer.class, "Layer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLayer_Source(), theSourcePackage.getSource(), null, "source", null, 1, 1, Layer.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tileLayerEClass, TileLayer.class, "TileLayer", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(vectorLayerEClass, VectorLayer.class, "VectorLayer", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVectorLayer_Styles(), theGeoffPackage.getStyleEntry(), null, "styles", null, 0, -1, //$NON-NLS-1$
				VectorLayer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData"; //$NON-NLS-1$	
		addAnnotation(getVectorLayer_Styles(), source, new String[] { "name", "style", //$NON-NLS-1$ //$NON-NLS-2$
				"kind", "element" //$NON-NLS-1$ //$NON-NLS-2$
		});
	}

} //LayerPackageImpl
