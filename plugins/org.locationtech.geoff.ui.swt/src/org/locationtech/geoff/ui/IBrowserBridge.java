package org.locationtech.geoff.ui;

public interface IBrowserBridge {
	void loadHTML(String html);

	void executeJavaSript(String jsCode);
}
