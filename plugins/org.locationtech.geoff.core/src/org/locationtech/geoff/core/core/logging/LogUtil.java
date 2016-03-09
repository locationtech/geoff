package org.locationtech.geoff.core.core.logging;

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
			Logger.getLogger(context.getName()).log(Level.SEVERE, e.getMessage(), e);
			return false;
		}
		
		return true;
	}
}
