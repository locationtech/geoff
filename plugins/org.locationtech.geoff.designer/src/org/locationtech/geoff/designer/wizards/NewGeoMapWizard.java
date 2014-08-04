package org.locationtech.geoff.designer.wizards;

import java.io.File;
import java.io.FileOutputStream;

import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
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

public class NewGeoMapWizard extends Wizard implements INewWizard {

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
			throw new UnsupportedOperationException(
					"Implement target container selection.");
		}

		File targetFolder = targetContainer.getLocation().toFile();
		File file = new File(targetFolder, "new-map" + ".geoff");
		Geoff geoff = Geoff.createMap("New Map", "A new map...");
		String xml = geoff.toXML();

		try (FileOutputStream out = new FileOutputStream(file)) {
			out.write(xml.getBytes());
		} catch (Exception e) {
			IStatus status = ValidationStatus.error(e.getMessage(), e);
			StatusManager.getManager().handle(status, StatusManager.SHOW);
		}

		return true;
	}
}
