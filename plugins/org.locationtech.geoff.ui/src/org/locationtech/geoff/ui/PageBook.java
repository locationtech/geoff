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
package org.locationtech.geoff.ui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.locationtech.geoff.core.IGeoMapService;

/**
 * A pagebook is a general pattern that accepts input and generates an
 * appropriate UI. A pagebook is assigned to a hosting part that acts as switch
 * to any relevant parts within the environment.
 * 
 * @author Erdal Karaca
 *
 */
public interface PageBook {
	/**
	 * A method annotation that denotes that a page is to be created given the
	 * injected paramters.<br>
	 * <br>
	 * NOTE: the execution of the method will be done in the context of the
	 * input/target part. This means the state of the target part is available
	 * and not the hosting part of the pagebook.
	 */
	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Create {
	}

	/**
	 * A method annotation that denotes that no relevant part is available,
	 * hence the implementor of the method has to provide a default UI that
	 * reflects this state.<br>
	 * <br>
	 * NOTE: the execution of the method will be done in the context of the
	 * input/target part. This means the state of the target part is available
	 * and not the hosting part of the pagebook.
	 */
	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Default {
	}

	/**
	 * A method annotation that a page is about to be destroyed. Implementors
	 * can cleanup any resources or the like.<br>
	 * <br>
	 * NOTE: the execution of the method will be done in the context of the
	 * input/target part. This means the state of the target part is available
	 * and not the hosting part of the pagebook.
	 */
	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Destroy {
	}

	/**
	 * A method annotation that a part has been focused/activated or made
	 * visible. Implementors must decide whether the input/target part has any
	 * relevance to the host that may want to create a page for.<br>
	 * <br>
	 * NOTE: the execution of the method will be done in the context of the
	 * input/target part. This means the state of the target part is available
	 * and not the hosting part of the pagebook.
	 */
	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface IsRelevantPart {
	}

	/**
	 * @param type
	 *            the variable's type to query by
	 * @return a variable that is expected to be within the target part's
	 *         variables pool
	 */
	<T> T getFromTarget(Class<T> type);
}