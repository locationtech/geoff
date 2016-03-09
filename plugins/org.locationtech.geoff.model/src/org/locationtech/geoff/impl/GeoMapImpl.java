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
package org.locationtech.geoff.impl;

import java.util.Collection;

import java.util.List;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.locationtech.geoff.Descriptive;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.GeoffPackage;
import org.locationtech.geoff.RendererHint;
import org.locationtech.geoff.Script;
import org.locationtech.geoff.View;
import org.locationtech.geoff.interaction.Interaction;
import org.locationtech.geoff.layer.Layer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Geo Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.locationtech.geoff.impl.GeoMapImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.locationtech.geoff.impl.GeoMapImpl#getShortDescription <em>Short Description</em>}</li>
 *   <li>{@link org.locationtech.geoff.impl.GeoMapImpl#getLongDescription <em>Long Description</em>}</li>
 *   <li>{@link org.locationtech.geoff.impl.GeoMapImpl#getLayers <em>Layers</em>}</li>
 *   <li>{@link org.locationtech.geoff.impl.GeoMapImpl#getView <em>View</em>}</li>
 *   <li>{@link org.locationtech.geoff.impl.GeoMapImpl#getRendererHint <em>Renderer Hint</em>}</li>
 *   <li>{@link org.locationtech.geoff.impl.GeoMapImpl#getScripts <em>Scripts</em>}</li>
 *   <li>{@link org.locationtech.geoff.impl.GeoMapImpl#getInteractions <em>Interactions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GeoMapImpl extends MinimalEObjectImpl.Container implements GeoMap {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = " Copyright (c) 2014 Erdal Karaca.\r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n \r\n Contributors:\r\n     Erdal Karaca - initial API and implementation\r\n"; //$NON-NLS-1$

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getShortDescription() <em>Short Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String SHORT_DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getShortDescription() <em>Short Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortDescription()
	 * @generated
	 * @ordered
	 */
	protected String shortDescription = SHORT_DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getLongDescription() <em>Long Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLongDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String LONG_DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLongDescription() <em>Long Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLongDescription()
	 * @generated
	 * @ordered
	 */
	protected String longDescription = LONG_DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLayers() <em>Layers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLayers()
	 * @generated
	 * @ordered
	 */
	protected EList<Layer> layers;

	/**
	 * The cached value of the '{@link #getView() <em>View</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getView()
	 * @generated
	 * @ordered
	 */
	protected View view;

	/**
	 * The default value of the '{@link #getRendererHint() <em>Renderer Hint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRendererHint()
	 * @generated
	 * @ordered
	 */
	protected static final RendererHint RENDERER_HINT_EDEFAULT = RendererHint.CANVAS;

	/**
	 * The cached value of the '{@link #getRendererHint() <em>Renderer Hint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRendererHint()
	 * @generated
	 * @ordered
	 */
	protected RendererHint rendererHint = RENDERER_HINT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getScripts() <em>Scripts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScripts()
	 * @generated
	 * @ordered
	 */
	protected EList<Script> scripts;

	/**
	 * The cached value of the '{@link #getInteractions() <em>Interactions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInteractions()
	 * @generated
	 * @ordered
	 */
	protected EList<Interaction> interactions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GeoMapImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeoffPackage.Literals.GEO_MAP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeoffPackage.GEO_MAP__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShortDescription(String newShortDescription) {
		String oldShortDescription = shortDescription;
		shortDescription = newShortDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeoffPackage.GEO_MAP__SHORT_DESCRIPTION,
					oldShortDescription, shortDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLongDescription() {
		return longDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLongDescription(String newLongDescription) {
		String oldLongDescription = longDescription;
		longDescription = newLongDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeoffPackage.GEO_MAP__LONG_DESCRIPTION,
					oldLongDescription, longDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Layer> getLayers() {
		if (layers == null) {
			layers = new EObjectContainmentEList<Layer>(Layer.class, this, GeoffPackage.GEO_MAP__LAYERS);
		}
		return layers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public View getView() {
		return view;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetView(View newView, NotificationChain msgs) {
		View oldView = view;
		view = newView;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GeoffPackage.GEO_MAP__VIEW,
					oldView, newView);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setView(View newView) {
		if (newView != view) {
			NotificationChain msgs = null;
			if (view != null)
				msgs = ((InternalEObject) view).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GeoffPackage.GEO_MAP__VIEW, null, msgs);
			if (newView != null)
				msgs = ((InternalEObject) newView).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GeoffPackage.GEO_MAP__VIEW, null, msgs);
			msgs = basicSetView(newView, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeoffPackage.GEO_MAP__VIEW, newView, newView));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RendererHint getRendererHint() {
		return rendererHint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRendererHint(RendererHint newRendererHint) {
		RendererHint oldRendererHint = rendererHint;
		rendererHint = newRendererHint == null ? RENDERER_HINT_EDEFAULT : newRendererHint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeoffPackage.GEO_MAP__RENDERER_HINT, oldRendererHint,
					rendererHint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Script> getScripts() {
		if (scripts == null) {
			scripts = new EObjectContainmentEList<Script>(Script.class, this, GeoffPackage.GEO_MAP__SCRIPTS);
		}
		return scripts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Interaction> getInteractions() {
		if (interactions == null) {
			interactions = new EObjectContainmentEList<Interaction>(Interaction.class, this,
					GeoffPackage.GEO_MAP__INTERACTIONS);
		}
		return interactions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GeoffPackage.GEO_MAP__LAYERS:
			return ((InternalEList<?>) getLayers()).basicRemove(otherEnd, msgs);
		case GeoffPackage.GEO_MAP__VIEW:
			return basicSetView(null, msgs);
		case GeoffPackage.GEO_MAP__SCRIPTS:
			return ((InternalEList<?>) getScripts()).basicRemove(otherEnd, msgs);
		case GeoffPackage.GEO_MAP__INTERACTIONS:
			return ((InternalEList<?>) getInteractions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GeoffPackage.GEO_MAP__ID:
			return getId();
		case GeoffPackage.GEO_MAP__SHORT_DESCRIPTION:
			return getShortDescription();
		case GeoffPackage.GEO_MAP__LONG_DESCRIPTION:
			return getLongDescription();
		case GeoffPackage.GEO_MAP__LAYERS:
			return getLayers();
		case GeoffPackage.GEO_MAP__VIEW:
			return getView();
		case GeoffPackage.GEO_MAP__RENDERER_HINT:
			return getRendererHint();
		case GeoffPackage.GEO_MAP__SCRIPTS:
			return getScripts();
		case GeoffPackage.GEO_MAP__INTERACTIONS:
			return getInteractions();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case GeoffPackage.GEO_MAP__ID:
			setId((String) newValue);
			return;
		case GeoffPackage.GEO_MAP__SHORT_DESCRIPTION:
			setShortDescription((String) newValue);
			return;
		case GeoffPackage.GEO_MAP__LONG_DESCRIPTION:
			setLongDescription((String) newValue);
			return;
		case GeoffPackage.GEO_MAP__LAYERS:
			getLayers().clear();
			getLayers().addAll((Collection<? extends Layer>) newValue);
			return;
		case GeoffPackage.GEO_MAP__VIEW:
			setView((View) newValue);
			return;
		case GeoffPackage.GEO_MAP__RENDERER_HINT:
			setRendererHint((RendererHint) newValue);
			return;
		case GeoffPackage.GEO_MAP__SCRIPTS:
			getScripts().clear();
			getScripts().addAll((Collection<? extends Script>) newValue);
			return;
		case GeoffPackage.GEO_MAP__INTERACTIONS:
			getInteractions().clear();
			getInteractions().addAll((Collection<? extends Interaction>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case GeoffPackage.GEO_MAP__ID:
			setId(ID_EDEFAULT);
			return;
		case GeoffPackage.GEO_MAP__SHORT_DESCRIPTION:
			setShortDescription(SHORT_DESCRIPTION_EDEFAULT);
			return;
		case GeoffPackage.GEO_MAP__LONG_DESCRIPTION:
			setLongDescription(LONG_DESCRIPTION_EDEFAULT);
			return;
		case GeoffPackage.GEO_MAP__LAYERS:
			getLayers().clear();
			return;
		case GeoffPackage.GEO_MAP__VIEW:
			setView((View) null);
			return;
		case GeoffPackage.GEO_MAP__RENDERER_HINT:
			setRendererHint(RENDERER_HINT_EDEFAULT);
			return;
		case GeoffPackage.GEO_MAP__SCRIPTS:
			getScripts().clear();
			return;
		case GeoffPackage.GEO_MAP__INTERACTIONS:
			getInteractions().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case GeoffPackage.GEO_MAP__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case GeoffPackage.GEO_MAP__SHORT_DESCRIPTION:
			return SHORT_DESCRIPTION_EDEFAULT == null ? shortDescription != null
					: !SHORT_DESCRIPTION_EDEFAULT.equals(shortDescription);
		case GeoffPackage.GEO_MAP__LONG_DESCRIPTION:
			return LONG_DESCRIPTION_EDEFAULT == null ? longDescription != null
					: !LONG_DESCRIPTION_EDEFAULT.equals(longDescription);
		case GeoffPackage.GEO_MAP__LAYERS:
			return layers != null && !layers.isEmpty();
		case GeoffPackage.GEO_MAP__VIEW:
			return view != null;
		case GeoffPackage.GEO_MAP__RENDERER_HINT:
			return rendererHint != RENDERER_HINT_EDEFAULT;
		case GeoffPackage.GEO_MAP__SCRIPTS:
			return scripts != null && !scripts.isEmpty();
		case GeoffPackage.GEO_MAP__INTERACTIONS:
			return interactions != null && !interactions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Descriptive.class) {
			switch (derivedFeatureID) {
			case GeoffPackage.GEO_MAP__SHORT_DESCRIPTION:
				return GeoffPackage.DESCRIPTIVE__SHORT_DESCRIPTION;
			case GeoffPackage.GEO_MAP__LONG_DESCRIPTION:
				return GeoffPackage.DESCRIPTIVE__LONG_DESCRIPTION;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Descriptive.class) {
			switch (baseFeatureID) {
			case GeoffPackage.DESCRIPTIVE__SHORT_DESCRIPTION:
				return GeoffPackage.GEO_MAP__SHORT_DESCRIPTION;
			case GeoffPackage.DESCRIPTIVE__LONG_DESCRIPTION:
				return GeoffPackage.GEO_MAP__LONG_DESCRIPTION;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: "); //$NON-NLS-1$
		result.append(id);
		result.append(", shortDescription: "); //$NON-NLS-1$
		result.append(shortDescription);
		result.append(", longDescription: "); //$NON-NLS-1$
		result.append(longDescription);
		result.append(", rendererHint: "); //$NON-NLS-1$
		result.append(rendererHint);
		result.append(')');
		return result.toString();
	}

} //GeoMapImpl
