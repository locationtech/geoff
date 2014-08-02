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
package org.locationtech.geoff;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see org.locationtech.geoff.GeoffFactory
 * @model kind="package"
 * @generated
 */
public interface GeoffPackage extends EPackage {
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
	String eNAME = "geoff"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.locationtech.org/geoff-v1"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "geoff"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	GeoffPackage eINSTANCE = org.locationtech.geoff.impl.GeoffPackageImpl
			.init();

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.Identifiable <em>Identifiable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.Identifiable
	 * @see org.locationtech.geoff.impl.GeoffPackageImpl#getIdentifiable()
	 * @generated
	 */
	int IDENTIFIABLE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE__ID = 0;

	/**
	 * The number of structural features of the '<em>Identifiable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Identifiable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.impl.GeoMapImpl <em>Geo Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.impl.GeoMapImpl
	 * @see org.locationtech.geoff.impl.GeoffPackageImpl#getGeoMap()
	 * @generated
	 */
	int GEO_MAP = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_MAP__ID = IDENTIFIABLE__ID;

	/**
	 * The feature id for the '<em><b>Layers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_MAP__LAYERS = IDENTIFIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>View</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_MAP__VIEW = IDENTIFIABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_MAP__NAME = IDENTIFIABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_MAP__DESCRIPTION = IDENTIFIABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Renderer Hint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_MAP__RENDERER_HINT = IDENTIFIABLE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Geo Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_MAP_FEATURE_COUNT = IDENTIFIABLE_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Geo Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_MAP_OPERATION_COUNT = IDENTIFIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.impl.ViewImpl <em>View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.impl.ViewImpl
	 * @see org.locationtech.geoff.impl.GeoffPackageImpl#getView()
	 * @generated
	 */
	int VIEW = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW__ID = IDENTIFIABLE__ID;

	/**
	 * The feature id for the '<em><b>Center</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW__CENTER = IDENTIFIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Zoom</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW__ZOOM = IDENTIFIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_FEATURE_COUNT = IDENTIFIABLE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_OPERATION_COUNT = IDENTIFIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.impl.LocationImpl <em>Location</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.impl.LocationImpl
	 * @see org.locationtech.geoff.impl.GeoffPackageImpl#getLocation()
	 * @generated
	 */
	int LOCATION = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__ID = IDENTIFIABLE__ID;

	/**
	 * The feature id for the '<em><b>Projection Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__PROJECTION_CODE = IDENTIFIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Location</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION_FEATURE_COUNT = IDENTIFIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Location</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION_OPERATION_COUNT = IDENTIFIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.impl.XYZLocationImpl <em>XYZ Location</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.impl.XYZLocationImpl
	 * @see org.locationtech.geoff.impl.GeoffPackageImpl#getXYZLocation()
	 * @generated
	 */
	int XYZ_LOCATION = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XYZ_LOCATION__ID = LOCATION__ID;

	/**
	 * The feature id for the '<em><b>Projection Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XYZ_LOCATION__PROJECTION_CODE = LOCATION__PROJECTION_CODE;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XYZ_LOCATION__X = LOCATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XYZ_LOCATION__Y = LOCATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Z</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XYZ_LOCATION__Z = LOCATION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>XYZ Location</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XYZ_LOCATION_FEATURE_COUNT = LOCATION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>XYZ Location</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XYZ_LOCATION_OPERATION_COUNT = LOCATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.impl.FeatureImpl <em>Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.impl.FeatureImpl
	 * @see org.locationtech.geoff.impl.GeoffPackageImpl#getFeature()
	 * @generated
	 */
	int FEATURE = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__ID = IDENTIFIABLE__ID;

	/**
	 * The feature id for the '<em><b>Geometry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__GEOMETRY = IDENTIFIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__STYLES = IDENTIFIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FEATURE_COUNT = IDENTIFIABLE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_OPERATION_COUNT = IDENTIFIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.locationtech.geoff.RendererHint <em>Renderer Hint</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.locationtech.geoff.RendererHint
	 * @see org.locationtech.geoff.impl.GeoffPackageImpl#getRendererHint()
	 * @generated
	 */
	int RENDERER_HINT = 6;

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.Identifiable <em>Identifiable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Identifiable</em>'.
	 * @see org.locationtech.geoff.Identifiable
	 * @generated
	 */
	EClass getIdentifiable();

	/**
	 * Returns the meta object for the attribute '{@link org.locationtech.geoff.Identifiable#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.locationtech.geoff.Identifiable#getId()
	 * @see #getIdentifiable()
	 * @generated
	 */
	EAttribute getIdentifiable_Id();

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.GeoMap <em>Geo Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Geo Map</em>'.
	 * @see org.locationtech.geoff.GeoMap
	 * @generated
	 */
	EClass getGeoMap();

	/**
	 * Returns the meta object for the containment reference list '{@link org.locationtech.geoff.GeoMap#getLayers <em>Layers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Layers</em>'.
	 * @see org.locationtech.geoff.GeoMap#getLayers()
	 * @see #getGeoMap()
	 * @generated
	 */
	EReference getGeoMap_Layers();

	/**
	 * Returns the meta object for the containment reference '{@link org.locationtech.geoff.GeoMap#getView <em>View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>View</em>'.
	 * @see org.locationtech.geoff.GeoMap#getView()
	 * @see #getGeoMap()
	 * @generated
	 */
	EReference getGeoMap_View();

	/**
	 * Returns the meta object for the attribute '{@link org.locationtech.geoff.GeoMap#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.locationtech.geoff.GeoMap#getName()
	 * @see #getGeoMap()
	 * @generated
	 */
	EAttribute getGeoMap_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.locationtech.geoff.GeoMap#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.locationtech.geoff.GeoMap#getDescription()
	 * @see #getGeoMap()
	 * @generated
	 */
	EAttribute getGeoMap_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.locationtech.geoff.GeoMap#getRendererHint <em>Renderer Hint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Renderer Hint</em>'.
	 * @see org.locationtech.geoff.GeoMap#getRendererHint()
	 * @see #getGeoMap()
	 * @generated
	 */
	EAttribute getGeoMap_RendererHint();

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.View <em>View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>View</em>'.
	 * @see org.locationtech.geoff.View
	 * @generated
	 */
	EClass getView();

	/**
	 * Returns the meta object for the containment reference '{@link org.locationtech.geoff.View#getCenter <em>Center</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Center</em>'.
	 * @see org.locationtech.geoff.View#getCenter()
	 * @see #getView()
	 * @generated
	 */
	EReference getView_Center();

	/**
	 * Returns the meta object for the attribute '{@link org.locationtech.geoff.View#getZoom <em>Zoom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Zoom</em>'.
	 * @see org.locationtech.geoff.View#getZoom()
	 * @see #getView()
	 * @generated
	 */
	EAttribute getView_Zoom();

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.Location <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Location</em>'.
	 * @see org.locationtech.geoff.Location
	 * @generated
	 */
	EClass getLocation();

	/**
	 * Returns the meta object for the attribute '{@link org.locationtech.geoff.Location#getProjectionCode <em>Projection Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Projection Code</em>'.
	 * @see org.locationtech.geoff.Location#getProjectionCode()
	 * @see #getLocation()
	 * @generated
	 */
	EAttribute getLocation_ProjectionCode();

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.XYZLocation <em>XYZ Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>XYZ Location</em>'.
	 * @see org.locationtech.geoff.XYZLocation
	 * @generated
	 */
	EClass getXYZLocation();

	/**
	 * Returns the meta object for the attribute '{@link org.locationtech.geoff.XYZLocation#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.locationtech.geoff.XYZLocation#getX()
	 * @see #getXYZLocation()
	 * @generated
	 */
	EAttribute getXYZLocation_X();

	/**
	 * Returns the meta object for the attribute '{@link org.locationtech.geoff.XYZLocation#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.locationtech.geoff.XYZLocation#getY()
	 * @see #getXYZLocation()
	 * @generated
	 */
	EAttribute getXYZLocation_Y();

	/**
	 * Returns the meta object for the attribute '{@link org.locationtech.geoff.XYZLocation#getZ <em>Z</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Z</em>'.
	 * @see org.locationtech.geoff.XYZLocation#getZ()
	 * @see #getXYZLocation()
	 * @generated
	 */
	EAttribute getXYZLocation_Z();

	/**
	 * Returns the meta object for class '{@link org.locationtech.geoff.Feature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature</em>'.
	 * @see org.locationtech.geoff.Feature
	 * @generated
	 */
	EClass getFeature();

	/**
	 * Returns the meta object for the containment reference '{@link org.locationtech.geoff.Feature#getGeometry <em>Geometry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Geometry</em>'.
	 * @see org.locationtech.geoff.Feature#getGeometry()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_Geometry();

	/**
	 * Returns the meta object for the containment reference list '{@link org.locationtech.geoff.Feature#getStyles <em>Styles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Styles</em>'.
	 * @see org.locationtech.geoff.Feature#getStyles()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_Styles();

	/**
	 * Returns the meta object for enum '{@link org.locationtech.geoff.RendererHint <em>Renderer Hint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Renderer Hint</em>'.
	 * @see org.locationtech.geoff.RendererHint
	 * @generated
	 */
	EEnum getRendererHint();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	GeoffFactory getGeoffFactory();

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
		 * The meta object literal for the '{@link org.locationtech.geoff.Identifiable <em>Identifiable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.Identifiable
		 * @see org.locationtech.geoff.impl.GeoffPackageImpl#getIdentifiable()
		 * @generated
		 */
		EClass IDENTIFIABLE = eINSTANCE.getIdentifiable();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTIFIABLE__ID = eINSTANCE.getIdentifiable_Id();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.impl.GeoMapImpl <em>Geo Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.impl.GeoMapImpl
		 * @see org.locationtech.geoff.impl.GeoffPackageImpl#getGeoMap()
		 * @generated
		 */
		EClass GEO_MAP = eINSTANCE.getGeoMap();

		/**
		 * The meta object literal for the '<em><b>Layers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GEO_MAP__LAYERS = eINSTANCE.getGeoMap_Layers();

		/**
		 * The meta object literal for the '<em><b>View</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GEO_MAP__VIEW = eINSTANCE.getGeoMap_View();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEO_MAP__NAME = eINSTANCE.getGeoMap_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEO_MAP__DESCRIPTION = eINSTANCE.getGeoMap_Description();

		/**
		 * The meta object literal for the '<em><b>Renderer Hint</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEO_MAP__RENDERER_HINT = eINSTANCE.getGeoMap_RendererHint();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.impl.ViewImpl <em>View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.impl.ViewImpl
		 * @see org.locationtech.geoff.impl.GeoffPackageImpl#getView()
		 * @generated
		 */
		EClass VIEW = eINSTANCE.getView();

		/**
		 * The meta object literal for the '<em><b>Center</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIEW__CENTER = eINSTANCE.getView_Center();

		/**
		 * The meta object literal for the '<em><b>Zoom</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW__ZOOM = eINSTANCE.getView_Zoom();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.impl.LocationImpl <em>Location</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.impl.LocationImpl
		 * @see org.locationtech.geoff.impl.GeoffPackageImpl#getLocation()
		 * @generated
		 */
		EClass LOCATION = eINSTANCE.getLocation();

		/**
		 * The meta object literal for the '<em><b>Projection Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCATION__PROJECTION_CODE = eINSTANCE
				.getLocation_ProjectionCode();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.impl.XYZLocationImpl <em>XYZ Location</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.impl.XYZLocationImpl
		 * @see org.locationtech.geoff.impl.GeoffPackageImpl#getXYZLocation()
		 * @generated
		 */
		EClass XYZ_LOCATION = eINSTANCE.getXYZLocation();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute XYZ_LOCATION__X = eINSTANCE.getXYZLocation_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute XYZ_LOCATION__Y = eINSTANCE.getXYZLocation_Y();

		/**
		 * The meta object literal for the '<em><b>Z</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute XYZ_LOCATION__Z = eINSTANCE.getXYZLocation_Z();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.impl.FeatureImpl <em>Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.impl.FeatureImpl
		 * @see org.locationtech.geoff.impl.GeoffPackageImpl#getFeature()
		 * @generated
		 */
		EClass FEATURE = eINSTANCE.getFeature();

		/**
		 * The meta object literal for the '<em><b>Geometry</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE__GEOMETRY = eINSTANCE.getFeature_Geometry();

		/**
		 * The meta object literal for the '<em><b>Styles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE__STYLES = eINSTANCE.getFeature_Styles();

		/**
		 * The meta object literal for the '{@link org.locationtech.geoff.RendererHint <em>Renderer Hint</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.locationtech.geoff.RendererHint
		 * @see org.locationtech.geoff.impl.GeoffPackageImpl#getRendererHint()
		 * @generated
		 */
		EEnum RENDERER_HINT = eINSTANCE.getRendererHint();

	}

} //GeoffPackage
