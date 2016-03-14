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
package org.locationtech.geoff.ui.internal;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.locationtech.geoff.ui.PageBook;
import org.osgi.service.component.annotations.Component;

@Component(property = "service.context.key:String=org.locationtech.geoff.ui.PageBook", service = IContextFunction.class)
public class PageBookFunction extends ContextFunction {

	@Override
	public Object compute(IEclipseContext context, String contextKey) {
		PageBook pageBook = ContextInjectionFactory.make(E4PageBookSWTImpl.class, context);
		return pageBook;
	}

}
