package com.monkey.api.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This an annotation. It is used to for tests methods already decorated by the @Test annotation.
 * It is used to specify the path of the json file that define the data of the test.
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface InjectData {
    /**
     * return the json path od the data file
     *
     * @return
     */
    String json();
}
