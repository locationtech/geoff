package org.locationtech.geoff.ol;

import java.io.IOException;
import java.io.InputStream;

import org.osgi.framework.FrameworkUtil;

public class ResourcesUtil {
	public static String readResource(String path) throws IOException {
		InputStream in = ResourcesUtil.class.getClassLoader().getResourceAsStream(
				"www/" + path);
		StringBuilder sb = new StringBuilder();

		int c;

		while ((c = in.read()) != -1) {
			sb.append((char) c);
		}

		return sb.toString();
	}
}
