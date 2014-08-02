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
	LayerPackage eINSTANCE = org.locationtech.geoff.layer.impl.LayerPackageImpl
			.init();

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
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__SOURCE = GeoffPackage.IDENTIFIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Layer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_FEATURE_COUNT = GeoffPackage.IDENTIFIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Layer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_OPERATION_COUNT = GeoffPackage.IDENTIFIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.layer.impl.TileImpl <em>Tile</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.layer.impl.TileImpl
	 * @see org.locationtech.geoff.layer.impl.LayerPackageImpl#getTile()
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
	int TILE__ID = LAYER__ID;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE__SOURCE = LAYER__SOURCE;

	/**
	 * The number of structural features of the '<em>Tile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE_FEATURE_COUNT = LAYER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Tile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TILE_OPERATION_COUNT = LAYER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.layer.impl.VectorImpl <em>Vector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.layer.impl.VectorImpl
	 * @see org.locationtech.geoff.layer.impl.LayerPackageImpl#getVector()
	 * @generated
	 */
	int VECTOR = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VECTOR__ID = LAYER__ID;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VECTOR__SOURCE = LAYER__SOURCE;

	/**
	 * The number of structural features of the '<em>Vector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VECTOR_FEATURE_COUNT = LAYER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Vector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VECTOR_OPERATION_COUNT = LAYER_OPERATION_COUNT + 0;

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
	 * Returns the meta object for class '{@link org.locationtech.geoff.layer.Tile <em>Tile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tile</em>'.
	 * @see org.locationtech.geoff.layer.Tile
	 * @generated
	 */
	EClass getTile();

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.layer.Vector <em>Vector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vector</em>'.
	 * @see org.locationtech.geoff.layer.Vector
	 * @generated
	 */
	EClass getVector();

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
		 * The meta object literal for the '{@link org.locationtech.geoff.layer.impl.TileImpl <em>Tile</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.layer.impl.TileImpl
		 * @see org.locationtech.geoff.layer.impl.LayerPackageImpl#getTile()
		 * @generated
		 */
		EClass TILE = eINSTANCE.getTile();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.layer.impl.VectorImpl <em>Vector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.layer.impl.VectorImpl
		 * @see org.locationtech.geoff.layer.impl.LayerPackageImpl#getVector()
		 * @generated
		 */
		EClass VECTOR = eINSTANCE.getVector();

	}

} //LayerPackage
