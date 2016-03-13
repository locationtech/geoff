/*******************************************************************************
 * Copyright (c) 2000, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Erdal Karaca, 2016 - adapted/extended to E4 environments
 *******************************************************************************/
package org.locationtech.geoff.ui.internal;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.InjectionException;
import org.eclipse.e4.core.internal.contexts.EclipseContext;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.IPartListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.locationtech.geoff.ui.PageBook;

/**
 * A pagebook is a composite control where only a single control is visible at a
 * time. It is similar to a notebook, but without tabs.
 *
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
@SuppressWarnings("restriction")
public final class E4PageBookSWTImpl extends Composite implements PageBook {

	/**
	 * The current control; <code>null</code> if none.
	 */
	private Control currentPage = null;
	private Control defaultPage = null;

	private MPart hostingPart;
	private IEclipseContext ctx;
	private EPartService partService;

	private IPartListener listener = new IPartListener() {

		@Override
		public void partVisible(MPart part) {
			processPart(part);
		}

		@Override
		public void partHidden(MPart part) {
		}

		@Override
		public void partDeactivated(MPart part) {

		}

		@Override
		public void partBroughtToTop(MPart part) {
			processPart(part);
		}

		@Override
		public void partActivated(MPart part) {
			processPart(part);
		}
	};

	/**
	 * Creates a new empty pagebook.
	 */
	public E4PageBookSWTImpl(Composite parent, IEclipseContext ctx, MPart hostingPart) {
		super(parent, SWT.None);
		this.ctx = ctx;
		this.hostingPart = hostingPart;
		setLayout(new PageBookLayout());

		partService = ctx.get(EPartService.class);
		partService.addPartListener(listener);

		addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent e) {
				destroyPages();
				partService.removePartListener(listener);
				partService = null;
				E4PageBookSWTImpl.this.ctx = null;
				E4PageBookSWTImpl.this.hostingPart = null;
			}
		});
	}

	/**
	 * Shows the given page. This method has no effect if the given page is not
	 * contained in this pagebook.
	 *
	 * @param page
	 *            the page to show
	 */
	private void showPage(Control page) {
		if (page == null || page.isDisposed() || page.getParent() != this) {
			return;
		}

		currentPage = page;

		// show new page
		page.setVisible(true);
		layout(true);

		// hide old (and all others) *after* new page has been made visible in
		// order to avoid flashing
		Control[] children = getChildren();
		for (int i = 0; i < children.length; i++) {
			Control child = children[i];
			if (child != page && !child.isDisposed()) {
				child.setVisible(false);
			}
		}
	}

	private void destroyPages() {
		for (Control control : getChildren()) {
			destroyPage(control, false);
		}
	}

	private void destroyPage(Control control, boolean dispose) {
		IEclipseContext ctx = (IEclipseContext) control.getData(IEclipseContext.class.getName());
		Object host = hostingPart.getObject();

		try {
			ContextInjectionFactory.invoke(host, Destroy.class, ctx);
		} catch (InjectionException ie) {
			// assume there is no DestroyPage annotation
		}

		if (dispose) {
			control.dispose();
		}
	}

	private void processPart(MPart part) {
		boolean self = part == hostingPart;

		if (self) {
			return;
		}

		processRelevantPart(part);
	}

	private void processRelevantPart(MPart part) {
		Object host = hostingPart.getObject();
		IEclipseContext partContext = part.getContext();
		Boolean isRelevant = (Boolean) ContextInjectionFactory.invoke(host, IsRelevantPart.class, partContext);

		if (isRelevant) {
			Composite toBeShown = getContainer(part);

			if (toBeShown == null) {
				final Composite container = new Composite(E4PageBookSWTImpl.this, SWT.None);
				container.setLayout(new FillLayout());
				IEclipseContext child = partContext.createChild();
				container.setData(IEclipseContext.class.getName(), child);
				container.setData(MPart.class.getName(), part);
				((EclipseContext) partContext).notifyOnDisposal((c) -> {
					if (container.isDisposed()) {
						return;
					}
					
					destroyPage(container, true);

					if (currentPage == container) {
						// unset currentPage to make the default page show up
						currentPage = null;
					}

					showDefaultPage(host);
				});
				child.set(Composite.class, container);
				// part's context as local context
				ContextInjectionFactory.invoke(host, Create.class, child);
				toBeShown = container;
			}

			showPage(toBeShown);
		} else {
			showDefaultPage(host);
		}
	}

	private void showDefaultPage(Object host) {
		if (currentPage != null) {
			// show the last valid page if it was set
			showPage(currentPage);
			return;
		}

		if (defaultPage == null || defaultPage.isDisposed()) {
			Composite container = new Composite(E4PageBookSWTImpl.this, SWT.None);
			container.setLayout(new FillLayout());
			IEclipseContext child = ctx.createChild();
			container.setData(IEclipseContext.class.getName(), child);
			child.set(Composite.class, container);
			ContextInjectionFactory.invoke(host, Default.class, child);
			defaultPage = container;
		}

		showPage(defaultPage);
	}

	private Composite getContainer(MPart part) {
		for (Control child : getChildren()) {
			MPart childPart = (MPart) child.getData(MPart.class.getName());

			if (childPart == part) {
				return (Composite) child;
			}
		}

		return null;
	}

	/**
	 * <p>
	 * [Issue: This class should be declared private.]
	 * </p>
	 */
	private class PageBookLayout extends Layout {

		@Override
		protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
			if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT) {
				return new Point(wHint, hHint);
			}

			Point result = null;
			if (currentPage != null) {
				result = currentPage.computeSize(wHint, hHint, flushCache);
			} else {
				// Rectangle rect= composite.getClientArea();
				// result= new Point(rect.width, rect.height);
				result = new Point(0, 0);
			}
			if (wHint != SWT.DEFAULT) {
				result.x = wHint;
			}
			if (hHint != SWT.DEFAULT) {
				result.y = hHint;
			}
			return result;
		}

		@Override
		protected void layout(Composite composite, boolean flushCache) {
			if (currentPage != null && !currentPage.isDisposed()) {
				currentPage.setBounds(composite.getClientArea());
			}
		}
	}
}
