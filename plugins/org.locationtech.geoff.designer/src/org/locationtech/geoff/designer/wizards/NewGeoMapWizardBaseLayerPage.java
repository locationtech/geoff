package org.locationtech.geoff.designer.wizards;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.core.core.logging.LogUtil;
import org.locationtech.geoff.designer.DesignerUtil;
import org.locationtech.geoff.designer.ResourcesUtil;
import org.locationtech.geoff.layer.Layer;
import org.locationtech.geoff.layer.VectorLayer;
import org.locationtech.geoff.source.SourceFormat;
import org.locationtech.geoff.source.VectorSource;

public class NewGeoMapWizardBaseLayerPage {
	private AdapterFactoryLabelProvider labelProvider;
	private TreeViewer viewer;

	@PostConstruct
	public void createUI(Composite container, IStructuredSelection selection) {
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(container);

		viewer = new TreeViewer(container);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(viewer.getControl());

		viewer.setContentProvider(new ITreeContentProvider() {

			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}

			@Override
			public void dispose() {
			}

			@Override
			public boolean hasChildren(Object element) {
				return false;
			}

			@Override
			public Object getParent(Object element) {
				return null;
			}

			@Override
			public Object[] getElements(Object inputElement) {
				return ArrayContentProvider.getInstance().getElements(inputElement);
			}

			@Override
			public Object[] getChildren(Object parentElement) {
				return new Object[0];
			}
		});
		AdapterFactory adapterFactory = DesignerUtil.createComposedAdapterFactor();
		labelProvider = new AdapterFactoryLabelProvider(adapterFactory);
		viewer.setLabelProvider(labelProvider);

		VectorSource countriesSource = Geoff.vectorSource();
		countriesSource.setUrl("data/countries.geojson");
		countriesSource.setFormat(SourceFormat.GEO_JSON);
		VectorLayer countriesLayer = Geoff.vectorLayer(countriesSource);
		countriesLayer.setShortDescription("Country Borders/Contours");
		DesignerUtil.populateDefaultStyles(countriesLayer);

		viewer.setInput(Arrays.asList(countriesLayer));
		viewer.setSelection(new StructuredSelection(countriesLayer));
	}

	@PreDestroy
	public void destroy() {
		if (labelProvider != null) {
			labelProvider.dispose();
		}
	}

	@Execute
	public boolean execute(IStructuredSelection selection) throws IOException, CoreException {
		IContainer targetContainer = getContainer(selection);

		File targetFolder = targetContainer.getLocation().toFile();
		String fileName = "geomap" + ".geoff";
		File file = new File(targetFolder, fileName);
		Geoff geoff = Geoff.createMap("New Map", "A new map...").view(Geoff.xyLocation(0, 0), 3);
		ITreeSelection structuredSelection = viewer.getStructuredSelection();

		if (!structuredSelection.isEmpty()) {
			Object firstElement = structuredSelection.getFirstElement();

			if (firstElement instanceof Layer) {
				geoff.addLayer((Layer) firstElement);
			}
		}

		ResourcesUtil.copyResources(ResourcesUtil.RESOURCES_FOLDER, targetFolder);
		ResourcesUtil.copyResources("org.locationtech.geoff.ol", ResourcesUtil.RESOURCES_FOLDER, targetFolder);

		String xml = geoff.toXML();

		try (FileOutputStream out = new FileOutputStream(file)) {
			out.write(xml.getBytes());
		}

		targetContainer.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());

		return true;
	}

	private IContainer getContainer(IStructuredSelection selection) {
		Assert.isNotNull(selection);
		Assert.isTrue(!selection.isEmpty());
		Assert.isTrue(selection.getFirstElement() instanceof IResource);

		IResource selectedResource = (IResource) selection.getFirstElement();
		IContainer targetContainer;

		if (selectedResource instanceof IContainer) {
			targetContainer = (IContainer) selectedResource;
		} else if (selectedResource instanceof IFile) {
			targetContainer = ((IFile) selectedResource).getParent();
		} else {
			throw new UnsupportedOperationException("Implement target container selection.");
		}
		return targetContainer;
	}

	@CanExecute
	public boolean canExecute() {
		// TODO evaluate selection
		return true;
	}
}
