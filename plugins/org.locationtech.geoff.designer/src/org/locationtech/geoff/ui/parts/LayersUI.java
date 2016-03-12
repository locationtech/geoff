package org.locationtech.geoff.ui.parts;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.core.IGeoMapService;
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
	public void createPage(Composite pageContainer, IGeoMapService geoMapService) {
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
	}
}