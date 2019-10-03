package com.monkey.api.page;

import com.monkey.api.annotation.InjectComponent;
import com.monkey.api.annotation.WebLocator;
import com.monkey.core.exception.ExceptionCode;
import com.monkey.core.exception.MonkeyException;
import com.monkey.core.page.MonkeyAbstractComponent;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class MonkeyWebComponent extends MonkeyAbstractComponent {
    /**
     * Implicit Constructor
     */
    public MonkeyWebComponent() {

    }

    /**
     * Load all the TestElements and setup there properties
     */
    protected void loadElements(final Field monkeyComponentField) {
        final MonkeyWebComponent monkeyComponent = this;
        final Field[] elements = monkeyComponentField.getType().getDeclaredFields();
        final Set<MonkeyWebElement> testElements = new HashSet<MonkeyWebElement>();
        final Set<MonkeyWebComponent> testEmbededElements = new HashSet<MonkeyWebComponent>();
        for (final Field element : elements) {
            final Class<?> type = element.getType();
            if (type.equals(MonkeyWebElement.class)) {
                try {
                    final WebLocator locator = element.getAnnotation(WebLocator.class);
                    final MonkeyWebElement testElement = this.buildTestelement(locator);
                    testElement.setVariableName(element.getName());
                    element.setAccessible(true);
                    element.set(monkeyComponent, testElement);
                    testElements.add(testElement);
                } catch (final IllegalArgumentException | IllegalAccessException e) {
                    throw new MonkeyException(
                            ExceptionCode.INITIALIZATION_TESTELEMENTS_ERROR,
                            getClass().getName(), e.getMessage());
                }

            } else if (element.isAnnotationPresent(InjectComponent.class)) {
                final MonkeyWebComponent testEmbededComponent;
                if (!MonkeyWebComponent.class.isAssignableFrom(type)) {
                    throw new MonkeyException(
                            ExceptionCode.INITIALIZATION_EMBEDDED_COMPONENT_ERROR,
                            element.getType().getName(), "Problem when instantiating the embedded Component");
                }
                try {
                    testEmbededComponent = (MonkeyWebComponent) element.getType().newInstance();
                    testEmbededComponent.loadElements(element);
                    element.setAccessible(true);
                    element.set(monkeyComponent, testEmbededComponent);
                } catch (final InstantiationException | IllegalAccessException e) {
                    throw new MonkeyException(
                            ExceptionCode.INITIALIZATION_TESTELEMENTS_ERROR,
                            getClass().getName(), e.getMessage());
                }
                testEmbededElements.add(testEmbededComponent);
            }

        }
        this.setComponentElements(testElements);
        this.setEmbeddedComponentElements(testEmbededElements);

    }

    /**
     * Build TestElements by setting them all there properties described in the @Locator
     *
     * @param locator annotation
     * @return
     */
    private MonkeyWebElement buildTestelement(final WebLocator locator) {
        final MonkeyWebElement testElement = new MonkeyWebElement();
        testElement.setInputValue(locator.value());
        testElement.setIdentifier(locator.identifier());
        testElement.setSelector(locator.selector());
        return testElement;
    }
}
