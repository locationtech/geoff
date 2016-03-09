package org.locationtech.geoff.designer.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.designer.IGeoMapService;
import org.locationtech.geoff.designer.databinding.ActionsObservables;
import org.locationtech.geoff.designer.internal.DesignerActivator;
import org.locationtech.geoff.layer.Layer;
import org.locationtech.geoff.layer.LayerPackage;
import org.locationtech.geoff.layer.TileLayer;
import org.locationtech.geoff.layer.VectorLayer;
import org.locationtech.geoff.source.Source;
import org.locationtech.geoff.source.SourcePackage;

public class LayersUI {
	@Inject
	private DataBindingContext dbc;

	@Inject
	private IWorkbenchSite site;

	private IGeoMapService geoMapService;

	private TreeViewer layersViewer;
	private IViewerObservableValue singleSelectionObservable;
	private WritableValue masterObservable = new WritableValue();

	@PostConstruct
	public void createUI(Composite parent, IToolBarManager toolBarManager) {
		AdapterFactory af = geoMapService.adaptTo(AdapterFactory.class);

		AdapterFactoryLabelProvider aflp = new AdapterFactoryLabelProvider(af);
		AdapterFactoryContentProvider afcp = new AdapterFactoryContentProvider(af) {
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

		layersViewer = new TreeViewer(parent);
		layersViewer.setContentProvider(afcp);
		layersViewer.setLabelProvider(aflp);

		singleSelectionObservable = ViewersObservables.observeSingleSelection(layersViewer);
		IObservableValue inputObservable = ViewersObservables.observeInput(layersViewer);
		dbc.bindValue(inputObservable, masterObservable);

		site.setSelectionProvider(layersViewer);
		makeContributions(toolBarManager, parent.getShell());
	}

	@Inject
	public void setGeoMap(IGeoMapService geoMapService) {
		this.geoMapService = geoMapService;
		masterObservable.setValue(geoMapService.adaptTo(GeoMap.class));
	}

	private void makeContributions(IToolBarManager toolBarManager, final Shell shell) {
		{
			Action deleteAction = new Action("Delete Layer/s",
					DesignerActivator.imageDescriptor("/icons/actions16/delete.gif")) {
				@Override
				public void run() {
					List<Layer> list = ((IStructuredSelection) layersViewer.getSelection()).toList();

					geoMapService.batchChanges(() -> {
						list.forEach((l) -> geoMapService.removeLayer(l));
					});
				}
			};

			ActionsObservables.bindEnabled(dbc, deleteAction, singleSelectionObservable);

			toolBarManager.add(deleteAction);
		}

		boolean addGenericLayerSelection = false;

		if (addGenericLayerSelection) {
			Action newAction = new Action("New Layer...",
					DesignerActivator.imageDescriptor("/icons/actions16/add.gif")) {
				@SuppressWarnings("unchecked")
				@Override
				public void run() {
					Collection<Source> sources = (Collection<Source>) Geoff.samplesOf(SourcePackage.Literals.SOURCE);
					AdapterFactory af = geoMapService.adaptTo(AdapterFactory.class);
					AdapterFactoryLabelProvider lp = new AdapterFactoryLabelProvider(af);
					ElementListSelectionDialog diag = new ElementListSelectionDialog(shell, lp);
					diag.setMessage("Choose a source to create a new layer");
					diag.setMultipleSelection(false);
					diag.setBlockOnOpen(true);
					diag.setElements(sources.toArray());
					diag.open();
					Object[] result = diag.getResult();
					lp.dispose();

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

							Source sample = (Source) object;
							Layer layer = Geoff.instance(type);
							Source source = Geoff.instance(sample.eClass());
							layer.setSource(source);
						}

						geoMapService.batchChanges(() -> {
							layers.forEach((l) -> geoMapService.addLayer(l));
						});
					}
				}
			};

			toolBarManager.add(newAction);
		}
	}
}