/*******************************************************************************
 * Copyright (c) 2016 Erdal Karaca and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Erdal Karaca - initial API and implementation
 *******************************************************************************/
package org.locationtech.geoff.e4.utils.fragments;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.eclipse.e4.ui.model.application.MApplicationElement;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.commands.impl.CommandsPackageImpl;
import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.e4.ui.model.application.ui.impl.UiPackageImpl;
import org.eclipse.e4.ui.model.fragment.MFragmentFactory;
import org.eclipse.e4.ui.model.fragment.MStringModelFragment;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

@SuppressWarnings("restriction")
public class FragmentBuilder {
	private MStringModelFragment fragment = MFragmentFactory.INSTANCE.createStringModelFragment();

	private FragmentBuilder() {
	}

	public static FragmentBuilder create() {
		return new FragmentBuilder();
	}

	public MStringModelFragment get() {
		return fragment;
	}

	public <T extends MApplicationElement> FragmentBuilder element(Supplier<T> elementSupplier,
			BiConsumer<T, FragmentBuilder.ChildBuilder> customization) {
		T element = elementSupplier.get();
		fragment.getElements().add(element);
		processChildBuilder(element, customization);
		return this;
	}

	public FragmentBuilder customize(Consumer<MStringModelFragment> fragmentCustomizer) {
		fragmentCustomizer.accept(fragment);
		return this;
	}

	public static class ChildBuilder {
		private MApplicationElement element;

		@SuppressWarnings("unchecked")
		public <T extends MApplicationElement> FragmentBuilder.ChildBuilder child(Supplier<T> childSupplier,
				BiConsumer<T, FragmentBuilder.ChildBuilder> customization) {
			element = childSupplier.get();
			processChildBuilder((T) element, customization);
			return this;
		}

		public MApplicationElement get() {
			return element;
		}
	}

	@SuppressWarnings("unchecked")
	private static <T extends MApplicationElement> void processChildBuilder(T element,
			BiConsumer<T, FragmentBuilder.ChildBuilder> customization) {
		FragmentBuilder.ChildBuilder childBuilder = new ChildBuilder();
		customization.accept((T) element, childBuilder);
		MApplicationElement child = childBuilder.get();

		if (child == null) {
			return;
		}

		EObject eo = (EObject) element;
		EReference containementFeature = null;

		// FIXME find clean way to add the child to its containment feature
		if (element instanceof MElementContainer<?>) {
			containementFeature = UiPackageImpl.eINSTANCE.getElementContainer_Children();
		} else if (element instanceof MCommand) {
			containementFeature = CommandsPackageImpl.eINSTANCE.getCommand_Parameters();
		}

		if (containementFeature != null) {
			List<MApplicationElement> elementsList = (List<MApplicationElement>) eo.eGet(containementFeature);

			if (elementsList != null) {
				elementsList.add(child);
			}
		}
	}
}