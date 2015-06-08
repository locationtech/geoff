package org.locationtech.geoff.ol;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.statushandlers.StatusManager;

public class Resource {
	private String path;
	private List<Resource> children = new ArrayList<Resource>();

	public Resource(String path) {
		this.path = path;
	}

	public Resource(String path, String... files) {
		this(path);

		if (files != null) {
			for (String file : files) {
				Resource child = new Resource(path + "/" + file);
				children.add(child);
			}
		}
	}

	public void copyTo(File targetFolder) {
		File targetFile = new File(targetFolder, path);

		try {
			InputStream stream = ResourcesUtil.readResourceAsStream(path);
			Files.copy(stream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			IStatus status = ValidationStatus.error(e.getMessage(), e);
			StatusManager.getManager().handle(status, StatusManager.SHOW);
		}
	}

	public String getPath() {
		return path;
	}
}