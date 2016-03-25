package org.locationtech.geoff.ui.internal;

import java.lang.reflect.Type;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.contexts.RunAndTrack;
import org.eclipse.e4.core.di.IInjector;
import org.eclipse.e4.core.di.suppliers.ExtendedObjectSupplier;
import org.eclipse.e4.core.di.suppliers.IObjectDescriptor;
import org.eclipse.e4.core.di.suppliers.IRequestor;
import org.eclipse.e4.core.internal.di.Requestor;
import org.locationtech.geoff.ui.Observable;
import org.osgi.service.component.annotations.Component;

@SuppressWarnings("restriction")
@Component(service = ExtendedObjectSupplier.class, property = "dependency.injection.annotation:String=org.locationtech.geoff.ui.Observable")
public class ObservableObjectSupplier extends ExtendedObjectSupplier {

	@Override
	public Object get(IObjectDescriptor descriptor, IRequestor requestor, boolean track, boolean group) {
		Type target = descriptor.getDesiredType();

		if (!IObservableValue.class.equals(target)) {
			return null;
		}

		Observable qualifier = descriptor.getQualifier(Observable.class);
		Class<?> valueType = qualifier.value();

		if (valueType == null) {
			return null;
		}

		IObservableValue observable = new WritableValue() {
			@PostConstruct
			public void setContext(IEclipseContext context) {

				context.runAndTrack(new RunAndTrack() {

					@Override
					public boolean changed(IEclipseContext context) {
						Object object = context.get(valueType);
						setValue(object);
						return true;
					}
				});
			}
		};
		Requestor r = (Requestor) requestor;
		IInjector injector = r.getInjector();
		injector.inject(observable, r.getPrimarySupplier());
		return observable;
	}
}
