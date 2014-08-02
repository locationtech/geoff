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
package org.locationtech.geoff.source;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.locationtech.geoff.GeoffPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.locationtech.geoff.source.SourceFactory
 * @model kind="package"
 * @generated
 */
public interface SourcePackage extends EPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = " Copyright (c) 2014 Erdal Karaca.\r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n \r\n Contributors:\r\n     Erdal Karaca - initial API and implementation\r\n"; //$NON-NLS-1$

	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "source"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.locationtech.org/geoff-source-v1"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "geoff.source"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SourcePackage eINSTANCE = org.locationtech.geoff.source.impl.SourcePackageImpl
			.init();

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.source.impl.SourceImpl <em>Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.source.impl.SourceImpl
	 * @see org.locationtech.geoff.source.impl.SourcePackageImpl#getSource()
	 * @generated
	 */
	int SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__ID = GeoffPackage.IDENTIFIABLE__ID;

	/**
	 * The number of structural features of the '<em>Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_FEATURE_COUNT = GeoffPackage.IDENTIFIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_OPERATION_COUNT = GeoffPackage.IDENTIFIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.source.impl.TileImpl <em>Tile</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.source.impl.TileImpl
	 * @see org.locationtech.geoff.source.impl.SourcePackageImpl#getTile()
	 * @generated
	 */
	int TILE = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE__ID = SOURCE__ID;

	/**
	 * The number of structural features of the '<em>Tile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE_FEATURE_COUNT = SOURCE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Tile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE_OPERATION_COUNT = SOURCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.source.impl.TileImageImpl <em>Tile Image</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.source.impl.TileImageImpl
	 * @see org.locationtech.geoff.source.impl.SourcePackageImpl#getTileImage()
	 * @generated
	 */
	int TILE_IMAGE = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE_IMAGE__ID = TILE__ID;

	/**
	 * The number of structural features of the '<em>Tile Image</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE_IMAGE_FEATURE_COUNT = TILE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Tile Image</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE_IMAGE_OPERATION_COUNT = TILE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.source.impl.XYZImpl <em>XYZ</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.source.impl.XYZImpl
	 * @see org.locationtech.geoff.source.impl.SourcePackageImpl#getXYZ()
	 * @generated
	 */
	int XYZ = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XYZ__ID = TILE_IMAGE__ID;

	/**
	 * The number of structural features of the '<em>XYZ</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XYZ_FEATURE_COUNT = TILE_IMAGE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>XYZ</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XYZ_OPERATION_COUNT = TILE_IMAGE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.source.impl.OSMImpl <em>OSM</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.source.impl.OSMImpl
	 * @see org.locationtech.geoff.source.impl.SourcePackageImpl#getOSM()
	 * @generated
	 */
	int OSM = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OSM__ID = XYZ__ID;

	/**
	 * The number of structural features of the '<em>OSM</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OSM_FEATURE_COUNT = XYZ_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>OSM</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OSM_OPERATION_COUNT = XYZ_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.source.impl.MapQuestImpl <em>Map Quest</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.source.impl.MapQuestImpl
	 * @see org.locationtech.geoff.source.impl.SourcePackageImpl#getMapQuest()
	 * @generated
	 */
	int MAP_QUEST = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_QUEST__ID = XYZ__ID;

	/**
	 * The feature id for the '<em><b>Layer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_QUEST__LAYER = XYZ_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Map Quest</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_QUEST_FEATURE_COUNT = XYZ_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Map Quest</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_QUEST_OPERATION_COUNT = XYZ_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.source.impl.BingMapsImpl <em>Bing Maps</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.source.impl.BingMapsImpl
	 * @see org.locationtech.geoff.source.impl.SourcePackageImpl#getBingMaps()
	 * @generated
	 */
	int BING_MAPS = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BING_MAPS__ID = XYZ__ID;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BING_MAPS__KEY = XYZ_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Imagery Set</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BING_MAPS__IMAGERY_SET = XYZ_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Bing Maps</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BING_MAPS_FEATURE_COUNT = XYZ_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Bing Maps</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BING_MAPS_OPERATION_COUNT = XYZ_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.source.impl.VectorImpl <em>Vector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.source.impl.VectorImpl
	 * @see org.locationtech.geoff.source.impl.SourcePackageImpl#getVector()
	 * @generated
	 */
	int VECTOR = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VECTOR__ID = SOURCE__ID;

	/**
	 * The feature id for the '<em><b>Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VECTOR__FEATURES = SOURCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Vector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VECTOR_FEATURE_COUNT = SOURCE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Vector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VECTOR_OPERATION_COUNT = SOURCE_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.source.Source <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Source</em>'.
	 * @see org.locationtech.geoff.source.Source
	 * @generated
	 */
	EClass getSource();

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.source.Tile <em>Tile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tile</em>'.
	 * @see org.locationtech.geoff.source.Tile
	 * @generated
	 */
	EClass getTile();

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.source.TileImage <em>Tile Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tile Image</em>'.
	 * @see org.locationtech.geoff.source.TileImage
	 * @generated
	 */
	EClass getTileImage();

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.source.XYZ <em>XYZ</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>XYZ</em>'.
	 * @see org.locationtech.geoff.source.XYZ
	 * @generated
	 */
	EClass getXYZ();

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.source.OSM <em>OSM</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>OSM</em>'.
	 * @see org.locationtech.geoff.source.OSM
	 * @generated
	 */
	EClass getOSM();

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.source.MapQuest <em>Map Quest</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map Quest</em>'.
	 * @see org.locationtech.geoff.source.MapQuest
	 * @generated
	 */
	EClass getMapQuest();

	/**
	 * Returns the meta object for the attribute '{@link org.locationtech.geoff.source.MapQuest#getLayer <em>Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Layer</em>'.
	 * @see org.locationtech.geoff.source.MapQuest#getLayer()
	 * @see #getMapQuest()
	 * @generated
	 */
	EAttribute getMapQuest_Layer();

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.source.BingMaps <em>Bing Maps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bing Maps</em>'.
	 * @see org.locationtech.geoff.source.BingMaps
	 * @generated
	 */
	EClass getBingMaps();

	/**
	 * Returns the meta object for the attribute '{@link org.locationtech.geoff.source.BingMaps#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see org.locationtech.geoff.source.BingMaps#getKey()
	 * @see #getBingMaps()
	 * @generated
	 */
	EAttribute getBingMaps_Key();

	/**
	 * Returns the meta object for the attribute '{@link org.locationtech.geoff.source.BingMaps#getImagerySet <em>Imagery Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Imagery Set</em>'.
	 * @see org.locationtech.geoff.source.BingMaps#getImagerySet()
	 * @see #getBingMaps()
	 * @generated
	 */
	EAttribute getBingMaps_ImagerySet();

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.source.Vector <em>Vector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vector</em>'.
	 * @see org.locationtech.geoff.source.Vector
	 * @generated
	 */
	EClass getVector();

	/**
	 * Returns the meta object for the containment reference list '{@link org.locationtech.geoff.source.Vector#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Features</em>'.
	 * @see org.locationtech.geoff.source.Vector#getFeatures()
	 * @see #getVector()
	 * @generated
	 */
	EReference getVector_Features();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SourceFactory getSourceFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.source.impl.SourceImpl <em>Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.source.impl.SourceImpl
		 * @see org.locationtech.geoff.source.impl.SourcePackageImpl#getSource()
		 * @generated
		 */
		EClass SOURCE = eINSTANCE.getSource();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.source.impl.TileImpl <em>Tile</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.source.impl.TileImpl
		 * @see org.locationtech.geoff.source.impl.SourcePackageImpl#getTile()
		 * @generated
		 */
		EClass TILE = eINSTANCE.getTile();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.source.impl.TileImageImpl <em>Tile Image</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.source.impl.TileImageImpl
		 * @see org.locationtech.geoff.source.impl.SourcePackageImpl#getTileImage()
		 * @generated
		 */
		EClass TILE_IMAGE = eINSTANCE.getTileImage();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.source.impl.XYZImpl <em>XYZ</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.source.impl.XYZImpl
		 * @see org.locationtech.geoff.source.impl.SourcePackageImpl#getXYZ()
		 * @generated
		 */
		EClass XYZ = eINSTANCE.getXYZ();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.source.impl.OSMImpl <em>OSM</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.source.impl.OSMImpl
		 * @see org.locationtech.geoff.source.impl.SourcePackageImpl#getOSM()
		 * @generated
		 */
		EClass OSM = eINSTANCE.getOSM();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.source.impl.MapQuestImpl <em>Map Quest</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.source.impl.MapQuestImpl
		 * @see org.locationtech.geoff.source.impl.SourcePackageImpl#getMapQuest()
		 * @generated
		 */
		EClass MAP_QUEST = eINSTANCE.getMapQuest();

		/**
		 * The meta object literal for the '<em><b>Layer</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAP_QUEST__LAYER = eINSTANCE.getMapQuest_Layer();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.source.impl.BingMapsImpl <em>Bing Maps</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.source.impl.BingMapsImpl
		 * @see org.locationtech.geoff.source.impl.SourcePackageImpl#getBingMaps()
		 * @generated
		 */
		EClass BING_MAPS = eINSTANCE.getBingMaps();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BING_MAPS__KEY = eINSTANCE.getBingMaps_Key();

		/**
		 * The meta object literal for the '<em><b>Imagery Set</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BING_MAPS__IMAGERY_SET = eINSTANCE.getBingMaps_ImagerySet();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.source.impl.VectorImpl <em>Vector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.source.impl.VectorImpl
		 * @see org.locationtech.geoff.source.impl.SourcePackageImpl#getVector()
		 * @generated
		 */
		EClass VECTOR = eINSTANCE.getVector();

		/**
		 * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VECTOR__FEATURES = eINSTANCE.getVector_Features();

	}

} //SourcePackage
