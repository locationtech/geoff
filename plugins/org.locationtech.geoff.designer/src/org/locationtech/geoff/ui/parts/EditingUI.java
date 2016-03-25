package org.locationtech.geoff.ui.parts;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.CoolBarManager;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.locationtech.geoff.core.IGeoMapService;
import org.locationtech.geoff.designer.internal.DesignerUtil;
import org.locationtech.geoff.layer.Layer;
import org.locationtech.geoff.layer.VectorLayer;
import org.locationtech.geoff.source.VectorSource;
import org.locationtech.geoff.ui.Observable;
import org.locationtech.geoff.ui.swt.IGeoMapWidget;
import org.locationtech.geoff.ui.swt.IGeoMapWidget.EditingMode;
import org.locationtech.geoff.ui.swt.IGeoMapWidget.Property;

public class EditingUI {
	private Map<IGeoMapWidget.EditingMode, Object[]> editingModeParams = new HashMap<IGeoMapWidget.EditingMode, Object[]>();
	{
		editingModeParams.put(IGeoMapWidget.EditingMode.POINT, new Object[] { "Point", "icons/new_point_mode.gif" });
		editingModeParams.put(IGeoMapWidget.EditingMode.LINE, new Object[] { "Line", "icons/new_line_mode.gif" });
		editingModeParams.put(IGeoMapWidget.EditingMode.POLYGON,
				new Object[] { "Polygon", "icons/new_polygon_mode.gif" });
		editingModeParams.put(IGeoMapWidget.EditingMode.RECTANGLE,
				new Object[] { "Rectangle", "icons/new_rectangle_mode.gif" });
		editingModeParams.put(IGeoMapWidget.EditingMode.CIRCLE, new Object[] { "Circle", "icons/new_circle_mode.gif" });
	}

	@Inject
	@Observable(Layer.class)
	private IObservableValue layerObservable;

	private DataBindingContext dbc = new DataBindingContext();

	@PostConstruct
	public void createUI(Composite parent, IGeoMapService gms, IGeoMapWidget gmWidget) {
		parent.setLayout(new FillLayout());
		CoolBarManager cbMan = new CoolBarManager(SWT.FLAT);
		cbMan.createControl(parent);

		// setup editing toolbar
		createEditingToolbar(gmWidget, cbMan);
		cbMan.update(true);
	}

	private void createEditingToolbar(IGeoMapWidget gmWidget, CoolBarManager cbMan) {
		IObservableValue observeValue = gmWidget.observeValue(Property.EDITING_MODE);
		ToolBarManager tbMan = new ToolBarManager(SWT.FLAT);
		tbMan.createControl(cbMan.getControl());
		editingModeParams.entrySet().stream().forEach(e -> {
			EditingMode key = e.getKey();
			Object[] params = e.getValue();
			String actionName = (String) params[0];
			String imageFilePath = (String) params[1];

			Action action = new Action(actionName, IAction.AS_RADIO_BUTTON) {
				@Override
				public void run() {
					observeValue.setValue(key);
				}
			};
			action.setImageDescriptor(DesignerUtil.getImageDescriptor(imageFilePath));
			tbMan.add(action);
		});
		cbMan.add(tbMan);

		ISWTObservableValue tbEnabledObservable = WidgetProperties.enabled().observe(tbMan.getControl());
		UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
		modelToTarget.setConverter(new Converter(Layer.class, Boolean.class) {
			@Override
			public Object convert(Object fromObject) {
				Layer layer = (Layer) fromObject;
				return layer != null && layer instanceof VectorLayer && layer.getSource() instanceof VectorSource;
			}
		});
		dbc.bindValue(tbEnabledObservable, layerObservable, null, modelToTarget);
	}
}
