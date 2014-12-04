package org.locationtech.geoff.designer.views;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
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
import org.locationtech.geoff.GeoffPackage;
import org.locationtech.geoff.Identifiable;
import org.locationtech.geoff.designer.IEditingService;
import org.locationtech.geoff.designer.databinding.ActionsObservables;
import org.locationtech.geoff.layer.Layer;
import org.locationtech.geoff.layer.LayerPackage;
import org.locationtech.geoff.provider.GeoffEditPlugin;
import org.locationtech.geoff.source.Source;
import org.locationtech.geoff.source.SourcePackage;
import org.locationtech.geoff.source.Tile;
import org.locationtech.geoff.source.Vector;

public class LayersUI {
	@Inject
	private DataBindingContext dbc;

	@Inject
	private IWorkbenchSite site;

	@Inject
	private AdapterFactory af;

	@Inject
	private IEditingService editingService;

	private TreeViewer layersViewer;
	private IViewerObservableValue singleSelectionObservable;
	private WritableValue masterObservable = new WritableValue();

	@PostConstruct
	public void createUI(Composite parent, IToolBarManager toolBarManager) {
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

		layersViewer = new TreeViewer(parent);
		layersViewer.setContentProvider(afcp);
		layersViewer.setLabelProvider(aflp);

		singleSelectionObservable = ViewersObservables
				.observeSingleSelection(layersViewer);
		IObservableValue inputObservable = ViewersObservables
				.observeInput(layersViewer);
		dbc.bindValue(inputObservable, masterObservable);

		site.setSelectionProvider(layersViewer);
		makeContributions(toolBarManager, parent.getShell());
	}

	@Inject
	public void setGeoMap(GeoMap geoMap) {
		masterObservable.setValue(geoMap);
	}

	private void makeContributions(IToolBarManager toolBarManager,
			final Shell shell) {
		{
			Action deleteAction = new Action("Delete Layer/s",
					GeoffEditPlugin.INSTANCE
							.getImageDescriptor("actions16/delete")) {
				@Override
				public void run() {
					List list = ((IStructuredSelection) layersViewer
							.getSelection()).toList();

					CompoundCommand cc = new CompoundCommand();
					cc.setLabel(getText());

					for (Object obj : list) {
						if (obj instanceof Identifiable) {
							Command delete = editingService
									.createDeleteCommand((Identifiable) obj);
							cc.append(delete);
						}
					}

					editingService.execute(cc);
				}
			};

			ActionsObservables.bindEnabled(dbc, deleteAction,
					singleSelectionObservable);

			toolBarManager.add(deleteAction);
		}

		{
			Action newAction = new Action("New Layer...",
					GeoffEditPlugin.INSTANCE
							.getImageDescriptor("actions16/add")) {
				@SuppressWarnings("unchecked")
				@Override
				public void run() {
					GeoMap geoMap = (GeoMap) masterObservable.getValue();
					// Collection<Layer> layers = (Collection<Layer>)
					// getEditingService()
					// .samplesOf(LayerPackage.Literals.LAYER);
					Collection<Source> sources = (Collection<Source>) editingService
							.samplesOf(SourcePackage.Literals.SOURCE);
					AdapterFactoryLabelProvider lp = new AdapterFactoryLabelProvider(
							af);
					ElementListSelectionDialog diag = new ElementListSelectionDialog(
							shell, lp);
					diag.setMessage("Choose a source to create a new layer");
					diag.setMultipleSelection(false);
					diag.setBlockOnOpen(true);
					diag.setElements(sources.toArray());
					diag.open();
					Object[] result = diag.getResult();
					lp.dispose();

					if (result != null) {
						CompoundCommand cc = new CompoundCommand();

						for (Object object : result) {
							EClass type = null;

							if (object instanceof Tile) {
								type = LayerPackage.Literals.TILE;
							} else if (object instanceof Vector) {
								type = LayerPackage.Literals.VECTOR;
							}

							if (type == null) {
								continue;
							}

							Source sample = (Source) object;
							Layer layer = editingService.createInstance(type);
							Source source = editingService
									.createInstance(sample.eClass());
							layer.setSource(source);
							Command command = editingService.createAddCommand(
									geoMap,
									GeoffPackage.Literals.GEO_MAP__LAYERS,
									layer);
							cc.append(command);
						}

						editingService.execute(cc);
					}
				}
			};

			toolBarManager.add(newAction);
		}
	}
}