package com.monkey.api.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Inject profil component in profil page
 */
@Retention(RUNTIME)
@Target(java.lang.annotation.ElementType.FIELD)
public @interface InjectComponent {

}
