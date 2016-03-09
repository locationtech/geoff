package org.locationtech.geoff.designer;

import java.util.EventObject;
import java.util.function.Consumer;

public interface IChangeSupport {
	boolean canUndo();

	boolean canRedo();

	void undo();

	void redo();

	void save();

	void onChange(Consumer<EventObject> event);

	void batchChanges(Runnable... runnable);
}
