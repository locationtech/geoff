package org.locationtech.geoff.designer.wizards;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
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
	private static final String RESOURCES = "resources";
	private static final String JS = "js";
	private static final String CSS = "css";
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

		File resourcesFolder = new File(targetFolder, RESOURCES);
		resourcesFolder.mkdirs();
		File jsFolder = new File(targetFolder, JS);
		jsFolder.mkdirs();
		File cssFolder = new File(targetFolder, CSS);
		cssFolder.mkdirs();

		copyGeoffOlResource(ResourcesUtil.GEOFF_OL_JS, jsFolder);
		copyGeoffOlResource(ResourcesUtil.JQUERY_MIN_JS, jsFolder);
		copyGeoffOlResource(ResourcesUtil.OL_JS, jsFolder);
		copyGeoffOlResource(ResourcesUtil.OL_CSS, cssFolder);

		copyResource("countries.geojson", resourcesFolder);

		try {
			String indexGeomapHtml = ResourcesUtil.readStream(NewGeoMapWizard.class.getClassLoader()
					.getResourceAsStream("resource-templates/" + INDEX_GEOMAP_HTML));
			Map<String, String> replaceVars = new HashMap<String, String>();
			replaceVars.put("\\{\\{GEOMAP_FILE_NAME\\}\\}", fileName);
			replaceVars.put("\\{\\{OL_CSS\\}\\}", CSS + "/" + ResourcesUtil.OL_CSS);
			replaceVars.put("\\{\\{OL_JS\\}\\}", JS + "/" + ResourcesUtil.OL_JS);
			replaceVars.put("\\{\\{JQUERY_MIN_JS\\}\\}", JS + "/" + ResourcesUtil.JQUERY_MIN_JS);
			replaceVars.put("\\{\\{GEOFF_OL_JS\\}\\}", JS + "/" + ResourcesUtil.GEOFF_OL_JS);

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

	private void copyGeoffOlResource(String geoffResourceFileName, File targetFolder) {
		File targetFile = new File(targetFolder, geoffResourceFileName);

		try {
			InputStream stream = ResourcesUtil.readResourceAsStream(geoffResourceFileName);
			Files.copy(stream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			IStatus status = ValidationStatus.error(e.getMessage(), e);
			StatusManager.getManager().handle(status, StatusManager.SHOW);
		}
	}
}
