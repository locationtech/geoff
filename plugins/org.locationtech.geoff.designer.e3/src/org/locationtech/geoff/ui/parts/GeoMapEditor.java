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
package org.locationtech.geoff.ui.parts;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.locationtech.geoff.ui.PersistAs;

public class GeoMapEditor extends EditorPart {

	private MPart mPart;
	private GeoMapUI mapUI;

	@Override
	public void doSave(IProgressMonitor monitor) {
		ContextInjectionFactory.invoke(mapUI, Persist.class, getContext());
	}

	@Override
	public void doSaveAs() {
		ContextInjectionFactory.invoke(mapUI, PersistAs.class, getContext());
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
		String name;

		if (input instanceof FileEditorInput) {
			FileEditorInput fInput = (FileEditorInput) getEditorInput();
			IFile file = fInput.getFile();
			getContext().set(IFile.class, file);
			name = file.getProject().getName() + "/" + file.getName();
		} else {
			throw new UnsupportedOperationException("Provided editor input not supported: " + input);
		}
		
		setPartName(name);
		mPart = getContext().get(MPart.class);
	}

	private IEclipseContext getContext() {
		return (IEclipseContext) getEditorSite().getService(IEclipseContext.class);
	}

	@Override
	public boolean isDirty() {
		return mPart.isDirty();
	}

	@Override
	public void createPartControl(Composite parent) {
		IEclipseContext context = getContext();
		mapUI = ContextInjectionFactory.make(GeoMapUI.class, context);
	}

	@Override
	public void setFocus() {
		ContextInjectionFactory.invoke(mapUI, Focus.class, getContext());
	}
}
