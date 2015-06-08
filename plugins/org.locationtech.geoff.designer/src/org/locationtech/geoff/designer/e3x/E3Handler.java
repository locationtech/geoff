package org.locationtech.geoff.designer.e3x;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.osgi.framework.FrameworkUtil;

public class E3Handler extends AbstractHandler implements IHandler, IExecutableExtension {

	public static final String PARAM_PARAMETERS = "parameters";
	private String className;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);

		if (activeEditor == null) {
			return null;
		}

		IEclipseContext context = (IEclipseContext) activeEditor.getSite().getService(IEclipseContext.class);

		if (context == null) {
			return null;
		}

		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		final IEclipseContext child = context.createChild();
		child.set(ISelection.class, currentSelection);
		child.set(PARAM_PARAMETERS, event.getParameters());

		SafeRunnable.run(new SafeRunnable() {

			@Override
			public void run() throws Exception {
				Class<?> type = null;
				try {
					type = FrameworkUtil.getBundle(getClass()).loadClass(className);
				} catch (Exception e) {
					type = Class.forName(className);
				}
				Object make = ContextInjectionFactory.make(type, child);
				ContextInjectionFactory.invoke(make, Execute.class, child);
			}
		});
		
		child.dispose();
		return null;
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
			throws CoreException {
		if (!(data instanceof String)) {
			throw new IllegalArgumentException("Expecting a string parameter that is a fully qualified type name.");
		}

		className = (String) data;
	}
}
