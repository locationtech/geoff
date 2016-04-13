package org.locationtech.geoff.ui.parts;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ControlContribution;
import org.eclipse.jface.action.CoolBarManager;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.locationtech.geoff.Feature;
import org.locationtech.geoff.GeoffPackage;
import org.locationtech.geoff.View;
import org.locationtech.geoff.XYZLocation;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.core.IGeoMapService;
import org.locationtech.geoff.designer.internal.DesignerUtil;
import org.locationtech.geoff.geom.SimpleGeometry;
import org.locationtech.geoff.layer.Layer;
import org.locationtech.geoff.layer.VectorLayer;
import org.locationtech.geoff.source.VectorSource;
import org.locationtech.geoff.ui.Observable;
import org.locationtech.geoff.ui.swt.IGeoMapWidget;
import org.locationtech.geoff.ui.swt.IGeoMapWidget.EditingMode;
import org.locationtech.geoff.ui.swt.IGeoMapWidget.Property;

public class EditingUI {
	private Map<IGeoMapWidget.EditingMode, Object[]> editingModeParams = new LinkedHashMap<IGeoMapWidget.EditingMode, Object[]>();
	{
		editingModeParams.put(IGeoMapWidget.EditingMode.Point, new Object[] { "Point", "icons/new_point_mode.gif" });
		editingModeParams.put(IGeoMapWidget.EditingMode.LineString, new Object[] { "Line", "icons/new_line_mode.gif" });

		// TODO polygon not yet supported
		// editingModeParams.put(IGeoMapWidget.EditingMode.Polygon,
		// new Object[] { "Polygon", "icons/new_polygon_mode.gif" });

		// TODO circle not yet supported
		// editingModeParams.put(IGeoMapWidget.EditingMode.Circle, new Object[]
		// { "Circle", "icons/new_circle_mode.gif" });

		// TODO rectangle not yet supported
		// editingModeParams.put(IGeoMapWidget.EditingMode.Rectangle,
		// new Object[] { "Rectangle", "icons/new_rectangle_mode.gif" });
	}

	@Inject
	@Observable(Layer.class)
	private IObservableValue layerObservable;

	private DataBindingContext dbc = new DataBindingContext();
	private IConverter converter = new Converter(Layer.class, Boolean.class) {
		@Override
		public Object convert(Object fromObject) {
			Layer layer = (Layer) fromObject;
			return layer != null && layer instanceof VectorLayer && layer.getSource() instanceof VectorSource;
		}
	};

	@PostConstruct
	public void createUI(Composite parent, IGeoMapService gms, IGeoMapWidget gmWidget) {
		parent.setLayout(new FillLayout());
		CoolBarManager cbMan = new CoolBarManager(SWT.FLAT);
		cbMan.createControl(parent);

		// setup editing toolbar
		createEditingToolbar(gms, gmWidget, cbMan);
		createZoomingToolbar(gms, gmWidget, cbMan);
		cbMan.update(true);
	}

	@PreDestroy
	public void preDestroy() {
		dbc.dispose();
	}

	private void createZoomingToolbar(IGeoMapService gms, IGeoMapWidget gmWidget, CoolBarManager cbMan) {
		ToolBarManager tbMan = new ToolBarManager(SWT.FLAT);
		tbMan.createControl(cbMan.getControl());
		cbMan.add(tbMan);

		IObservableValue zoomObservable = gmWidget.observeValue(IGeoMapWidget.Property.VIEW_ZOOM);
		{
			IObservableValue centerObservable = gmWidget.observeValue(IGeoMapWidget.Property.VIEW_CENTER);

			Action action = new Action("AV") {
				@Override
				public void run() {
					gms.execTX(getToolTipText(), () -> {
						View view = gms.getGeoMap().getView();
						Integer zoom = (Integer) zoomObservable.getValue();

						if (zoom != null) {
							view.setZoom(zoom);
						}

						XYZLocation center = (XYZLocation) centerObservable.getValue();

						if (center != null) {
							view.setCenter(center);
						}
					});
				}
			};
			action.setToolTipText("Apply current view to map");
			action.setImageDescriptor(DesignerUtil.getImageDescriptor("icons/apply.gif"));
			tbMan.add(action);
		}

		{
			Action action = new Action("+") {
				@Override
				public void run() {
					Integer zoom = (Integer) zoomObservable.getValue();

					if (zoom != null) {
						zoom++;
						zoomObservable.setValue(zoom);
					}
				}
			};
			action.setToolTipText("Zoom in");
			action.setImageDescriptor(DesignerUtil.getImageDescriptor("icons/zoom_in.gif"));
			tbMan.add(action);
		}

		{
			Action action = new Action("-") {
				@Override
				public void run() {
					Integer zoom = (Integer) zoomObservable.getValue();

					if (zoom != null) {
						zoom--;
						zoomObservable.setValue(zoom);
					}
				}
			};
			action.setToolTipText("Zoom out");
			action.setImageDescriptor(DesignerUtil.getImageDescriptor("icons/zoom_out.gif"));
			tbMan.add(action);
		}
	}

	private void createEditingToolbar(IGeoMapService gms, IGeoMapWidget gmWidget, CoolBarManager cbMan) {
		IObservableValue editingModeObservable = gmWidget.observeValue(Property.EDITING_MODE);
		ToolBarManager tbMan = new ToolBarManager(SWT.FLAT);
		tbMan.createControl(cbMan.getControl());
		cbMan.add(tbMan);

		ControlContribution item = new ControlContribution("active-layer") {

			@Override
			protected Control createControl(Composite parent) {
				Text layerName = new Text(parent, SWT.READ_ONLY);
				ISWTObservableValue textObservable = WidgetProperties.text().observe(layerName);
				ISWTObservableValue tooltipObservable = WidgetProperties.tooltipText().observe(layerName);
				IObservableValue layerNameObservable = EMFObservables.observeDetailValue(textObservable.getRealm(),
						layerObservable, GeoffPackage.Literals.DESCRIPTIVE__SHORT_DESCRIPTION);
				dbc.bindValue(textObservable, layerNameObservable);
				dbc.bindValue(tooltipObservable, layerNameObservable);
				return layerName;
			}
		};
		tbMan.add(item);
		editingModeParams.entrySet().stream().forEach(e -> {
			EditingMode key = e.getKey();
			Object[] params = e.getValue();
			String actionName = (String) params[0];
			String imageFilePath = (String) params[1];

			Action action = new Action(actionName, IAction.AS_RADIO_BUTTON) {
				@Override
				public void run() {
					if (key.equals(editingModeObservable.getValue())) {
						// if same item was chosen, then disable editing mode
						editingModeObservable.setValue(IGeoMapWidget.EditingMode.NONE);
						setChecked(false);
					} else {
						editingModeObservable.setValue(key);
					}
				}
			};
			action.setImageDescriptor(DesignerUtil.getImageDescriptor(imageFilePath));
			tbMan.add(action);
		});

		ISWTObservableValue tbEnabledObservable = WidgetProperties.enabled().observe(tbMan.getControl());
		UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
		modelToTarget.setConverter(converter);
		dbc.bindValue(tbEnabledObservable, layerObservable, null, modelToTarget);

		IObservableValue geomAdded = gmWidget.observeValue(IGeoMapWidget.Property.GEOMETRY_ADDED);
		geomAdded.addValueChangeListener(ev -> {
			if (layerObservable.isDisposed() || !(Boolean) converter.convert(layerObservable.getValue())) {
				return;
			}

			VectorLayer vectorLayer = (VectorLayer) layerObservable.getValue();
			SimpleGeometry geom = (SimpleGeometry) ev.diff.getNewValue();
			VectorSource source = (VectorSource) vectorLayer.getSource();
			Feature feature = Geoff.feature(geom, null);

			gms.execTX("Add new feature", () -> {
				source.getFeatures().add(feature);
			});
		});
		tbMan.update(true);
	}
}
