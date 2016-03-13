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

import java.util.EventObject;
import java.util.function.Consumer;

public interface IChangeSupport {
	boolean canUndo();

	boolean canRedo();

	void undo();

	void redo();

	void save();

	void onChange(Consumer<EventObject> event);

	void batchChanges(Runnable... runnable);
}
