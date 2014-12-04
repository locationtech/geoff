package org.locationtech.geoff.designer.buddies;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.IPageBookViewPage;
import org.eclipse.ui.part.MessagePage;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.part.PageBookView;
import org.locationtech.geoff.designer.editors.GeoMapEditor;
import org.osgi.framework.FrameworkUtil;

public class E3PageBookViewPart extends PageBookView implements
		IExecutableExtension {
	private String className;

	@Override
	protected IPage createDefaultPage(PageBook book) {
		MessagePage page = new MessagePage();
		initPage(page);
		page.createControl(book);
		page.setMessage("No view input available.");
		return page;
	}

	@Override
	protected final PageRec doCreatePage(IWorkbenchPart part) {
		if (part instanceof GeoMapEditor) {
			final GeoMapEditor editor = (GeoMapEditor) part;
			IEclipseContext editorContext = (IEclipseContext) editor.getSite()
					.getService(IEclipseContext.class);
			E3PageBookPage page = doCreateEditorPage(editorContext);
			initPage((IPageBookViewPage) page);
			page.createControl(getPageBook());
			PageRec pageRec = new PageRec(part, page);
			return pageRec;
		}

		return null;
	}

	protected E3PageBookPage doCreateEditorPage(final IEclipseContext context) {
		return new E3PageBookPage() {

			@Override
			protected Control doCreateControl(Composite parent) {
				Composite container = new Composite(parent, SWT.None);
				container.setLayout(new FillLayout());
				final IEclipseContext child = context.createChild();
				child.set(DataBindingContext.class, getDataBindingContext());
				child.set(Composite.class, container);
				child.set(IWorkbenchSite.class, getSite());
				child.set(IToolBarManager.class, getSite().getActionBars()
						.getToolBarManager());
				SafeRunnable.run(new SafeRunnable() {

					@Override
					public void run() throws Exception {
						Class<?> type = FrameworkUtil.getBundle(getClass())
								.loadClass(className);
						ContextInjectionFactory.make(type, child);
					}
				});
				return container;
			}
		};
	}

	@Override
	protected final void doDestroyPage(IWorkbenchPart part, PageRec pageRecord) {
		pageRecord.page.dispose();
		pageRecord.dispose();
	}

	@Override
	protected final IWorkbenchPart getBootstrapPart() {
		IWorkbenchPage page = getSite().getPage();

		if (page != null) {
			return page.getActiveEditor();
		}

		return null;
	}

	@Override
	protected final boolean isImportant(IWorkbenchPart part) {
		return part instanceof GeoMapEditor;
	}

	@Override
	public Object getAdapter(Class key) {
		Object adapter = super.getAdapter(key);

		if (adapter == null) {
			IPage currentPage = getCurrentPage();

			if (currentPage != null) {
				PageRec pageRec = getPageRec(currentPage);

				if (pageRec != null && pageRec.part != null) {
					adapter = pageRec.part.getAdapter(key);
				}
			}
		}

		return adapter;
	}

	@Override
	public void setInitializationData(IConfigurationElement cfig,
			String propertyName, Object data) {
		if (!(data instanceof String)) {
			throw new IllegalArgumentException(
					"Expecting a string parameter that is a fully qualified type name.");
		}

		className = (String) data;
	}
}
