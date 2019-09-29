

package com.monkey.api.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.monkey.api.enumeration.Selector;

/**
 * Annotation used for the TestElement. It decorate the element to be located by the monkey Framework.
 */
@Retention(RUNTIME)
@Target(java.lang.annotation.ElementType.FIELD)
public @interface WebLocator {
    /**
     * The identifier key of the element
     *
     * @return
     */
    String identifier() default "";

    /**
     * The selector that define the way to find profil field.
     * The default value is xpath
     *
     * @return
     */
    Selector selector() default Selector.xpath;

    /**
     * The input value of the field
     *
     * @return
     */
    String value() default "";

}
