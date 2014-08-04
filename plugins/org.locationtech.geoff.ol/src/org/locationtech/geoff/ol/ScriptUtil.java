package org.locationtech.geoff.ol;

import java.io.IOException;
import java.io.InputStream;

import org.osgi.framework.FrameworkUtil;

public class ScriptUtil {
	public static String getOL3ImplScript(boolean minified) throws IOException {
		String sb = readResource("geoff-ol3.js");
		return sb.toString();
	}

	public static String getIndexHtml() throws IOException {
		String indexHtml = getIndexHtml(
				"http://code.jquery.com/jquery-1.10.2.js",
				"http://ol3js.org/en/master/build/ol.js",
				"http://ol3js.org/en/master/css/ol.css");
		return indexHtml;
	}

	public static String getIndexHtml(String jqueryURL, String ol3URL,
			String ol3CSSURL) throws IOException {
		String sb = readResource("index.html");
		sb = sb.replaceAll("\\{\\{OL3\\}\\}", ol3URL);
		sb = sb.replaceAll("\\{\\{OL3-CSS\\}\\}", ol3CSSURL);
		sb = sb.replaceAll("\\{\\{JQUERY\\}\\}", jqueryURL);
		return sb.toString();
	}

	public static String readResource(String path) throws IOException {
		InputStream in = ScriptUtil.class.getClassLoader().getResourceAsStream(
				"www/" + path);
		StringBuilder sb = new StringBuilder();

		int c;

		while ((c = in.read()) != -1) {
			sb.append((char) c);
		}

		return sb.toString();
	}
}
