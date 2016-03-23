package org.locationtech.geoff.ui.swt;

public interface IScriptable {
	<T> T execute(String jsCode);
}
