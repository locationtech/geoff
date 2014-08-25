package org.locationtech.geoff.designer.views;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledToolItem;
import org.eclipse.e4.ui.model.application.ui.menu.MToolBar;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.designer.commands.AddLayerHandler;

public class LayersView {

	private TreeViewer treeViewer;

	@PostConstruct
	public void createUI(Composite parent, EMenuService ms, MPart part,
			EModelService modelService) {
		parent.setLayout(new FillLayout());
		AdapterFactory af = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		AdapterFactoryLabelProvider aflp = new AdapterFactoryLabelProvider(af);
		AdapterFactoryContentProvider afcp = new AdapterFactoryContentProvider(
				af) {
			@Override
			public boolean hasChildren(Object object) {
				// only flat list
				return false;
			}

			@Override
			public Object[] getElements(Object object) {
				if (object instanceof GeoMap) {
					return ((GeoMap) object).getLayers().toArray();
				}

				return new Object[0];
			}
		};
		treeViewer = new TreeViewer(parent);
		treeViewer.setContentProvider(afcp);
		treeViewer.setLabelProvider(aflp);

//		MHandledToolItem addLayersToolItem = modelService
//				.createModelElement(MHandledToolItem.class);
//		
//		MCommand command = modelService.createModelElement(MCommand.class);
//		addLayersToolItem.setCommand(command);
//		addLayersToolItem.setLabel("Add Layer");
		MToolBar tb = modelService.createModelElement(MToolBar.class);
//		tb.getChildren().add(addLayersToolItem);
		part.setToolbar(tb);
		ms.registerContextMenu(treeViewer.getControl(), part.getElementId());
	}

	@Inject
	@Optional
	public void setMap(GeoMap map) {
		treeViewer.setInput(map);

		IEditingDomainProvider editingDomainProvider = (IEditingDomainProvider) EcoreUtil
				.getExistingAdapter(map, IEditingDomainProvider.class);
		System.out.println(editingDomainProvider);
	}
}
