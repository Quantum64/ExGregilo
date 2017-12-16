package co.q64.exgregilo.api.binders;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import co.q64.com.google.inject.BindingAnnotation;

public interface ModDataBinders {
	//formatter:off
	public static @Target({ ElementType.FIELD }) @Retention(RUNTIME) @BindingAnnotation  @interface GUITexPath {}
	//formatter:on
}
