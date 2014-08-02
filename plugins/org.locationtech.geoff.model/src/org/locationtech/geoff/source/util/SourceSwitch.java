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
package org.locationtech.geoff.source.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.locationtech.geoff.Identifiable;
import org.locationtech.geoff.source.*;
import org.locationtech.geoff.source.BingMaps;
import org.locationtech.geoff.source.MapQuest;
import org.locationtech.geoff.source.OSM;
import org.locationtech.geoff.source.Source;
import org.locationtech.geoff.source.SourcePackage;
import org.locationtech.geoff.source.Tile;
import org.locationtech.geoff.source.TileImage;
import org.locationtech.geoff.source.XYZ;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.locationtech.geoff.source.SourcePackage
 * @generated
 */
public class SourceSwitch<T> extends Switch<T> {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = " Copyright (c) 2014 Erdal Karaca.\r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n \r\n Contributors:\r\n     Erdal Karaca - initial API and implementation\r\n"; //$NON-NLS-1$

	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SourcePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourceSwitch() {
		if (modelPackage == null) {
			modelPackage = SourcePackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case SourcePackage.SOURCE: {
			Source source = (Source) theEObject;
			T result = caseSource(source);
			if (result == null)
				result = caseIdentifiable(source);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case SourcePackage.TILE: {
			Tile tile = (Tile) theEObject;
			T result = caseTile(tile);
			if (result == null)
				result = caseSource(tile);
			if (result == null)
				result = caseIdentifiable(tile);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case SourcePackage.TILE_IMAGE: {
			TileImage tileImage = (TileImage) theEObject;
			T result = caseTileImage(tileImage);
			if (result == null)
				result = caseTile(tileImage);
			if (result == null)
				result = caseSource(tileImage);
			if (result == null)
				result = caseIdentifiable(tileImage);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case SourcePackage.XYZ: {
			XYZ xyz = (XYZ) theEObject;
			T result = caseXYZ(xyz);
			if (result == null)
				result = caseTileImage(xyz);
			if (result == null)
				result = caseTile(xyz);
			if (result == null)
				result = caseSource(xyz);
			if (result == null)
				result = caseIdentifiable(xyz);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case SourcePackage.OSM: {
			OSM osm = (OSM) theEObject;
			T result = caseOSM(osm);
			if (result == null)
				result = caseXYZ(osm);
			if (result == null)
				result = caseTileImage(osm);
			if (result == null)
				result = caseTile(osm);
			if (result == null)
				result = caseSource(osm);
			if (result == null)
				result = caseIdentifiable(osm);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case SourcePackage.MAP_QUEST: {
			MapQuest mapQuest = (MapQuest) theEObject;
			T result = caseMapQuest(mapQuest);
			if (result == null)
				result = caseXYZ(mapQuest);
			if (result == null)
				result = caseTileImage(mapQuest);
			if (result == null)
				result = caseTile(mapQuest);
			if (result == null)
				result = caseSource(mapQuest);
			if (result == null)
				result = caseIdentifiable(mapQuest);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case SourcePackage.BING_MAPS: {
			BingMaps bingMaps = (BingMaps) theEObject;
			T result = caseBingMaps(bingMaps);
			if (result == null)
				result = caseXYZ(bingMaps);
			if (result == null)
				result = caseTileImage(bingMaps);
			if (result == null)
				result = caseTile(bingMaps);
			if (result == null)
				result = caseSource(bingMaps);
			if (result == null)
				result = caseIdentifiable(bingMaps);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case SourcePackage.VECTOR: {
			Vector vector = (Vector) theEObject;
			T result = caseVector(vector);
			if (result == null)
				result = caseSource(vector);
			if (result == null)
				result = caseIdentifiable(vector);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSource(Source object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tile</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tile</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTile(Tile object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tile Image</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tile Image</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTileImage(TileImage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>XYZ</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>XYZ</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseXYZ(XYZ object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>OSM</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>OSM</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOSM(OSM object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Map Quest</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Map Quest</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMapQuest(MapQuest object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bing Maps</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bing Maps</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBingMaps(BingMaps object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Vector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Vector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVector(Vector object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Identifiable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Identifiable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdentifiable(Identifiable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //SourceSwitch
