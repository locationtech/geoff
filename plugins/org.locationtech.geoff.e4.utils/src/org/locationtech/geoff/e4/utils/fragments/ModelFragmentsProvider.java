package org.locationtech.geoff.e4.utils.fragments;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.MApplicationFactory;
import org.eclipse.e4.ui.model.application.commands.MCategory;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.commands.MCommandsFactory;
import org.eclipse.e4.ui.model.application.descriptor.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.advanced.MAdvancedFactory;
import org.locationtech.geoff.e4.utils.fragments.internal.FragmentHelper;
import org.osgi.framework.FrameworkUtil;

public abstract class ModelFragmentsProvider {
	public static final String ANY_APPLICATION = ModelFragmentsProvider.class.getName() + ".application.any";
	public static final String ANY_PERSPECTIVE_STACK = ModelFragmentsProvider.class.getName() + ".perspectivestack.any";

	public static final MBasicFactory FDESC = org.eclipse.e4.ui.model.application.descriptor.basic.MBasicFactory.INSTANCE;
	public static final MApplicationFactory FAPP = MApplicationFactory.INSTANCE;
	public static final MCommandsFactory FCOMM = MCommandsFactory.INSTANCE;
	public static final MAdvancedFactory FADV = MAdvancedFactory.INSTANCE;

	@Execute
	protected final void process(IEclipseContext ctx, MApplication app) {
		FragmentHelper.process(this, ctx, app);
	}

	protected String toBundleclassURI(Class<?> type) {
		return String.format("bundleclass://%s/%s", FrameworkUtil.getBundle(type).getSymbolicName(),
				type.getCanonicalName());
	}

	protected String toPlatformURI(String path) {
		return String.format("platform:/plugin/%s/%s", FrameworkUtil.getBundle(getClass()).getSymbolicName(), path);
	}

	protected MCommand commandRef(String commandId) {
		MCommand cmdRef = FCOMM.createCommand();
		cmdRef.setElementId(commandId);
		return cmdRef;
	}

	protected MCategory categoryRef(String categoryId) {
		MCategory cat = FCOMM.createCategory();
		cat.setElementId(categoryId);
		return null;
	}
}
