/*******************************************************************************
 * Copyright (c) 2016 Erdal Karaca and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Erdal Karaca - initial API and implementation
 *******************************************************************************/
package org.locationtech.geoff.designer.wizards;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.ui.INewWizard;
import org.locationtech.geoff.designer.e3x.E3Wizard;
import org.locationtech.geoff.designer.internal.DesignerUtil;

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
