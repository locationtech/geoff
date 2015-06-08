package org.locationtech.geoff.ol;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class ResourcesUtil {
	public static final Resource INDEX_TEMPLATE_HTML = new Resource("index.template.html");
	public static final Resource OL_CSS = new Resource("ol.css");
	public static final Resource JQUERY_MIN_JS = new Resource("jquery-1.10.2.min.js");
	public static final Resource OL_JS = new Resource("ol.js");
	public static final Resource GEOFF_OL_JS = new Resource("geoff-ol3.js");

	public static final Resource JQUERY_UI_MIN_JS = new Resource("jquery-ui-1.10.4.min.js");
	public static final Resource JQUERY_UI_MIN_CSS = new Resource("jquery-ui.min.css");
	public static final String RESOURCES_FOLDER = "resources";

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
		return ResourcesUtil.class.getClassLoader().getResourceAsStream("www/" + path);
	}

	public static List<String> copyResources(String sourcePath, File targetFolder) throws IOException {
		return copyResources(ResourcesUtil.class, sourcePath, targetFolder);
	}

	public static List<String> copyResources(Class<?> context, String sourcePath, File targetFolder) throws IOException {
		Bundle bundle = FrameworkUtil.getBundle(context);

		if (bundle == null) {
			throw new UnsupportedOperationException("This method requires to be executed in an OSGi environment.");
		}

		Enumeration<URL> entries = bundle.findEntries(sourcePath, "*", true);

		if (entries == null) {
			return Collections.emptyList();
		}

		List<String> copiedFiles = new ArrayList<>();

		while (entries.hasMoreElements()) {
			URL url = (URL) entries.nextElement();
			String targetPathName = url.getPath();
			
			if (targetPathName.endsWith("/")) {
				continue;
			}

			if (targetPathName.startsWith("/")) {
				targetPathName = targetPathName.substring(1);
			}
			
			targetPathName = targetPathName.substring(targetPathName.indexOf('/'));
			copiedFiles.add(targetPathName.substring(1));
			
			File targetFile = new File(targetFolder, targetPathName);
			targetFile.mkdirs();
			Files.copy(url.openStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}

		return copiedFiles;
	}
}
