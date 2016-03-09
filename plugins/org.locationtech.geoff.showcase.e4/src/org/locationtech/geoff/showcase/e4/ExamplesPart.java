/*******************************************************************************
 * Copyright (c) 2014 Erdal Karaca.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Erdal Karaca - initial API and implementation
 ******************************************************************************/
package org.locationtech.geoff.showcase.e4;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.locationtech.geoff.showcase.IExampleGeoMap;
import org.locationtech.geoff.showcase.swt.widgets.ExamplesViewerComposite;

public class ExamplesPart {
	private ExamplesViewerComposite viewerComposite;

	@Inject
	public ExamplesPart(Composite parent, final IEclipseContext context) {
		parent.setLayout(new FillLayout());

		viewerComposite = new ExamplesViewerComposite(parent, SWT.None);

		viewerComposite.getViewer().addPostSelectionChangedListener(
				new ISelectionChangedListener() {
					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						context.getParent().set(
								IExampleGeoMap.class,
								(IExampleGeoMap) ((IStructuredSelection) event
										.getSelection()).getFirstElement());
					}
				});
	}

	@Focus
	public void onFocus() {
		viewerComposite.setFocus();
	}

}
