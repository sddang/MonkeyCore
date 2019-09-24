
/**
 * This package will be exposed to the external. It will 
 * be used to write the tests.
 * All the utilies, the DSL and the apis are in this document
 */
package com.monkey.api.page;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import com.monkey.api.annotation.InjectComponent;
import com.monkey.api.annotation.InjectPage;
import com.monkey.api.annotation.WebLocator;
import com.monkey.core.exception.ExceptionCode;
import com.monkey.core.exception.MonkeyException;
import com.monkey.core.page.MonkeyAbstractPage;

public class MonkeyWebPage extends MonkeyAbstractPage {
	/**
	 * Constructor the TestPage class. At this time we load all the elements and
	 * we build them
	 */
	public MonkeyWebPage() {
        this.loadElements();
	}

	/**
	 * Load all the TestElements and setup there properties
	 */
	protected void loadElements() {
		final Object testPage = this;
		final Field[] elements = testPage.getClass().getDeclaredFields();
		final Set<MonkeyWebElement> testElements = new HashSet<MonkeyWebElement>();
		final Set<MonkeyWebComponent> monkeyComponents = new HashSet<MonkeyWebComponent>();
		final Set<MonkeyWebPage> testembeddedPages = new HashSet<MonkeyWebPage>();
		for (final Field element : elements) {
			if (element.getType().equals(MonkeyWebElement.class)) {
				final WebLocator locator = element.getAnnotation(WebLocator.class);
				final MonkeyWebElement testElement = this.buildTestElement(locator);
				testElement.setVariableName(element.getName());
				try {
					element.setAccessible(true);
					element.set(testPage, testElement);
				} catch (final IllegalArgumentException | IllegalAccessException e) {
					throw new MonkeyException(
							ExceptionCode.INITIALIZATION_TESTELEMENTS_ERROR,
                            getClass().getName(), e.getMessage());
				}
				testElements.add(testElement);

			} else {
				if (element.isAnnotationPresent(InjectComponent.class)) {
					final Class<?> type = element.getType();
					MonkeyWebComponent monkeyComponent = null;
					if (MonkeyWebComponent.class.isAssignableFrom(type)) {
						try {
							monkeyComponent = (MonkeyWebComponent) type.newInstance();
							monkeyComponent.loadElements(element);
							if (monkeyComponent != null) {
								element.setAccessible(true);
								element.set(testPage, monkeyComponent);
							}
						} catch (final Exception e) {
							throw new MonkeyException(
									ExceptionCode.INITIALIZATION_EMBEDDED_PAGES_ERROR,
									element.getClass().getName(), e
											.getMessage());
						}
					}
					monkeyComponents.add(monkeyComponent);
				}
				if (element.isAnnotationPresent(InjectPage.class)) {
					final Class<?> type = element.getType();
					if (!MonkeyWebPage.class.isAssignableFrom(type)) {
						throw new MonkeyException(
								ExceptionCode.INITIALIZATION_EMBEDDED_PAGES_ERROR,
								element.getType().getName(), "Problem when instantiating the embedded Pages");
					}
					MonkeyWebPage embeddedPage = null;
					if (MonkeyWebPage.class.isAssignableFrom(type)) {
						try {
							embeddedPage = (MonkeyWebPage) type.newInstance();
							embeddedPage.loadElements();
							if (embeddedPage != null) {
								element.setAccessible(true);
								element.set(testPage, embeddedPage);
							}
						} catch (final Exception e) {
							throw new MonkeyException(
									ExceptionCode.INITIALIZATION_EMBEDDED_PAGES_ERROR,
									element.getClass().getName(), e
											.getMessage());
						}
					}
					testembeddedPages.add(embeddedPage);
				}
			}

		}
        this.setPageElements(testElements);
        this.setPageComponents(monkeyComponents);
        this.setEmbeddedPages(testembeddedPages);

	}

	/**
	 * Build TestElements by setting them all there properties described in the @Locator
	 * 
	 * @param locator
	 *            annotation
	 * @return
	 */
	private MonkeyWebElement buildTestElement(final WebLocator locator) {
		final MonkeyWebElement testElement = new MonkeyWebElement();
		testElement.setInputValue(locator.value());
		testElement.setIdentifier(locator.identifier());
		testElement.setSelector(locator.selector());
		return testElement;
	}

}
