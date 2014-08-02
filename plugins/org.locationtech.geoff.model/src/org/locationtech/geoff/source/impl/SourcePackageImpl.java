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
package org.locationtech.geoff.source.impl;

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
import org.locationtech.geoff.source.BingMaps;
import org.locationtech.geoff.source.MapQuest;
import org.locationtech.geoff.source.Source;
import org.locationtech.geoff.source.SourceFactory;
import org.locationtech.geoff.source.SourcePackage;
import org.locationtech.geoff.source.Tile;
import org.locationtech.geoff.source.TileImage;
import org.locationtech.geoff.source.Vector;
import org.locationtech.geoff.style.StylePackage;
import org.locationtech.geoff.style.impl.StylePackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SourcePackageImpl extends EPackageImpl implements SourcePackage {
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
	private EClass sourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tileImageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass xyzEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass osmEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mapQuestEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bingMapsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vectorEClass = null;

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
	 * @see org.locationtech.geoff.source.SourcePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SourcePackageImpl() {
		super(eNS_URI, SourceFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link SourcePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SourcePackage init() {
		if (isInited)
			return (SourcePackage) EPackage.Registry.INSTANCE
					.getEPackage(SourcePackage.eNS_URI);

		// Obtain or create and register package
		SourcePackageImpl theSourcePackage = (SourcePackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof SourcePackageImpl ? EPackage.Registry.INSTANCE
				.get(eNS_URI) : new SourcePackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		GeoffPackageImpl theGeoffPackage = (GeoffPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(GeoffPackage.eNS_URI) instanceof GeoffPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(GeoffPackage.eNS_URI) : GeoffPackage.eINSTANCE);
		LayerPackageImpl theLayerPackage = (LayerPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(LayerPackage.eNS_URI) instanceof LayerPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(LayerPackage.eNS_URI) : LayerPackage.eINSTANCE);
		GeomPackageImpl theGeomPackage = (GeomPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(GeomPackage.eNS_URI) instanceof GeomPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(GeomPackage.eNS_URI) : GeomPackage.eINSTANCE);
		StylePackageImpl theStylePackage = (StylePackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(StylePackage.eNS_URI) instanceof StylePackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(StylePackage.eNS_URI) : StylePackage.eINSTANCE);

		// Create package meta-data objects
		theSourcePackage.createPackageContents();
		theGeoffPackage.createPackageContents();
		theLayerPackage.createPackageContents();
		theGeomPackage.createPackageContents();
		theStylePackage.createPackageContents();

		// Initialize created meta-data
		theSourcePackage.initializePackageContents();
		theGeoffPackage.initializePackageContents();
		theLayerPackage.initializePackageContents();
		theGeomPackage.initializePackageContents();
		theStylePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSourcePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SourcePackage.eNS_URI, theSourcePackage);
		return theSourcePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSource() {
		return sourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTile() {
		return tileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTileImage() {
		return tileImageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getXYZ() {
		return xyzEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOSM() {
		return osmEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMapQuest() {
		return mapQuestEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMapQuest_Layer() {
		return (EAttribute) mapQuestEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBingMaps() {
		return bingMapsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBingMaps_Key() {
		return (EAttribute) bingMapsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBingMaps_ImagerySet() {
		return (EAttribute) bingMapsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVector() {
		return vectorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVector_Features() {
		return (EReference) vectorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourceFactory getSourceFactory() {
		return (SourceFactory) getEFactoryInstance();
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
		sourceEClass = createEClass(SOURCE);

		tileEClass = createEClass(TILE);

		tileImageEClass = createEClass(TILE_IMAGE);

		xyzEClass = createEClass(XYZ);

		osmEClass = createEClass(OSM);

		mapQuestEClass = createEClass(MAP_QUEST);
		createEAttribute(mapQuestEClass, MAP_QUEST__LAYER);

		bingMapsEClass = createEClass(BING_MAPS);
		createEAttribute(bingMapsEClass, BING_MAPS__KEY);
		createEAttribute(bingMapsEClass, BING_MAPS__IMAGERY_SET);

		vectorEClass = createEClass(VECTOR);
		createEReference(vectorEClass, VECTOR__FEATURES);
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
		sourceEClass.getESuperTypes().add(theGeoffPackage.getIdentifiable());
		tileEClass.getESuperTypes().add(this.getSource());
		tileImageEClass.getESuperTypes().add(this.getTile());
		xyzEClass.getESuperTypes().add(this.getTileImage());
		osmEClass.getESuperTypes().add(this.getXYZ());
		mapQuestEClass.getESuperTypes().add(this.getXYZ());
		bingMapsEClass.getESuperTypes().add(this.getXYZ());
		vectorEClass.getESuperTypes().add(this.getSource());

		// Initialize classes, features, and operations; add parameters
		initEClass(
				sourceEClass,
				Source.class,
				"Source", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(tileEClass, Tile.class,
				"Tile", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				tileImageEClass,
				TileImage.class,
				"TileImage", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(xyzEClass, org.locationtech.geoff.source.XYZ.class,
				"XYZ", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(osmEClass, org.locationtech.geoff.source.OSM.class,
				"OSM", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				mapQuestEClass,
				MapQuest.class,
				"MapQuest", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getMapQuest_Layer(),
				ecorePackage.getEString(),
				"layer", "osm", 0, 1, MapQuest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(
				bingMapsEClass,
				BingMaps.class,
				"BingMaps", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getBingMaps_Key(),
				ecorePackage.getEString(),
				"key", null, 1, 1, BingMaps.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getBingMaps_ImagerySet(),
				ecorePackage.getEString(),
				"imagerySet", null, 1, 1, BingMaps.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				vectorEClass,
				Vector.class,
				"Vector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getVector_Features(),
				theGeoffPackage.getFeature(),
				null,
				"features", null, 0, -1, Vector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

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
		addAnnotation(getVector_Features(), source, new String[] {
				"name", "feature", //$NON-NLS-1$ //$NON-NLS-2$
				"kind", "element" //$NON-NLS-1$ //$NON-NLS-2$
		});
	}

} //SourcePackageImpl
