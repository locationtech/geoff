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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.core.IGeoMapService;
import org.locationtech.geoff.designer.internal.DesignerUtil;
import org.locationtech.geoff.designer.internal.ResourcesUtil;
import org.locationtech.geoff.layer.Layer;
import org.locationtech.geoff.layer.VectorLayer;
import org.locationtech.geoff.source.SourceFormat;
import org.locationtech.geoff.source.VectorSource;

public class AddResourceAsLayerHandler {
	private Map<String, SourceFormat> formatsMappings = new HashMap<String, SourceFormat>();

	{
		formatsMappings.put("geojson", SourceFormat.GEO_JSON);
		formatsMappings.put("gpx", SourceFormat.GPX);
		formatsMappings.put("kml", SourceFormat.KML);
	}

	@Execute
	public void execute(IGeoMapService geoMapService, ISelection selection) {
		if (selection.isEmpty()) {
			return;
		}

		Object[] array = ((IStructuredSelection) selection).toArray();
		List<Layer> layers = new ArrayList<>();

		for (Object o : array) {
			if (!(o instanceof IFile)) {
				continue;
			}

			IFile file = (IFile) o;
			String fileExtension = file.getFileExtension();
			SourceFormat sourceFormat = formatsMappings.get(fileExtension);

			if (sourceFormat == null) {
				continue;
			}

			VectorSource source = Geoff.vectorSource();
			source.setFormat(sourceFormat);
			source.setProjection(Geoff.EPSG3857_WEB_MERCATOR);
			String path = ResourcesUtil.toProjectRelativePath(file);
			source.setUrl(path);

			VectorLayer layer = Geoff.vectorLayer(source);
			Geoff.populateDefaultStyles(layer);
			layers.add(layer);
		}

		if (!layers.isEmpty()) {
			geoMapService.batchChanges(() -> {
				layers.forEach((l) -> geoMapService.addLayer(l));
			});
		}
	}
}
