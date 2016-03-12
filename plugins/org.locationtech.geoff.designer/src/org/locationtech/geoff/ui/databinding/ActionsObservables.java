package org.locationtech.geoff.ui.databinding;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.action.Action;

public class ActionsObservables {
	public static Binding bindEnabled(DataBindingContext ctx, Action action,
			IObservableValue observableValue) {
		IObservableValue observeValue = PojoObservables.observeValue(action,
				"enabled");
		UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
		modelToTarget.setConverter(new IConverter() {

			@Override
			public Object getToType() {
				return Boolean.class;
			}

			@Override
			public Object getFromType() {
				return Object.class;
			}

			@Override
			public Object convert(Object fromObject) {
				return fromObject != null;
			}
		});

		return ctx
				.bindValue(observeValue, observableValue, null, modelToTarget);
	}
}
