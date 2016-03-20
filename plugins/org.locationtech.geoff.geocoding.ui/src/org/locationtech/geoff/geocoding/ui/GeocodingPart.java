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
package org.locationtech.geoff.geocoding.ui;

import static org.locationtech.geoff.core.Geoff.feature;
import static org.locationtech.geoff.core.Geoff.icon;
import static org.locationtech.geoff.core.Geoff.pointGeom;
import static org.locationtech.geoff.core.Geoff.style;
import static org.locationtech.geoff.core.Geoff.vectorLayer;
import static org.locationtech.geoff.core.Geoff.vectorSource;
import static org.locationtech.geoff.core.Geoff.xyLocation;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MDirectMenuItem;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.locationtech.geoff.Feature;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.Location;
import org.locationtech.geoff.XYZLocation;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.geocoding.IGeocodingService;
import org.locationtech.geoff.geocoding.POI;
import org.locationtech.geoff.geocoding.POI.LatLon;
import org.locationtech.geoff.geom.Geometry;
import org.locationtech.geoff.layer.VectorLayer;
import org.locationtech.geoff.source.SourceFormat;
import org.locationtech.geoff.source.VectorSource;
import org.locationtech.geoff.ui.swt.GeoMapComposite;

public class GeocodingPart {

	private java.util.Optional<IGeocodingService> geoService = Optional.of(IGeocodingService.Util.getFirstFound());
	private XYZLocation markerLocation;
	private GeoMap mapModel = createBaseMap();
	private GeoMapComposite mapUI;

	@PostConstruct
	public void createUI(Composite parent, MPart part, EModelService modelService) {
		fillViewMenu(part, modelService);

		SashForm sash = new SashForm(parent, SWT.VERTICAL);
		{
			Composite container = new Composite(sash, SWT.None);
			container.setLayout(GridLayoutFactory.swtDefaults().numColumns(1).create());

			Text text = new Text(container, SWT.V_SCROLL | SWT.WRAP | SWT.SEARCH | SWT.ICON_SEARCH);
			text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			text.setMessage("Enter location name...");

			TableViewer viewer = new TableViewer(container);
			GridData tableData = new GridData(GridData.FILL_BOTH);
			tableData.horizontalSpan = 2;
			viewer.getControl().setLayoutData(tableData);
			viewer.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					POI poi = (POI) element;
					LatLon latLon = poi.getLatLon();
					return String.format("%s (lon: %f, lat: %f)", poi.getDescription(), latLon.getLon(),
							latLon.getLat());
				}

				@Override
				public Image getImage(Object element) {
					return super.getImage(element);
				}
			});
			viewer.setContentProvider(ArrayContentProvider.getInstance());

			ISWTObservableValue textObservable = WidgetProperties.text(SWT.Modify).observeDelayed(1000, text);
			textObservable.addValueChangeListener((e) -> {
				geoService.ifPresent((service) -> {
					List<POI> result = service.executeQuery(text.getText());
					viewer.setInput(result);
				});
			});

			viewer.addSelectionChangedListener((e) -> {
				IStructuredSelection sel = viewer.getStructuredSelection();

				if (sel.isEmpty()) {
					return;
				}

				POI selectedPOI = (POI) sel.getFirstElement();
				LatLon ll = selectedPOI.getLatLon();
				Location newLocation = Geoff.xyLocation(ll.getLon(), ll.getLat(), Geoff.EPSG4326_WGS84);

				markerLocation.setX(ll.getLon());
				markerLocation.setY(ll.getLat());
				mapModel.getView().setCenter(newLocation);
				mapUI.reloadMap();
			});
		}

		mapUI = new GeoMapComposite(sash, SWT.None);
		mapUI.loadMap(mapModel);
	}

	private void fillViewMenu(MPart part, EModelService modelService) {
		part.getMenus().stream().filter((m) -> m.getTags().contains("ViewMenu")).findFirst().ifPresent((m) -> {
			List<IGeocodingService> services = IGeocodingService.Util.getService(IGeocodingService.class);
			services.forEach((service) -> {
				MDirectMenuItem mi = modelService.createModelElement(MDirectMenuItem.class);
				mi.setLabel(service.getClass().getSimpleName());
				mi.setObject(GeocodingPart.this);
				mi.setType(org.eclipse.e4.ui.model.application.ui.menu.ItemType.RADIO);
				mi.getTransientData().put(IGeocodingService.class.getName(), service);
				m.getChildren().add(mi);

				if (geoService == null) {
					geoService = Optional.of(service);
					mi.setSelected(true);
				}
			});
		});
	}

	private GeoMap createBaseMap() {
		Geoff geoMap = Geoff.createMap().view(Geoff.xyLocation(0, 0, Geoff.EPSG4326_WGS84), 1);
		{
			VectorSource source = Geoff.vectorSource();
			source.setFormat(SourceFormat.GEO_JSON);
			source.setProjection(Geoff.EPSG3857_WEB_MERCATOR);
			source.setUrl("/org.locationtech.geoff.geocoding.ui/resources/countries.geojson");
			VectorLayer layer = Geoff.vectorLayer(source);
			Geoff.populateDefaultStyles(layer);
			geoMap.addLayer(layer);
		}
		{
			VectorSource vectorSource = vectorSource();
			markerLocation = xyLocation(0, 0, Geoff.EPSG4326_WGS84);
			Geometry geometry = pointGeom(markerLocation);
			Feature feature = feature(geometry,
					style(icon("/org.locationtech.geoff.geocoding.ui/resources/marker.gif")));
			vectorSource.getFeatures().add(feature);
			geoMap.addLayer(vectorLayer(vectorSource));
		}

		return geoMap.get();
	}
}
