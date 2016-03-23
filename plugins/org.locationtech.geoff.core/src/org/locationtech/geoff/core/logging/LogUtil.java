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
package org.locationtech.geoff.core.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogUtil {
	public static interface RunnableWithException {
		public void run() throws Exception;
	}

	public static boolean logErrorOnException(RunnableWithException runnable) {
		return logErrorOnException(LogUtil.class, runnable);
	}

	public static boolean logErrorOnException(Class<?> context, RunnableWithException runnable) {
		try {
			runnable.run();
		} catch (Exception e) {
			getLogger(context).log(Level.SEVERE, e.getMessage(), e);
			return false;
		}

		return true;
	}

	private static Logger getLogger(Class<?> context) {
		return Logger.getLogger(context.getName());
	}

	public static void warn(Class<?> context, String msg) {
		getLogger(context).warning(msg);
	}

	public static void error(Class<?> context, String msg) {
		getLogger(context).severe(msg);
	}
}
