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
package org.locationtech.geoff.ui.parts;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.core.IGeoMapService;
import org.locationtech.geoff.layer.Layer;
import org.locationtech.geoff.ui.PageBook;

public class LayersUI {

	private PageBook pageBook;

	@PostConstruct
	public void createUI(PageBook pageBook) {
		this.pageBook = pageBook;
	}

	@PageBook.Default
	public void defaultPage(Composite container) {
		CLabel label = new CLabel(container, SWT.CENTER);
		label.setText("No input available.");
	}

	@PageBook.Destroy
	public void destroyPage(MPart part) {
	}

	@PageBook.IsRelevantPart
	public boolean relevantPart(@Optional IGeoMapService geoMapService) {
		return geoMapService != null;
	}

	@PageBook.Create
	public Class<LayersPage> createPage() {
		return LayersPage.class;
	}

	public IGeoMapService getCurrentMap() {
		return pageBook.getFromTarget(IGeoMapService.class);
	}

	public static IGeoMapService getGeoMapService(EPartService partService) {
		LayersUI layersUI = (LayersUI) partService.getParts().stream()
				.filter(p -> p.getContributionURI().endsWith(LayersUI.class.getName())).findFirst()
				.map(p -> p.getObject()).get();

		if (layersUI == null) {
			return null;
		}

		IGeoMapService geoMapService = layersUI.getCurrentMap();
		return geoMapService;
	}

	public static class LayersPage {
		@PostConstruct
		public void createUI(Composite pageContainer, IGeoMapService geoMapService, PageBook pageBook) {
			AdapterFactory af = geoMapService.adaptTo(AdapterFactory.class);

			AdapterFactoryLabelProvider aflp = new AdapterFactoryLabelProvider(af);
			AdapterFactoryContentProvider afcp = new AdapterFactoryContentProvider(af) {
				@Override
				public boolean hasChildren(Object object) {
					super.hasChildren(object);

					// only flat list
					return false;
				}

				@Override
				public Object[] getElements(Object object) {
					super.getElements(object);

					if (object instanceof GeoMap) {
						return ((GeoMap) object).getLayers().toArray();
					}

					return new Object[0];
				}
			};

			TreeViewer layersViewer = new TreeViewer(pageContainer);
			layersViewer.setContentProvider(afcp);
			layersViewer.setLabelProvider(aflp);
			layersViewer.setInput(geoMapService.adaptTo(GeoMap.class));

			// make the selected layer available within the editor context,
			// so other parts can use it
			{
				IViewerObservableValue singleSelection = ViewersObservables.observeSingleSelection(layersViewer);
				pageBook.bindValueTo(Layer.class, singleSelection);
			}
		}
	}
}