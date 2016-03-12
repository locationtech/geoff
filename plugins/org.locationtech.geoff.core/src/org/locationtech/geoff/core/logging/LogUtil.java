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
}
