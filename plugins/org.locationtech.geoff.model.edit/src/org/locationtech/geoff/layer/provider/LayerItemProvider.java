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
package org.locationtech.geoff.layer.provider;

import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.locationtech.geoff.GeoffPackage;
import org.locationtech.geoff.layer.Layer;
import org.locationtech.geoff.layer.LayerPackage;
import org.locationtech.geoff.provider.GeoffEditPlugin;
import org.locationtech.geoff.source.Source;
import org.locationtech.geoff.source.SourceFactory;

/**
 * This is the item provider adapter for a {@link org.locationtech.geoff.layer.Layer} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class LayerItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = " Copyright (c) 2014 Erdal Karaca.\r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n \r\n Contributors:\r\n     Erdal Karaca - initial API and implementation\r\n"; //$NON-NLS-1$

	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public LayerItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addIdPropertyDescriptor(object);
			addShortDescriptionPropertyDescriptor(object);
			addLongDescriptionPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Id feature.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Identifiable_id_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_Identifiable_id_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_Identifiable_type"), //$NON-NLS-1$
						GeoffPackage.Literals.IDENTIFIABLE__ID, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Short Description feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addShortDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Descriptive_shortDescription_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_Descriptive_shortDescription_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_Descriptive_type"), //$NON-NLS-1$
						GeoffPackage.Literals.DESCRIPTIVE__SHORT_DESCRIPTION, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Long Description feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addLongDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Descriptive_longDescription_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_Descriptive_longDescription_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_Descriptive_type"), //$NON-NLS-1$
						GeoffPackage.Literals.DESCRIPTIVE__LONG_DESCRIPTION, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(LayerPackage.Literals.LAYER__SOURCE);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Layer.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Object getImage(Object object) {
		Layer tile = (Layer) object;
		Source source = tile.getSource();

		if (source != null) {
			IItemLabelProvider itemLabelProvider = (IItemLabelProvider) getRootAdapterFactory().adapt((Object) source,
					IItemLabelProvider.class);
			return itemLabelProvider.getImage(source);
		}

		return overlayImage(object, getResourceLocator().getImage("full/obj16/Layer")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		Layer tile = (Layer) object;
		Source source = tile.getSource();

		if (source != null) {
			IItemLabelProvider itemLabelProvider = (IItemLabelProvider) getRootAdapterFactory().adapt((Object) source,
					IItemLabelProvider.class);
			return itemLabelProvider.getText(source);
		}

		return getTypeText(object);
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Layer.class)) {
		case LayerPackage.LAYER__ID:
		case LayerPackage.LAYER__SHORT_DESCRIPTION:
		case LayerPackage.LAYER__LONG_DESCRIPTION:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case LayerPackage.LAYER__SOURCE:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors
				.add(createChildParameter(LayerPackage.Literals.LAYER__SOURCE, SourceFactory.eINSTANCE.createOSM()));

		newChildDescriptors.add(
				createChildParameter(LayerPackage.Literals.LAYER__SOURCE, SourceFactory.eINSTANCE.createMapQuest()));

		newChildDescriptors.add(
				createChildParameter(LayerPackage.Literals.LAYER__SOURCE, SourceFactory.eINSTANCE.createBingMaps()));

		newChildDescriptors.add(createChildParameter(LayerPackage.Literals.LAYER__SOURCE,
				SourceFactory.eINSTANCE.createVectorSource()));
	}

	/**
	 * Return the resource locator for this item provider's resources. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return GeoffEditPlugin.INSTANCE;
	}

}
