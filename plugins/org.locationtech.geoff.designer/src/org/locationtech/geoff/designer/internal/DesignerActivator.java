package org.locationtech.geoff.designer.internal;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.FrameworkUtil;

public class DesignerActivator extends AbstractUIPlugin {

	public static ImageDescriptor imageDescriptor(String imageFilePath) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(
				FrameworkUtil.getBundle(DesignerActivator.class).getSymbolicName(), imageFilePath);
	}
}
