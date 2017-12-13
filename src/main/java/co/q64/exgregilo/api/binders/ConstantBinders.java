package co.q64.exgregilo.api.binders;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import co.q64.com.google.inject.BindingAnnotation;

public interface ConstantBinders {
	//formatter:off
	public static @Target({ ElementType.FIELD }) @Retention(RUNTIME) @BindingAnnotation  @interface Name {}
	
	public static @Target({ ElementType.FIELD }) @Retention(RUNTIME) @BindingAnnotation  @interface Author {}
	
	public static @Target({ ElementType.FIELD }) @Retention(RUNTIME) @BindingAnnotation  @interface ConfigFile {}
	
	//formatter:on
}
