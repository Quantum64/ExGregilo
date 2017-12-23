package co.q64.exgregilo.api.binders;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import co.q64.com.google.inject.BindingAnnotation;

public interface ModDataBinders {
	//formatter:off
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface ModId {}
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface GtGUITexPath {}
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface BlockTexPath {}
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER}) @Retention(RUNTIME) @BindingAnnotation  @interface DomainPath {}
	
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface BasicSieveBlockName {}
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface BasicSieveMesh {}
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface BasicSieveTex {}
	
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface AdvancedSieveBlockName {}
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface AdvancedSieveMesh {}
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface AdvancedSieveTex {}
	
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface HeavySieveBlockName {}
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface HeavySieveMesh {}
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface HeavySieveTex {}
	
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface GemSandBlockName {}
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface GemSandTex {}
	
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface DustBlockName {}
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface DustTex {}
	
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface CompressedBlockName {}
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface CompressedTex {}
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface CompressedGravelType {}
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface CompressedSandType {}
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface CompressedDustType {}
	
	public static @Target({ ElementType.FIELD, ElementType.PARAMETER }) @Retention(RUNTIME) @BindingAnnotation  @interface GregCrucibleBlockName {}
	//formatter:on
}
