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
package org.locationtech.geoff.geom;

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
 * @see org.locationtech.geoff.geom.GeomFactory
 * @model kind="package"
 * @generated
 */
public interface GeomPackage extends EPackage {
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
	String eNAME = "geom"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.locationtech.org/geoff-geom-v1"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "geoff.geom"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	GeomPackage eINSTANCE = org.locationtech.geoff.geom.impl.GeomPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.geom.impl.GeometryImpl <em>Geometry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.geom.impl.GeometryImpl
	 * @see org.locationtech.geoff.geom.impl.GeomPackageImpl#getGeometry()
	 * @generated
	 */
	int GEOMETRY = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY__ID = GeoffPackage.IDENTIFIABLE__ID;

	/**
	 * The number of structural features of the '<em>Geometry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY_FEATURE_COUNT = GeoffPackage.IDENTIFIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Geometry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY_OPERATION_COUNT = GeoffPackage.IDENTIFIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.geom.impl.SimpleGeometryImpl <em>Simple Geometry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.geom.impl.SimpleGeometryImpl
	 * @see org.locationtech.geoff.geom.impl.GeomPackageImpl#getSimpleGeometry()
	 * @generated
	 */
	int SIMPLE_GEOMETRY = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_GEOMETRY__ID = GEOMETRY__ID;

	/**
	 * The number of structural features of the '<em>Simple Geometry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_GEOMETRY_FEATURE_COUNT = GEOMETRY_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Simple Geometry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_GEOMETRY_OPERATION_COUNT = GEOMETRY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.geom.impl.PointImpl <em>Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.geom.impl.PointImpl
	 * @see org.locationtech.geoff.geom.impl.GeomPackageImpl#getPoint()
	 * @generated
	 */
	int POINT = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT__ID = SIMPLE_GEOMETRY__ID;

	/**
	 * The feature id for the '<em><b>Coordinates</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT__COORDINATES = SIMPLE_GEOMETRY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_FEATURE_COUNT = SIMPLE_GEOMETRY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_OPERATION_COUNT = SIMPLE_GEOMETRY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.geom.impl.LineStringImpl <em>Line String</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.geom.impl.LineStringImpl
	 * @see org.locationtech.geoff.geom.impl.GeomPackageImpl#getLineString()
	 * @generated
	 */
	int LINE_STRING = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_STRING__ID = SIMPLE_GEOMETRY__ID;

	/**
	 * The feature id for the '<em><b>Coordinates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_STRING__COORDINATES = SIMPLE_GEOMETRY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Line String</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_STRING_FEATURE_COUNT = SIMPLE_GEOMETRY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Line String</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_STRING_OPERATION_COUNT = SIMPLE_GEOMETRY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.geom.impl.PolygonImpl <em>Polygon</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.geom.impl.PolygonImpl
	 * @see org.locationtech.geoff.geom.impl.GeomPackageImpl#getPolygon()
	 * @generated
	 */
	int POLYGON = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__ID = SIMPLE_GEOMETRY__ID;

	/**
	 * The feature id for the '<em><b>Coordinates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON__COORDINATES = SIMPLE_GEOMETRY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Polygon</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON_FEATURE_COUNT = SIMPLE_GEOMETRY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Polygon</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYGON_OPERATION_COUNT = SIMPLE_GEOMETRY_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.geom.Geometry <em>Geometry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Geometry</em>'.
	 * @see org.locationtech.geoff.geom.Geometry
	 * @generated
	 */
	EClass getGeometry();

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.geom.SimpleGeometry <em>Simple Geometry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simple Geometry</em>'.
	 * @see org.locationtech.geoff.geom.SimpleGeometry
	 * @generated
	 */
	EClass getSimpleGeometry();

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.geom.Point <em>Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Point</em>'.
	 * @see org.locationtech.geoff.geom.Point
	 * @generated
	 */
	EClass getPoint();

	/**
	 * Returns the meta object for the containment reference '{@link org.locationtech.geoff.geom.Point#getCoordinates <em>Coordinates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Coordinates</em>'.
	 * @see org.locationtech.geoff.geom.Point#getCoordinates()
	 * @see #getPoint()
	 * @generated
	 */
	EReference getPoint_Coordinates();

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.geom.LineString <em>Line String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Line String</em>'.
	 * @see org.locationtech.geoff.geom.LineString
	 * @generated
	 */
	EClass getLineString();

	/**
	 * Returns the meta object for the containment reference list '{@link org.locationtech.geoff.geom.LineString#getCoordinates <em>Coordinates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Coordinates</em>'.
	 * @see org.locationtech.geoff.geom.LineString#getCoordinates()
	 * @see #getLineString()
	 * @generated
	 */
	EReference getLineString_Coordinates();

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.geom.Polygon <em>Polygon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Polygon</em>'.
	 * @see org.locationtech.geoff.geom.Polygon
	 * @generated
	 */
	EClass getPolygon();

	/**
	 * Returns the meta object for the containment reference list '{@link org.locationtech.geoff.geom.Polygon#getCoordinates <em>Coordinates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Coordinates</em>'.
	 * @see org.locationtech.geoff.geom.Polygon#getCoordinates()
	 * @see #getPolygon()
	 * @generated
	 */
	EReference getPolygon_Coordinates();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	GeomFactory getGeomFactory();

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
		 * The meta object literal for the '{@link org.locationtech.geoff.geom.impl.GeometryImpl <em>Geometry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.geom.impl.GeometryImpl
		 * @see org.locationtech.geoff.geom.impl.GeomPackageImpl#getGeometry()
		 * @generated
		 */
		EClass GEOMETRY = eINSTANCE.getGeometry();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.geom.impl.SimpleGeometryImpl <em>Simple Geometry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.geom.impl.SimpleGeometryImpl
		 * @see org.locationtech.geoff.geom.impl.GeomPackageImpl#getSimpleGeometry()
		 * @generated
		 */
		EClass SIMPLE_GEOMETRY = eINSTANCE.getSimpleGeometry();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.geom.impl.PointImpl <em>Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.geom.impl.PointImpl
		 * @see org.locationtech.geoff.geom.impl.GeomPackageImpl#getPoint()
		 * @generated
		 */
		EClass POINT = eINSTANCE.getPoint();

		/**
		 * The meta object literal for the '<em><b>Coordinates</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POINT__COORDINATES = eINSTANCE.getPoint_Coordinates();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.geom.impl.LineStringImpl <em>Line String</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.geom.impl.LineStringImpl
		 * @see org.locationtech.geoff.geom.impl.GeomPackageImpl#getLineString()
		 * @generated
		 */
		EClass LINE_STRING = eINSTANCE.getLineString();

		/**
		 * The meta object literal for the '<em><b>Coordinates</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINE_STRING__COORDINATES = eINSTANCE.getLineString_Coordinates();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.geom.impl.PolygonImpl <em>Polygon</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.geom.impl.PolygonImpl
		 * @see org.locationtech.geoff.geom.impl.GeomPackageImpl#getPolygon()
		 * @generated
		 */
		EClass POLYGON = eINSTANCE.getPolygon();

		/**
		 * The meta object literal for the '<em><b>Coordinates</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POLYGON__COORDINATES = eINSTANCE.getPolygon_Coordinates();

	}

} //GeomPackage
