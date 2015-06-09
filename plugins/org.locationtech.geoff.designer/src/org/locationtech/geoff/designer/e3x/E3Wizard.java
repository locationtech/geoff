package org.locationtech.geoff.designer.e3x;

import java.util.List;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public abstract class E3Wizard extends Wizard implements INewWizard {

	private static final class E3WizardPage extends WizardPage {
		private final Class<?> pageClass;
		private final IEclipseContext pageContext;
		private Object page;

		private E3WizardPage(String pageName, Class<?> pageClass, IEclipseContext wizardContext) {
			super(pageName);
			this.pageClass = pageClass;

			// give each page its own context
			this.pageContext = wizardContext.createChild();
		}

		@Override
		public void createControl(Composite parent) {
			Composite container = new Composite(parent, SWT.NONE);
			pageContext.set(Composite.class, container);
			pageContext.set(IWizardPage.class, this);
			pageContext.set(IWizard.class, getWizard());
			page = ContextInjectionFactory.make(pageClass, pageContext);
			setControl(container);
		}

		@Override
		public void dispose() {
			pageContext.dispose();
			super.dispose();
		}

		@Override
		public boolean isPageComplete() {
			Object result = ContextInjectionFactory.invoke(page, CanExecute.class, pageContext);
			return Boolean.TRUE.equals(result);
		}
	}

	private IEclipseContext wizardContext;

	@Override
	public boolean performFinish() {
		for (IWizardPage page : getPages()) {
			if (page instanceof E3WizardPage) {
				E3WizardPage e3WizardPage = (E3WizardPage) page;
				IEclipseContext pageContext = e3WizardPage.pageContext;
				Object pagePOJO = e3WizardPage.page;
				Object invoke = ContextInjectionFactory.invoke(pagePOJO, Execute.class, pageContext);

				if (invoke == null) {
					// assume null or void means OK
					continue;
				}

				if (!Boolean.TRUE.equals(invoke)) {
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		IEclipseContext workbenchContext = workbench.getService(IEclipseContext.class);
		wizardContext = workbenchContext.createChild(getClass().getName());
		wizardContext.set(IStructuredSelection.class, selection);
		wizardContext.set(IDialogSettings.class, getDialogSettings());
	}

	@Override
	public void dispose() {
		wizardContext.dispose();
		super.dispose();
	}

	protected IWizardPage createPage(final Class<?> pageClass) {
		String name = pageClass.getName();
		E3WizardPage page = new E3WizardPage(name, pageClass, wizardContext);
		return page;
	}

	@Override
	public void addPages() {
		List<IWizardPage> pages = doCreatePages();

		for (IWizardPage page : pages) {
			addPage(page);
		}
	}

	protected abstract List<IWizardPage> doCreatePages();
}
