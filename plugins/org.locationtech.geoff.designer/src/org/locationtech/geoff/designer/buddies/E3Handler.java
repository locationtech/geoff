package org.locationtech.geoff.designer.buddies;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.handlers.HandlerUtil;
import org.locationtech.geoff.GeoffPackage;
import org.locationtech.geoff.designer.editors.GeoMapEditor;
import org.locationtech.geoff.layer.Layer;
import org.locationtech.geoff.layer.LayerPackage;
import org.locationtech.geoff.source.Source;
import org.locationtech.geoff.source.Tile;
import org.locationtech.geoff.source.Vector;
import org.osgi.framework.FrameworkUtil;

public class E3Handler extends AbstractHandler implements IHandler,
		IExecutableExtension {

	private String className;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);

		if (!(activeEditor instanceof GeoMapEditor)) {
			return null;
		}

		IEclipseContext context = (IEclipseContext) activeEditor.getSite()
				.getService(IEclipseContext.class);

		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		final IEclipseContext child = context.createChild();
		child.set(ISelection.class, currentSelection);

		SafeRunnable.run(new SafeRunnable() {

			@Override
			public void run() throws Exception {
				Class<?> type = null;
				try {
					type = FrameworkUtil.getBundle(getClass()).loadClass(
							className);
				} catch (Exception e) {
					type = Class.forName(className);
				}
				Object make = ContextInjectionFactory.make(type, child);
				ContextInjectionFactory.invoke(make, Execute.class, child);
			}
		});

		return null;
	}

	@Override
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		if (!(data instanceof String)) {
			throw new IllegalArgumentException(
					"Expecting a string parameter that is a fully qualified type name.");
		}

		className = (String) data;
	}
}
