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
package org.locationtech.geoff.showcase.swt.widgets;

import java.util.Set;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.locationtech.geoff.showcase.IExampleGeoMap;
import org.locationtech.geoff.showcase.IShowcaseRegistry;

@SuppressWarnings("serial")
public class ExamplesViewerComposite extends Composite {
	private TreeViewer viewer;

	public ExamplesViewerComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());

		SashForm container = new SashForm(this, SWT.VERTICAL);

		viewer = new TreeViewer(container, SWT.None);
		viewer.setContentProvider(new ITreeContentProvider() {
			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
			}

			public void dispose() {
			}

			public boolean hasChildren(Object element) {
				return false;
			}

			public Object getParent(Object element) {
				return null;
			}

			public Object[] getElements(Object inputElement) {
				return new ArrayContentProvider().getElements(inputElement);
			}

			public Object[] getChildren(Object parentElement) {
				return null;
			}
		});

		viewer.setLabelProvider(new LabelProvider() {
			public String getText(Object element) {
				IExampleGeoMap creator = (IExampleGeoMap) element;
				return creator.getName();
			}
		});

		Set<IExampleGeoMap> examples = IShowcaseRegistry.DEFAULT.getExamples();
		viewer.setInput(examples);

		final Text text = new Text(container, SWT.MULTI | SWT.WRAP
				| SWT.READ_ONLY | SWT.V_SCROLL);

		viewer.addPostSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				String txt = "";

				if (!event.getSelection().isEmpty()) {
					IExampleGeoMap sel = (IExampleGeoMap) ((IStructuredSelection) event
							.getSelection()).getFirstElement();
					txt = sel.getDescription();
				}

				text.setText(txt);
			}
		});

		container.setWeights(new int[] { 70, 30 });
	}

	public ColumnViewer getViewer() {
		return viewer;
	}
}
