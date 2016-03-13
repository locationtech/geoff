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
package org.locationtech.geoff.core;

import java.util.function.Supplier;

public class SyncInit<T> {
	private Object mutex = new Object();
	private T t;
	private final Supplier<T> supplier;
	
	private SyncInit(Supplier<T> sup) {
		this.supplier = sup;
	}

	public T get() {
		if (t == null) {
			synchronized (mutex) {
				if (t == null) {
					t = supplier.get();
				}
			}
		}
		
		return t;
	}

	public static <T> SyncInit<T> create(Supplier<T> sup) {
		return new SyncInit<T>(sup);
	}
}
