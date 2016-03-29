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
package org.locationtech.geoff.ui.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.swt.widgets.Shell;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.core.IGeoMapService;
import org.locationtech.geoff.layer.Layer;
import org.locationtech.geoff.layer.LayerPackage;
import org.locationtech.geoff.layer.TileLayer;
import org.locationtech.geoff.layer.VectorLayer;
import org.locationtech.geoff.source.Source;
import org.locationtech.geoff.source.SourcePackage;
import org.locationtech.geoff.ui.DialogsBuilder;

public class AddNewLayerHandler {

	@Execute
	public void execute(IGeoMapService geoMapService, Shell shell) {
		Collection<Source> sources = Geoff.samplesOf(SourcePackage.Literals.SOURCE);
		AdapterFactory af = geoMapService.adaptTo(AdapterFactory.class);
		DialogsBuilder diag = DialogsBuilder.create()//
				.input(sources.toArray())//
				.message("Choose a source to create a new layer")//
				.multi(false)//
				.modal(true)//
				.adapterFactory(af);

		Object[] result = diag.getResult(shell);

		if (result != null) {
			List<Layer> layers = new ArrayList<Layer>();

			for (Object object : result) {
				EClass type = null;

				if (object instanceof TileLayer) {
					type = LayerPackage.Literals.TILE_LAYER;
				} else if (object instanceof VectorLayer) {
					type = LayerPackage.Literals.VECTOR_LAYER;
				}

				if (type == null) {
					continue;
				}

				Source source = (Source) object;
				Layer layer = Geoff.instance(type);
				layer.setSource(source);
			}

			geoMapService.batchChanges(() -> {
				layers.forEach((l) -> geoMapService.addLayer(l));
			});
		}
	}

}
