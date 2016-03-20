package org.locationtech.geoff.ui.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.locationtech.geoff.XYZLocation;
import org.locationtech.geoff.core.IGeoMapService;
import org.locationtech.geoff.ui.PageBook;
import org.locationtech.geoff.ui.swt.IGeoMapWidget;

public class MapPropsUI {
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
	public Class<MapPropsPage> createPage() {
		return MapPropsPage.class;
	}

	public static class MapPropsPage {
		private DataBindingContext dbc = new DataBindingContext();
		private IObservableValue center;
		private IObservableValue zoom;

		@PostConstruct
		public void createUI(Composite parent, IGeoMapService gms, IGeoMapWidget gmWidget) {
			center = gmWidget.observeValue(IGeoMapWidget.Property.VIEW_CENTER);
			zoom = gmWidget.observeValue(IGeoMapWidget.Property.VIEW_ZOOM);

			parent.setLayout(new GridLayout());
			Label zoomLabel = new Label(parent, SWT.None);
			zoomLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			IObservableValue labelText = new ComputedValue() {
				@Override
				protected Object calculate() {
					XYZLocation loc = (XYZLocation) center.getValue();
					StringBuilder sb = new StringBuilder();
					sb.append("Zoom: ").append(zoom.getValue());

					if (loc != null) {
						sb.append(", Center: (lon=").append(loc.getX()).append(", lat=").append(loc.getY())
								.append("), Projection: ")
								.append(loc.getProjectionCode() == null ? "default" : loc.getProjectionCode());
					}

					return sb.toString();
				}
			};
			ISWTObservableValue uiZoom = WidgetProperties.text().observe(zoomLabel);
			dbc.bindValue(uiZoom, labelText);
		}

		@PreDestroy
		public void disposeUI() {
			center.dispose();
			zoom.dispose();

			dbc.dispose();
		}
	}
}
