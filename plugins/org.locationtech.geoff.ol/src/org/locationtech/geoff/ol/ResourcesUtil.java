package org.locationtech.geoff.ol;

import java.io.IOException;
import java.io.InputStream;

public class ResourcesUtil {
	public static final String INDEX_TEMPLATE_HTML = "index.template.html";
	public static final String OL_CSS = "ol.css";
	public static final String JQUERY_MIN_JS = "jquery-1.10.2.min.js";
	public static final String OL_JS = "ol.js";
	public static final String GEOFF_OL_JS = "geoff-ol3.js";

	public static String readResource(String path) throws IOException {
		InputStream in = readResourceAsStream(path);
		return readStream(in);
	}

	public static String readStream(InputStream in) throws IOException {
		StringBuilder sb = new StringBuilder();

		int c;

		while ((c = in.read()) != -1) {
			sb.append((char) c);
		}

		return sb.toString();
	}

	public static InputStream readResourceAsStream(String path) {
		return ResourcesUtil.class.getClassLoader().getResourceAsStream(
				"www/" + path);
	}
}
