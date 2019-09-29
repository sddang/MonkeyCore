

package com.monkey.api;

import java.util.List;

import org.testng.Assert;

import com.monkey.api.page.MonkeyMobileElement;
import com.monkey.api.page.MonkeyWebElement;
import com.monkey.core.assertion.MonkeyAssertion;
import com.monkey.core.page.LocalisationHelper;
import com.monkey.services.data.DataMapper;


public class Assertion extends Assert {

    /**
     * Asserts that the value of the given element is equals to the expected. If
     * it isn't, an AssertionError, with the custom message, is thrown.
     *
     * @param element  is of type TestElement, it is the given html component
     * @param expected the value that we expect
     */
    public static void assertEqualsToElementValue(final MonkeyWebElement element, final String expected) {
        MonkeyAssertion.equalsToElementValue(element, expected, null, true);
    }

    /**
     * Asserts that the value of the given element is equals to the expected. If
     * it isn't, an AssertionError, with the custom message, is thrown.
     *
     * @param element  is of type TestElement, it is the given html component
     * @param expected the value that we expect
     */
    public static void assertEqualsToElementValue(final MonkeyWebElement element, final String expected, final String functionalLog) {
        MonkeyAssertion.equalsToElementValue(element, expected, functionalLog, true);
    }

    /**
     * Assert that the values are equals in upperCase
     *
     * @param element
     * @param expectedValue
     */
    public static void assertEqualsWithUpperCase(final MonkeyWebElement element, String expectedValue) {
        expectedValue = DataMapper.getSessionMapper().mapData(expectedValue);
        final String elementValue = LocalisationHelper.getElementAttribut(element, "value");

        MonkeyAssertion.equals(expectedValue.toUpperCase(), elementValue.toUpperCase(),
                expectedValue.toUpperCase() + " is not equal to " + elementValue.toUpperCase(), true);
    }

    /**
     * Assert that the values are equals in upperCase
     *
     * @param element
     * @param expectedValue
     */
    public static void assertEqualsWithUpperCase(final MonkeyWebElement element, String expectedValue,
                                                 final String FunctionalLog) {
        expectedValue = DataMapper.getSessionMapper().mapData(expectedValue);
        final String elementValue = LocalisationHelper.getElementAttribut(element, "value");
        MonkeyAssertion.equals(expectedValue.toUpperCase(), elementValue.toUpperCase(), FunctionalLog, true);
    }

    /**
     * Validate that the values are equals in upperCase
     *
     * @param element
     * @param expectedValue
     */
    public static void validateEqualsWithUpperCase(final MonkeyWebElement element, String expectedValue,
                                                   final String FunctionalLog) {
        expectedValue = DataMapper.getSessionMapper().mapData(expectedValue);
        final String elementValue = LocalisationHelper.getElementAttribut(element, "value");

        MonkeyAssertion.equals(expectedValue.toUpperCase(), elementValue.toUpperCase(),
                expectedValue.toUpperCase() + " is not equal to " + elementValue.toUpperCase(), false);
    }

    /**
     * Validate that the value of the given element is equals to the expected.
     * If it isn't, an AssertionError, with the custom message, is thrown.
     *
     * @param element  is of type TestElement, it is the given html component
     * @param expected the value that we expect
     */
    public static void validateEqualsToElementValue(final MonkeyWebElement element, final String expected, final String functionalLog) {
        MonkeyAssertion.equalsToElementValue(element, expected, functionalLog, false);
    }

    /**
     * Asserts that the value of the given element is not equals to the
     * expected. If it is, an AssertionError, with the custom message, is
     * thrown.
     *
     * @param element  is of type TestElement, it is the given html component
     * @param expected the value that we expect
     */
    public static void assertNotEqualsToElementValue(final MonkeyWebElement element, final String expected) {
        MonkeyAssertion.notEqualsToElementValue(element, expected, null, true);
    }

    /**
     * Asserts that the value of the given element is not equals to the
     * expected. If it is, an AssertionError, with the custom message, is
     * thrown.
     *
     * @param element  is of type TestElement, it is the given html component
     * @param expected the value that we expect
     */
    public static void assertNotEqualsToElementValue(final MonkeyWebElement element, final String expected, final String functionalLog) {
        MonkeyAssertion.notEqualsToElementValue(element, expected, functionalLog, true);
    }

    /**
     * Asserts that the attribute of the given element is equals to the expected
     * value. If it isn't, an AssertionError, with the custom message, is
     * thrown.
     *
     * @param element   is of type TestElement, it is the given html component
     * @param attribute html attribute of the element
     * @param expected  expected the value that we expect
     */
    public static void assertEqualsToElementAttribut(final MonkeyWebElement element, final String attribute, final String expected) {
        MonkeyAssertion.equalsToElementAttribut(element, attribute, expected, null, true);
    }

    public static void validateEqualsToElementAttribut(final MonkeyWebElement element, final String attribute, final String expected) {
        MonkeyAssertion.equalsToElementAttribut(element, attribute, expected, null, false);
    }

    public static void validateEqualsToElementAttribut(final MonkeyWebElement element, final String attribute, final String expected, final String functionalLog) {
        MonkeyAssertion.equalsToElementAttribut(element, attribute, expected, functionalLog, false);
    }

    /**
     * Asserts that the attribute of the given element is equals to the expected
     * value. If it isn't, an AssertionError, with the custom message, is
     * thrown.
     *
     * @param element   is of type TestElement, it is the given html component
     * @param attribute html attribute of the element
     * @param expected  expected the value that we expect
     */
    public static void assertEqualsToElementAttribut(final MonkeyWebElement element, final String attribute, final String expected,
                                                     final String functionalLog) {
        MonkeyAssertion.equalsToElementAttribut(element, attribute, expected, functionalLog, true);
    }

    /**
     * Asserts that the attribute of the given element isn't equals to the
     * expected value. If it is, an AssertionError, with the custom message, is
     * thrown.
     *
     * @param element   is of type TestElement, it is the given html component
     * @param attribute html attribute of the element
     * @param expected  expected the value that we expect
     */
    public static void assertNotEqualsToElementAttribut(final MonkeyWebElement element, final String attribute, final String expected) {
        MonkeyAssertion.notEqualsToElementAttribut(element, attribute, expected, null, true);
    }

    /**
     * Asserts that the attribute of the given element isn't equals to the
     * expected value. If it is, an AssertionError, with the custom message, is
     * thrown.
     *
     * @param element   is of type TestElement, it is the given html component
     * @param attribute html attribute of the element
     * @param expected  expected the value that we expect
     */
    public static void assertNotEqualsToElementAttribut(final MonkeyWebElement element, final String attribute, final String expected,
                                                        final String functionalLog) {
        MonkeyAssertion.notEqualsToElementAttribut(element, attribute, expected, functionalLog, true);
    }

    /**
     * Asserts that the given element is selected. generally this method is used
     * for the select elements or radio or check. An AssertionError, with the
     * custom message, is thrown when the element is not selected.
     *
     * @param element is of type TestElement, it is the given html component
     */
    public static void assertIsElementSelected(final MonkeyWebElement element) {
        MonkeyAssertion.isElementSelected(element, null, true);
    }

    /**
     * Asserts that the given element is selected. generally this method is used
     * for the select elements or radio or check. An AssertionError, with the
     * custom message, is thrown when the element is not selected.
     *
     * @param element is of type TestElement, it is the given html component
     */
    public static void assertIsElementSelected(final MonkeyWebElement element, final String functionalLog) {
        MonkeyAssertion.isElementSelected(element, functionalLog, true);
    }

    /**
     * Asserts that the given element is not selected. generally this method is
     * used for the select elements or radio or check. An AssertionError, with
     * the custom message, is thrown when the element is selected.
     *
     * @param element is of type TestElement, it is the given html component
     */
    public static void assertIsElementNotSelected(final MonkeyWebElement element) {
        MonkeyAssertion.isElementNotSelected(element, null, true);
    }

    /**
     * Asserts that the given element is not selected. generally this method is
     * used for the select elements or radio or check. An AssertionError, with
     * the custom message, is thrown when the element is selected.
     *
     * @param element is of type TestElement, it is the given html component
     */
    public static void assertIsElementNotSelected(final MonkeyWebElement element, final String functionalLog) {
        MonkeyAssertion.isElementNotSelected(element, functionalLog, true);
    }

    /**
     * Asserts that the given value is present in the page.
     *
     * @param expected is the given text
     */
    public static void assertTextPresent(final String expected) {
        MonkeyAssertion.textPresent(expected, null, true);
    }

    /**
     * Asserts that the given value is present in the page.
     *
     * @param expected is the given text
     */
    public static void assertTextPresent(final String expected, final String functionalLog) {
        MonkeyAssertion.textPresent(expected, functionalLog, true);
    }

    /**
     * Asserts that the given value is not present in the page.
     *
     * @param expected is the given text
     */
    public static void assertTextNotPresent(final String expected) {
        MonkeyAssertion.textNotPresent(expected, null, true);
    }

    /**
     * Asserts that the given value is not present in the page.
     *
     * @param expected is the given text
     */
    public static void assertTextNotPresent(final String expected, final String functionalLog) {
        MonkeyAssertion.textNotPresent(expected, functionalLog, true);
    }

    /**
     * Asserts that the given element is present in the page.
     *
     * @param testElement is the given element
     */
    public static void assertElementIsPresent(final MonkeyWebElement testElement) {
        MonkeyAssertion.elementPresent(testElement, null, true);
    }

    /**
     * Asserts that the given element is present in the page.
     *
     * @param testElement is the given element
     */
    public static void assertElementIsPresent(final MonkeyWebElement testElement, final String functionalLog) {
        MonkeyAssertion.elementPresent(testElement, functionalLog, true);
    }

    /**
     * Asserts that the given element is not present in the page.
     *
     * @param testElement is the given element
     */
    public static void assertElementIsNotPresent(final MonkeyWebElement testElement) {
        MonkeyAssertion.elementNotPresent(testElement, null, true);
    }

    /**
     * Asserts that the given element is not present in the page.
     *
     * @param testElement is the given element
     */
    public static void assertElementIsNotPresent(final MonkeyWebElement testElement, final String functionalLog) {
        MonkeyAssertion.elementNotPresent(testElement, functionalLog, true);
    }

    public static void assertElementEqualsToCurrentInputValue(final MonkeyWebElement testElement) {
        assertEqualsToElementValue(testElement, testElement.getInputValue());
    }

    /**
     * Asserts that the value of the given element contains the value expected.
     * If it isn't, an AssertionError, with the custom message, is thrown.
     *
     * @param element  is of type TestElement, it is the given html component
     * @param expected the value that we expect
     */
    public static void assertElementValueContains(final MonkeyWebElement testElement, final String expected) {
        MonkeyAssertion.elementValuecontains(testElement, expected, null, true);
    }

    /**
     * Asserts that the text length of the given element as expected. If it
     * isn't, an AssertionError, with the custom message, is thrown.
     *
     * @param element  is of type TestElement, it is the given html component
     * @param expected the value that we expect
     */
    public static void assertElementAttributeLengthExpected(final MonkeyWebElement testElement, final String attribute,
                                                            final String expected) {
        MonkeyAssertion.elementAttributeExpectedLength(testElement, attribute, expected, null, true);
    }

    public static void validateElementAttributeLengthExpected(final MonkeyWebElement testElement, final String attribute,
                                                              final String expected) {
        MonkeyAssertion.elementAttributeExpectedLength(testElement, attribute, expected, null, false);
    }

    public static void assertElementAttributeLengthExpected(final MonkeyWebElement testElement, final String attribute,
                                                            final String expected, final String functionalLog) {
        MonkeyAssertion.elementAttributeExpectedLength(testElement, attribute, expected, functionalLog, true);
    }

    /**
     * Asserts that the value of the given element contains the value expected.
     * If it isn't, an AssertionError, with the custom message, is thrown.
     *
     * @param element  is of type TestElement, it is the given html component
     * @param expected the value that we expect
     */
    public static void assertElementValueContains(final MonkeyWebElement testElement, final String expected, final String functionalLog) {
        MonkeyAssertion.elementValuecontains(testElement, expected, functionalLog, true);
    }

    /**
     * Validate that the condition is ok, exitOnerror will exit the tests if the
     *
     * @param condition
     * @param message
     * @param exitOnError
     */
    public static void assertTrue(final Boolean condition, final String message) {
        MonkeyAssertion.isTrue(condition, message, true);
    }

    /**
     * Validate that the condition is ok, exitOnerror will exit the tests if the
     *
     * @param condition
     * @param message
     * @param exitOnError
     */
    public static void validateTrue(final Boolean condition, final String message) {
        MonkeyAssertion.isTrue(condition, message, false);
    }

    /**
     * validate that the given string fit with the given regex
     *
     * @param stringToCheck
     * @param regex
     * @param message
     * @param exitOnError
     */
    public static void validateMatchRegex(final String stringToCheck, final String regex, final String message) {
        MonkeyAssertion.matchRegex(stringToCheck, regex, message, false);
    }

    /**
     * validate that the given string fit with the given regex
     *
     * @param stringToCheck
     * @param regex
     * @param message
     * @param exitOnError
     */
    public static void assertMatchRegex(final String stringToCheck, final String regex, final String message) {
        MonkeyAssertion.matchRegex(stringToCheck, regex, message, true);
    }

    /**
     * validate that the two given string are equals
     *
     * @param expected
     * @param given
     * @param message
     * @param exitOnError
     */
    public static void validateEquals(final String expected, final String given, final String message) {
        MonkeyAssertion.equals(expected, given, message, false);
    }

    public static void validateEquals(final Object expected, final Object given, final String message) {
        MonkeyAssertion.equals(expected, given, message, false);
    }

    public static void validateNotEquals(final Object expected, final Object given, final String message) {
        MonkeyAssertion.notEquals(expected, given, message, false);
    }

    /**
     * validate that the two given string are equals
     *
     * @param expected
     * @param given
     * @param message
     * @param exitOnError
     */
    public static void assertEquals(final String expected, final String given, final String message) {
        MonkeyAssertion.equals(expected, given, message, true);
    }

    /**
     * validate that the expected string contains the given value
     *
     * @param expected
     * @param value
     * @param message
     * @param exitOnError
     */
    public static void validateContains(final String expected, final String value, final String message) {
        MonkeyAssertion.contains(expected, value, message, false);
    }

    /**
     * validate that the expected string contains the given value
     *
     * @param expected
     * @param value
     * @param message
     * @param exitOnError
     */
    public static void assertContains(final String expected, final String value, final String message) {
        MonkeyAssertion.contains(expected, value, message, true);
    }

    /**
     * verify that the list contains the given value
     *
     * @param stringList
     * @param string
     * @param message
     * @param exitOnError
     */
    public static void validateContainsSubString(final List<String> stringList, final String string, final String message) {
        MonkeyAssertion.containsSubString(stringList, string, message, false);
    }

    /**
     * verify that the list contains the given value
     *
     * @param stringList
     * @param string
     * @param message
     * @param exitOnError
     */
    public static void assertContainsSubString(final List<String> stringList, final String string, final String message) {
        MonkeyAssertion.containsSubString(stringList, string, message, true);
    }

    /**
     * validate that the element is Present
     *
     * @param element
     * @param message
     * @param exitOnError
     */
    public static void validateElementIsPresent(final MonkeyWebElement element, final String message) {
        MonkeyAssertion.elementPresent(element, message, false);
    }

    public static void validateElementIsPresent(final MonkeyWebElement element) {
        MonkeyAssertion.elementPresent(element, null, false);
    }

    /**
     * validate that the element is not Present
     *
     * @param element
     * @param message
     * @param exitOnError
     */
    public static void validateElementIsNotPresent(final MonkeyWebElement element, final String message) {
        MonkeyAssertion.elementNotPresent(element, message, false);
    }

    /**
     * Validate that the given value is present in the page.
     *
     * @param expected is the given text
     */
    public static void validateTextPresent(final String expected, final String message) {
        MonkeyAssertion.textPresent(expected, message, false);
    }

    /**
     * Validate that the given value is not present in the page.
     *
     * @param expected is the given text
     */
    public static void validateTextNotPresent(final String expected, final String message) {
        MonkeyAssertion.textNotPresent(expected, message, false);
    }

    /**
     * Used only for mobile
     *
     * @param element
     * @param text
     * @param maxDownScroll
     * @param message
     * @param exitOnError
     */
    public static void validateTextVisibleInPageByScrolling(final MonkeyMobileElement element, final String text, final int maxDownScroll,
                                                            final String message) {
        MonkeyAssertion.textVisibleInPageByScrolling(element, text, maxDownScroll, message, false);
    }

    /**
     * Used only for mobile
     *
     * @param element
     * @param text
     * @param maxDownScroll
     * @param message
     * @param exitOnError
     */
    public static void assertTextVisibleInPageByScrolling(final MonkeyMobileElement element, final String text, final int maxDownScroll,
                                                          final String message) {
        MonkeyAssertion.textVisibleInPageByScrolling(element, text, maxDownScroll, message, true);
    }

    public static void validateCheckboxChecked(final MonkeyWebElement element, final String message) {
        MonkeyAssertion.checkboxChecked(element, true, message, false);
    }

    public static void assertCheckboxChecked(final MonkeyWebElement element, final String message) {
        MonkeyAssertion.checkboxChecked(element, true, message, true);
    }

    public static void validateCheckboxUnchecked(final MonkeyWebElement element, final String message) {
        MonkeyAssertion.checkboxChecked(element, false, message, false);
    }

    public static void assertCheckboxUnchecked(final MonkeyWebElement element, final String message) {
        MonkeyAssertion.checkboxChecked(element, false, message, true);
    }

    public static void validateElementValueContains(final MonkeyWebElement testElement, final String expected,
                                                    final String functionalLog) {
        MonkeyAssertion.elementValuecontains(testElement, expected, functionalLog, false);

    }

    public static void validateIsElementNotSelected(final MonkeyWebElement element, final String functionalLog) {
        MonkeyAssertion.isElementNotSelected(element, functionalLog, false);

    }

    public static void validateIsElementSelected(final MonkeyWebElement element, final String functionalLog) {
        MonkeyAssertion.isElementSelected(element, functionalLog, false);

    }

}
