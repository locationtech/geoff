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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.GeoffPackage;
import org.locationtech.geoff.RendererHint;
import org.locationtech.geoff.View;
import org.locationtech.geoff.layer.Layer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Geo Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.locationtech.geoff.impl.GeoMapImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.locationtech.geoff.impl.GeoMapImpl#getLayers <em>Layers</em>}</li>
 *   <li>{@link org.locationtech.geoff.impl.GeoMapImpl#getView <em>View</em>}</li>
 *   <li>{@link org.locationtech.geoff.impl.GeoMapImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.locationtech.geoff.impl.GeoMapImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.locationtech.geoff.impl.GeoMapImpl#getRendererHint <em>Renderer Hint</em>}</li>
 * </ul>
 * </p>
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
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

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
			eNotify(new ENotificationImpl(this, Notification.SET,
					GeoffPackage.GEO_MAP__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Layer> getLayers() {
		if (layers == null) {
			layers = new EObjectContainmentEList<Layer>(Layer.class, this,
					GeoffPackage.GEO_MAP__LAYERS);
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
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, GeoffPackage.GEO_MAP__VIEW, oldView,
					newView);
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
						EOPPOSITE_FEATURE_BASE - GeoffPackage.GEO_MAP__VIEW,
						null, msgs);
			if (newView != null)
				msgs = ((InternalEObject) newView).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GeoffPackage.GEO_MAP__VIEW,
						null, msgs);
			msgs = basicSetView(newView, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					GeoffPackage.GEO_MAP__VIEW, newView, newView));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					GeoffPackage.GEO_MAP__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					GeoffPackage.GEO_MAP__DESCRIPTION, oldDescription,
					description));
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
		rendererHint = newRendererHint == null ? RENDERER_HINT_EDEFAULT
				: newRendererHint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					GeoffPackage.GEO_MAP__RENDERER_HINT, oldRendererHint,
					rendererHint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GeoffPackage.GEO_MAP__LAYERS:
			return ((InternalEList<?>) getLayers()).basicRemove(otherEnd, msgs);
		case GeoffPackage.GEO_MAP__VIEW:
			return basicSetView(null, msgs);
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
		case GeoffPackage.GEO_MAP__LAYERS:
			return getLayers();
		case GeoffPackage.GEO_MAP__VIEW:
			return getView();
		case GeoffPackage.GEO_MAP__NAME:
			return getName();
		case GeoffPackage.GEO_MAP__DESCRIPTION:
			return getDescription();
		case GeoffPackage.GEO_MAP__RENDERER_HINT:
			return getRendererHint();
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
		case GeoffPackage.GEO_MAP__LAYERS:
			getLayers().clear();
			getLayers().addAll((Collection<? extends Layer>) newValue);
			return;
		case GeoffPackage.GEO_MAP__VIEW:
			setView((View) newValue);
			return;
		case GeoffPackage.GEO_MAP__NAME:
			setName((String) newValue);
			return;
		case GeoffPackage.GEO_MAP__DESCRIPTION:
			setDescription((String) newValue);
			return;
		case GeoffPackage.GEO_MAP__RENDERER_HINT:
			setRendererHint((RendererHint) newValue);
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
		case GeoffPackage.GEO_MAP__LAYERS:
			getLayers().clear();
			return;
		case GeoffPackage.GEO_MAP__VIEW:
			setView((View) null);
			return;
		case GeoffPackage.GEO_MAP__NAME:
			setName(NAME_EDEFAULT);
			return;
		case GeoffPackage.GEO_MAP__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		case GeoffPackage.GEO_MAP__RENDERER_HINT:
			setRendererHint(RENDERER_HINT_EDEFAULT);
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
		case GeoffPackage.GEO_MAP__LAYERS:
			return layers != null && !layers.isEmpty();
		case GeoffPackage.GEO_MAP__VIEW:
			return view != null;
		case GeoffPackage.GEO_MAP__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT
					.equals(name);
		case GeoffPackage.GEO_MAP__DESCRIPTION:
			return DESCRIPTION_EDEFAULT == null ? description != null
					: !DESCRIPTION_EDEFAULT.equals(description);
		case GeoffPackage.GEO_MAP__RENDERER_HINT:
			return rendererHint != RENDERER_HINT_EDEFAULT;
		}
		return super.eIsSet(featureID);
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
		result.append(", name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", description: "); //$NON-NLS-1$
		result.append(description);
		result.append(", rendererHint: "); //$NON-NLS-1$
		result.append(rendererHint);
		result.append(')');
		return result.toString();
	}

} //GeoMapImpl
