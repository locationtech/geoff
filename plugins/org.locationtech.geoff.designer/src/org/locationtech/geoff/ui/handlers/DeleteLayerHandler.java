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

import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.locationtech.geoff.core.IGeoMapService;
import org.locationtech.geoff.layer.Layer;

public class DeleteLayerHandler {
	@Execute
	public void execute(IGeoMapService geoMapService, IStructuredSelection selection) {
		List<Layer> list = selection.toList();

		geoMapService.batchChanges(() -> {
			list.forEach((l) -> geoMapService.removeLayer(l));
		});
	}

}
