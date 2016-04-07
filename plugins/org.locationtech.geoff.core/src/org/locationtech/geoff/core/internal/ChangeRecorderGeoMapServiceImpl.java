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
package org.locationtech.geoff.core.internal;

import java.io.File;
import java.util.Arrays;
import java.util.EventObject;
import java.util.List;
import java.util.function.Consumer;

import org.eclipse.core.databinding.observable.value.DecoratingObservableValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.core.IGeoMapService;
import org.locationtech.geoff.core.logging.LogUtil;
import org.locationtech.geoff.layer.Layer;

/**
 * An {@link IGeoMapService} implementation that uses {@link ChangeRecorder}
 * capabilities to track changes to the model.
 * 
 * @author Erdal Karaca
 *
 */
public class ChangeRecorderGeoMapServiceImpl implements IGeoMapService {

	private AdapterFactoryEditingDomain editingDomain;
	private GeoMap geoMap;
	private Resource resource;

	public ChangeRecorderGeoMapServiceImpl(File input) {
		ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		editingDomain = new AdapterFactoryEditingDomain(composedAdapterFactory, new BasicCommandStack());

		resource = editingDomain.loadResource(input.toURI().toString());
		resource.eAdapters().add(new EditingDomainAdapter());
		EList<EObject> contents = resource.getContents();

		for (EObject eObject : contents) {
			if (eObject instanceof GeoMap) {
				geoMap = Geoff.toId(eObject);
				// assume there is only one GeoMap per file/resource
				break;
			}
		}

		EObject eObject = Geoff.toEObject(geoMap);

		if (geoMap == null) {
			geoMap = Geoff.createMap().get();
			resource.getContents().add(eObject);
		}

		eObject.eAdapters().add(new EditingDomainAdapter());
	}

	@Override
	public List<Layer> listLayers() {
		return null;
	}

	@Override
	public void save() {
		LogUtil.logErrorOnException(() -> resource.save(null));
	}

	private class EditingDomainAdapter extends AdapterImpl implements IEditingDomainProvider {
		@Override
		public boolean isAdapterForType(Object type) {
			return IEditingDomainProvider.class.equals(type);
		}

		@Override
		public EditingDomain getEditingDomain() {
			return editingDomain;
		}
	}

	@Override
	public boolean canUndo() {
		return commandStack().canUndo();
	}

	private CommandStack commandStack() {
		return editingDomain.getCommandStack();
	}

	@Override
	public boolean canRedo() {
		return commandStack().canRedo();
	}

	@Override
	public void undo() {
		commandStack().undo();
	}

	@Override
	public void redo() {
		commandStack().redo();
	}

	@Override
	public void onChange(Consumer<EventObject> eventsConsumers) {
		CommandStackListener listener = (e) -> eventsConsumers.accept(e);
		commandStack().addCommandStackListener(listener);
	}

	@Override
	public GeoMap getGeoMap() {
		return geoMap;
	}

	@Override
	public void shutdown() {
		//
	}

	@Override
	public void batchChanges(Runnable... commands) {
		if (commands == null || commands.length == 0) {
			return;
		}

		// the code to execute may affect the whole map instance
		ChangeCommand cc = new ChangeCommand(Geoff.toEObject(geoMap)) {

			@Override
			protected void doExecute() {
				Arrays.asList(commands).forEach((r) -> r.run());
			}
		};

		commandStack().execute(cc);
	}

	@Override
	public void addLayer(Layer l) {
		batchChanges(() -> geoMap.getLayers().add(l));
	}

	@Override
	public void removeLayer(Layer l) {
		batchChanges(() -> geoMap.getLayers().remove(l));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T adaptTo(Class<T> type) {
		if (AdapterFactory.class == type) {
			return (T) editingDomain.getAdapterFactory();
		}

		if (type == GeoMap.class) {
			return (T) geoMap;
		}

		return null;
	}

	@Override
	public IObservableValue transactional(IObservableValue observableValue) {
		return new DecoratingObservableValue(observableValue, true) {
			@Override
			public void setValue(Object value) {
				batchChanges(() -> {
					super.setValue(value);
				});
			}
		};
	}
}
