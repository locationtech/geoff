package org.locationtech.geoff.designer.internal;

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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class ResourcesUtil {
	public static final String RESOURCES_FOLDER = "resources";

	public static String readStream(InputStream in) throws IOException {
		StringBuilder sb = new StringBuilder();

		int c;

		while ((c = in.read()) != -1) {
			sb.append((char) c);
		}

		return sb.toString();
	}

	public static List<String> copyResources(String sourcePath, File targetFolder) throws IOException {
		return copyResources(ResourcesUtil.class, sourcePath, targetFolder);
	}

	public static List<String> copyResources(String bundleSymbolicName, String sourcePath, File targetFolder)
			throws IOException {
		Bundle bundle = Platform.getBundle(bundleSymbolicName);
		return copyResources(bundle, sourcePath, targetFolder);
	}

	public static List<String> copyResources(Class<?> context, String sourcePath, File targetFolder)
			throws IOException {
		Bundle bundle = FrameworkUtil.getBundle(context);
		Assert.isNotNull(bundle, "This method requires to be executed in an OSGi environment.");
		return copyResources(bundle, sourcePath, targetFolder);
	}

	public static List<String> copyResources(Bundle bundle, String sourcePath, File targetFolder) throws IOException {

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

	public static String toProjectRelativePath(IFile file) {
		List<String> paths = new ArrayList<String>();
		IResource resource = file;

		do {
			paths.add(resource.getName());
			resource = resource.getParent();
		} while (resource != null && !(resource instanceof IProject));

		StringBuilder ret = new StringBuilder();

		for (int i = paths.size() - 1; i >= 0; i--) {
			ret.append(paths.get(i));

			if (i > 0) {
				ret.append("/");
			}
		}

		return ret.toString();
	}
}
