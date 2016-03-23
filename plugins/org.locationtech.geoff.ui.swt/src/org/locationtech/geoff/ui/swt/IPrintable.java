package org.locationtech.geoff.ui.swt;

import java.io.InputStream;
import java.util.function.Consumer;

public interface IPrintable {
	enum Format {
		IMAGE_PNG
	}

	void print(Format format, Consumer<InputStream> consumer);
}
