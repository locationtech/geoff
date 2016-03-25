package org.locationtech.geoff.ui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@javax.inject.Qualifier
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Observable {
	Class<?> value();
}
