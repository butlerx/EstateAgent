package com.dcu;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.glassfish.jersey.internal.inject.AnnotationLiteral;
import org.glassfish.jersey.message.filtering.EntityFiltering;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EntityFiltering
public @interface PropertyView {

  class Factory extends AnnotationLiteral<PropertyView> implements PropertyView {

    private static final long serialVersionUID = 1000;

    private Factory() {}

    public static PropertyView get() {
      return new Factory();
    }
  }
}
