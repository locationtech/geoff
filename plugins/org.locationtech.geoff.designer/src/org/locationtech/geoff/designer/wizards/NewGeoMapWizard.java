package org.locationtech.geoff.designer.wizards;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.ui.INewWizard;
import org.locationtech.geoff.designer.DesignerUtil;
import org.locationtech.geoff.designer.e3x.E3Wizard;

public class NewGeoMapWizard extends E3Wizard implements INewWizard {

	@Override
	protected List<IWizardPage> doCreatePages() {
		setWindowTitle("New Geo Map");
		ImageDescriptor imageDescriptor = DesignerUtil.getImageDescriptor(DesignerUtil.ICONS_GEOFF_WIZBAN_PNG);
		setDefaultPageImageDescriptor(imageDescriptor);

		IWizardPage firstPage = createPage(NewGeoMapWizardBaseLayerPage.class);
		firstPage.setDescription("Select base layer settings.");
		firstPage.setTitle("Base layer");

		return Arrays.asList(firstPage);
	}
}
