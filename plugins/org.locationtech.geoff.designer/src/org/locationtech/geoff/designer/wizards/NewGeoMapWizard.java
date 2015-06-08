package org.locationtech.geoff.designer.wizards;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.statushandlers.StatusManager;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.ol.ResourcesUtil;

public class NewGeoMapWizard extends Wizard implements INewWizard {

	private static final String INDEX_GEOMAP_HTML = "index-geomap.html";
	private static final String RESOURCES = ResourcesUtil.RESOURCES_FOLDER;
	private IStructuredSelection selection;

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}

	@Override
	public void addPages() {
		IWizardPage page = new WizardPage("first") {

			@Override
			public void createControl(Composite parent) {
				setPageComplete(true);
				Label label = new Label(parent, SWT.None);
				label.setText("Press finish");
				setControl(label);
			}
		};
		addPage(page);
	}

	@Override
	public boolean performFinish() {
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

	private String collectStyles(List<String> resources) {
		List<String> styles = filter(resources, ".*\\.css");
		StringBuilder sb = new StringBuilder();

		for (String style : styles) {
			sb.append("<link rel='stylesheet' href='");
			sb.append(style).append("' type='text/css'>");
			sb.append("\n");
		}

		return sb.toString();
	}

	private String collectScripts(List<String> resources) {
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

	private List<String> filter(List<String> resources, String pattern) {
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

	private String replaceVars(String contents, Map<String, String> replaceVars) {
		String ret = contents;

		for (Entry<String, String> e : replaceVars.entrySet()) {
			ret = ret.replaceAll(e.getKey(), e.getValue());
		}

		return ret;
	}

	private void copyResource(String fileName, File targetFolder) {
		File targetFile = new File(targetFolder, fileName);

		try {
			InputStream stream = NewGeoMapWizard.class.getClassLoader()
					.getResourceAsStream("resource-templates/" + fileName);
			Files.copy(stream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			IStatus status = ValidationStatus.error(e.getMessage(), e);
			StatusManager.getManager().handle(status, StatusManager.SHOW);
		}
	}
}
