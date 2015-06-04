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
package org.locationtech.geoff.layer;

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
 * @see org.locationtech.geoff.layer.LayerFactory
 * @model kind="package"
 * @generated
 */
public interface LayerPackage extends EPackage {
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
	String eNAME = "layer"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.locationtech.org/geoff-layer-v1"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "geoff.layer"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LayerPackage eINSTANCE = org.locationtech.geoff.layer.impl.LayerPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.layer.impl.LayerImpl <em>Layer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.layer.impl.LayerImpl
	 * @see org.locationtech.geoff.layer.impl.LayerPackageImpl#getLayer()
	 * @generated
	 */
	int LAYER = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__ID = GeoffPackage.IDENTIFIABLE__ID;

	/**
	 * The feature id for the '<em><b>Short Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__SHORT_DESCRIPTION = GeoffPackage.IDENTIFIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Long Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__LONG_DESCRIPTION = GeoffPackage.IDENTIFIABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__SOURCE = GeoffPackage.IDENTIFIABLE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Layer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_FEATURE_COUNT = GeoffPackage.IDENTIFIABLE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Layer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_OPERATION_COUNT = GeoffPackage.IDENTIFIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.layer.impl.TileLayerImpl <em>Tile Layer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.layer.impl.TileLayerImpl
	 * @see org.locationtech.geoff.layer.impl.LayerPackageImpl#getTileLayer()
	 * @generated
	 */
	int TILE_LAYER = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE_LAYER__ID = LAYER__ID;

	/**
	 * The feature id for the '<em><b>Short Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE_LAYER__SHORT_DESCRIPTION = LAYER__SHORT_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Long Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE_LAYER__LONG_DESCRIPTION = LAYER__LONG_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE_LAYER__SOURCE = LAYER__SOURCE;

	/**
	 * The number of structural features of the '<em>Tile Layer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE_LAYER_FEATURE_COUNT = LAYER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Tile Layer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE_LAYER_OPERATION_COUNT = LAYER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.layer.impl.VectorLayerImpl <em>Vector Layer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.layer.impl.VectorLayerImpl
	 * @see org.locationtech.geoff.layer.impl.LayerPackageImpl#getVectorLayer()
	 * @generated
	 */
	int VECTOR_LAYER = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VECTOR_LAYER__ID = LAYER__ID;

	/**
	 * The feature id for the '<em><b>Short Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VECTOR_LAYER__SHORT_DESCRIPTION = LAYER__SHORT_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Long Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VECTOR_LAYER__LONG_DESCRIPTION = LAYER__LONG_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VECTOR_LAYER__SOURCE = LAYER__SOURCE;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VECTOR_LAYER__STYLES = LAYER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Vector Layer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VECTOR_LAYER_FEATURE_COUNT = LAYER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Vector Layer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VECTOR_LAYER_OPERATION_COUNT = LAYER_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.layer.Layer <em>Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Layer</em>'.
	 * @see org.locationtech.geoff.layer.Layer
	 * @generated
	 */
	EClass getLayer();

	/**
	 * Returns the meta object for the containment reference '{@link org.locationtech.geoff.layer.Layer#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Source</em>'.
	 * @see org.locationtech.geoff.layer.Layer#getSource()
	 * @see #getLayer()
	 * @generated
	 */
	EReference getLayer_Source();

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.layer.TileLayer <em>Tile Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tile Layer</em>'.
	 * @see org.locationtech.geoff.layer.TileLayer
	 * @generated
	 */
	EClass getTileLayer();

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.layer.VectorLayer <em>Vector Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vector Layer</em>'.
	 * @see org.locationtech.geoff.layer.VectorLayer
	 * @generated
	 */
	EClass getVectorLayer();

	/**
	 * Returns the meta object for the map '{@link org.locationtech.geoff.layer.VectorLayer#getStyles <em>Styles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Styles</em>'.
	 * @see org.locationtech.geoff.layer.VectorLayer#getStyles()
	 * @see #getVectorLayer()
	 * @generated
	 */
	EReference getVectorLayer_Styles();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	LayerFactory getLayerFactory();

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
		 * The meta object literal for the '{@link org.locationtech.geoff.layer.impl.LayerImpl <em>Layer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.layer.impl.LayerImpl
		 * @see org.locationtech.geoff.layer.impl.LayerPackageImpl#getLayer()
		 * @generated
		 */
		EClass LAYER = eINSTANCE.getLayer();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LAYER__SOURCE = eINSTANCE.getLayer_Source();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.layer.impl.TileLayerImpl <em>Tile Layer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.layer.impl.TileLayerImpl
		 * @see org.locationtech.geoff.layer.impl.LayerPackageImpl#getTileLayer()
		 * @generated
		 */
		EClass TILE_LAYER = eINSTANCE.getTileLayer();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.layer.impl.VectorLayerImpl <em>Vector Layer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.layer.impl.VectorLayerImpl
		 * @see org.locationtech.geoff.layer.impl.LayerPackageImpl#getVectorLayer()
		 * @generated
		 */
		EClass VECTOR_LAYER = eINSTANCE.getVectorLayer();

		/**
		 * The meta object literal for the '<em><b>Styles</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VECTOR_LAYER__STYLES = eINSTANCE.getVectorLayer_Styles();

	}

} //LayerPackage
