package org.locationtech.geoff.ui;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.e4.core.contexts.IEclipseContext;

public class ContextObservables {

	@SuppressWarnings("unchecked")
	public static <T> IObservableValue observeValue(IEclipseContext ctx, Class<T> type) {
		WritableValue value = new WritableValue(ctx.get(type), type);
		value.addValueChangeListener((evt) -> {
			ctx.set(type, (T)evt.diff.getNewValue());
		});
		return value;
	}

	@SuppressWarnings("unchecked")
	public static <T> void bindValueTo(IEclipseContext ctx, Class<T> type, IObservableValue observableValue) {
		// set initial value
		ctx.set(type, (T) observableValue.getValue());

		observableValue.addValueChangeListener((evt) -> {
			if (observableValue.isDisposed()) {
				return;
			}

			T value = (T) evt.diff.getNewValue();
			ctx.set(type, value);
		});
	}
}
