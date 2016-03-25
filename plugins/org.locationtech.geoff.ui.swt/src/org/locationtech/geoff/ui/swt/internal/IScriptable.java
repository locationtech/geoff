package org.locationtech.geoff.ui.swt.internal;

public interface IScriptable {
	<T> T execute(String jsCode);
}
