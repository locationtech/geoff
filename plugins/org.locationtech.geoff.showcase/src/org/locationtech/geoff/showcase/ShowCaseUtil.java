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
package org.locationtech.geoff.showcase;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

public class ShowCaseUtil {
	public static File exportResourcesToTempDir() throws IOException {
		Path resourcesPath = Files.createTempDirectory("lt-geoff");
		File resourcesPathFile = resourcesPath.toFile();
		Bundle bundle = FrameworkUtil.getBundle(ShowCaseUtil.class);
		Enumeration<URL> findEntries = bundle.findEntries("resources", "*",
				false);

		if (findEntries != null) {
			while (findEntries.hasMoreElements()) {
				URL url = (URL) findEntries.nextElement();
				// get file name without any parent directory
				String fileName = new File(url.getFile()).getName();
				File file = new File(resourcesPathFile, fileName);
				file.deleteOnExit();
				InputStream openStream = url.openStream();
				Files.copy(openStream, file.toPath());
				openStream.close();
			}
		}

		return resourcesPathFile;
	}
}
