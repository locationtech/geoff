package org.locationtech.geoff.designer.wizards;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
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
import org.eclipse.ui.statushandlers.StatusManager;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.designer.DesignerUtil;
import org.locationtech.geoff.layer.Layer;
import org.locationtech.geoff.layer.VectorLayer;
import org.locationtech.geoff.ol.ResourcesUtil;
import org.locationtech.geoff.source.SourceFormat;
import org.locationtech.geoff.source.VectorSource;

public class NewGeoMapWizardBaseLayerPage {
	private static final String INDEX_GEOMAP_HTML = "index-geomap.html";
	private static final String RESOURCES = ResourcesUtil.RESOURCES_FOLDER;
	private AdapterFactoryLabelProvider labelProvider;
	private TreeViewer viewer;

	@PostConstruct
	public void createUI(Composite container) {
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
	public boolean execute(IStructuredSelection selection) {
		IResource selectedResource = (IResource) selection.getFirstElement();
		IContainer targetContainer;

		if (selectedResource instanceof IContainer) {
			targetContainer = (IContainer) selectedResource;
		} else if (selectedResource instanceof IFile) {
			targetContainer = ((IFile) selectedResource).getParent();
		} else {
			throw new UnsupportedOperationException("Implement target container selection.");
		}

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

		String xml = geoff.toXML();

		try (FileOutputStream out = new FileOutputStream(file)) {
			out.write(xml.getBytes());
		} catch (Exception e) {
			IStatus status = ValidationStatus.error(e.getMessage(), e);
			StatusManager.getManager().handle(status, StatusManager.SHOW);
		}

		try {
			List<String> resources = new ArrayList<>();
			List<String> copyResources = ResourcesUtil.copyResources(NewGeoMapWizard.class, RESOURCES, targetFolder);
			List<String> copyResources2 = ResourcesUtil.copyResources(ResourcesUtil.RESOURCES_FOLDER, targetFolder);
			resources.addAll(copyResources);
			resources.addAll(copyResources2);

			String indexGeomapHtml = ResourcesUtil.readStream(NewGeoMapWizard.class.getClassLoader()
					.getResourceAsStream("resource-templates/" + INDEX_GEOMAP_HTML));
			Map<String, String> replaceVars = new HashMap<String, String>();
			String styles = collectStyles(resources);
			String scripts = collectScripts(resources);

			replaceVars.put("\\{\\{GEOMAP_FILE_NAME\\}\\}", fileName);
			replaceVars.put("\\{\\{STYLES\\}\\}", styles);
			replaceVars.put("\\{\\{SCRIPTS\\}\\}", scripts);

			indexGeomapHtml = replaceVars(indexGeomapHtml, replaceVars);

			Files.copy(new ByteArrayInputStream(indexGeomapHtml.getBytes()),
					new File(targetFolder, INDEX_GEOMAP_HTML).toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			IStatus status = ValidationStatus.error(e.getMessage(), e);
			StatusManager.getManager().handle(status, StatusManager.SHOW);
		}

		try {
			targetContainer.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
		}

		return true;
	}

	@CanExecute
	public boolean canExecute() {
		// TODO evaluate selection
		return true;
	}

	private static String collectStyles(List<String> resources) {
		List<String> styles = filter(resources, ".*\\.css");
		StringBuilder sb = new StringBuilder();

		for (String style : styles) {
			sb.append("<link rel='stylesheet' href='");
			sb.append(style).append("' type='text/css'>");
			sb.append("\n");
		}

		return sb.toString();
	}

	private static String collectScripts(List<String> resources) {
		List<String> styles = filter(resources, ".*\\.js");
		final List<String> order = Arrays.asList("js/jquery-1.10.2.min.js", "js/jquery-ui-1.10.4.min.js", "js/ol.js",
				"js/scriptloader", "js/geoff-ol3.js");

		Collections.sort(styles, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				int io1 = order.indexOf(o1);

				if (io1 < 0) {
					return -1;
				}

				int io2 = order.indexOf(o2);

				if (io2 < 0) {
					return 1;
				}

				if (io1 == io2) {
					return 0;
				}

				return io1 > io2 ? 1 : -1;
			}
		});

		StringBuilder sb = new StringBuilder();

		for (String style : styles) {
			sb.append("<script src='");
			sb.append(style).append("'></script>");
			sb.append("\n");
		}

		return sb.toString();
	}

	private static List<String> filter(List<String> resources, String pattern) {
		if (resources.isEmpty()) {
			return Collections.emptyList();
		}

		List<String> filtered = new ArrayList<>();

		for (String file : resources) {
			if (file.matches(pattern)) {
				filtered.add(file);
			}
		}

		return filtered;
	}

	private static String replaceVars(String contents, Map<String, String> replaceVars) {
		String ret = contents;

		for (Entry<String, String> e : replaceVars.entrySet()) {
			ret = ret.replaceAll(e.getKey(), e.getValue());
		}

		return ret;
	}
}
