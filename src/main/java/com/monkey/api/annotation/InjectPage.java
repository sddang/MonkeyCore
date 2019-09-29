
package com.monkey.api.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Inject profil an embedded page in profil TestPage.
 */
@Retention(RUNTIME)
@Target(java.lang.annotation.ElementType.FIELD)
public @interface InjectPage {

}
