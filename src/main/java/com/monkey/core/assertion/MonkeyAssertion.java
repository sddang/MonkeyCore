package com.monkey.core.assertion;

import com.monkey.api.Assertion;
import com.monkey.api.MonkeyExecutionContext;
import com.monkey.api.MonkeyLogger;
import com.monkey.api.enumeration.LogLevel;
import com.monkey.api.mobile.workstation.TouchAction;
import com.monkey.api.page.MonkeyMobileElement;
import com.monkey.api.page.MonkeyWebElement;
import com.monkey.core.page.LocalisationHelper;
import com.monkey.services.data.DataMapper;
import com.monkey.services.log.LogTrackerEvent;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class MonkeyAssertion {

    private static SoftAssert softAssert;

    public static SoftAssert getSoftAssert() {
        return softAssert;
    }

    public static void checkboxChecked(final MonkeyWebElement element, final boolean checked, final String logFunctional,
                                       final boolean exitOnError) {
        boolean condition = false;
        if (MonkeyExecutionContext.isIOS()) {
            if (checked ? element.getWebElement().getAttribute("value").equals("1") : element.getWebElement().getAttribute("value").equals("")) {
                condition = true;
            }
        } else {
            if (checked ? element.getWebElement().getAttribute("checked").equals("true") : element.getWebElement().getAttribute("checked").equals("false")) {
                condition = true;
            }
        }
//        softAssert.assertEquals(condition,true);
        final String logTracker = "Verify checkbox is checked " + checked;
        final String logAssert = "Checkbox should be checked " + !checked;

        MonkeyAssertion.monkeyAssert(condition, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void isTrue(final boolean condition, final String logFunctional, final boolean exitOnError) {

        final String logTracker = "Verify condition is true";
        final String logAssert = "Condition should be true";

        MonkeyAssertion.monkeyAssert(condition, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void equalsToElementValue(final MonkeyWebElement element, String expected, final String logFunctional,
                                            final boolean exitOnError) {
        expected = DataMapper.getSessionMapper().mapData(expected);
        final String value = LocalisationHelper.getElementValue(element);
        final boolean testIsOK = value.equals(expected);
//        softAssert.assertEquals(value,expected);
        final String logTracker = "Verify that the given value '" + expected + "' equals to the value of the elemtent '"
                + element + "'";
        final String logAssert = "Value Or Option '" + expected + "' should be equals to element value :" + value
                + " !!! /n Element : " + element;

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void elementValuecontains(final MonkeyWebElement element, String expected, final String logFunctional,
                                            final boolean exitOnError) {
        expected = DataMapper.getSessionMapper().mapData(expected);
        final String container = LocalisationHelper.getElementValue(element);

        final boolean testIsOK = container.contains(expected);
//        softAssert.assertEquals(testIsOK,true);
        final String logTracker = "Verify that the value of the element '" + container + "' contains the vulue expected '"
                + expected + "'";
        final String logAssert = "Value Or Option '" + container + "' of the elment '" + element
                + "' should contain the expected value :'" + expected;

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void elementAttributeExpectedLength(final MonkeyWebElement element, final String attribute, final String expected,
                                                      final String logFunctional, final boolean exitOnError) {
        final int lengthExpected = Integer.parseInt(DataMapper.getSessionMapper().mapData(expected));
        final String attr = LocalisationHelper.getElementAttribute(element, attribute);
        final int textLenght = attr.length();

        final boolean testIsOK = lengthExpected == textLenght;

        final String logTracker = "Verify that the text length:'" + textLenght + "' of the element: '" + element
                + "' as expected '" + expected + "'";
        final String logAssert = "text length '" + textLenght + "' of the elment '" + element
                + "' should be equal to the expected value :'" + expected;

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void notEqualsToElementValue(final MonkeyWebElement element, String expected, final String logFunctional,
                                               final boolean exitOnError) {
        expected = DataMapper.getSessionMapper().mapData(expected);
        final String value = LocalisationHelper.getElementValue(element);
        final boolean testIsOK = !value.equals(expected);
//        softAssert.assertNotEquals(value,expected);
        final String logTracker = "Verify that the given value '" + expected
                + "' is different to to the value of the elemtent '" + element + "'";
        final String logAssert = "Value Or Option '" + expected + "' shouldn't be equal to element value :" + value
                + " !!! /n Element : " + element;

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void equalsToElementAttribut(final MonkeyWebElement element, final String attribute, String expected,
                                               final String logFunctional, final boolean exitOnError) {
        expected = DataMapper.getSessionMapper().mapData(expected);
        final String attr = LocalisationHelper.getElementAttribute(element, attribute);

        final boolean testIsOK = attr != null && attr.equals(expected);

        final String logTracker = "Verify that the given attribut '" + attribute + ": " + expected
                + "' equals to the attribut of the elemtent '" + element + "'";
        final String logAssert = "Attribute '" + expected + "' should be equal to element attribute :" + attr
                + " !!! /n Element : " + element;

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);

    }

    public static void notEqualsToElementAttribut(final MonkeyWebElement element, final String attribut, String expected,
                                                  final String logFunctional, final boolean exitOnError) {
        expected = DataMapper.getSessionMapper().mapData(expected);
        final String attr = LocalisationHelper.getElementAttribute(element, attribut);
        final boolean testIsOK = attr != null && !attr.equals(expected);

        final String logTracker = "Verify that the given attribute '" + attribut + ": " + expected
                + "' is different from the attribute of the elemtent '" + element + "'";
        final String logAssert = "Attribute '" + expected + "' shouldn't be equal to element attribute :" + attr
                + " !!! /n Element : " + element;

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void isElementSelected(final MonkeyWebElement element, final String logFunctional, final boolean exitOnError) {
        final boolean testIsOK;
        if (MonkeyExecutionContext.isIOS()) {
            testIsOK = element.getWebElement().getAttribute("value") != null && Boolean.parseBoolean(element.getWebElement().getAttribute("value"));
        } else {
            testIsOK = LocalisationHelper.isElementSelected(element);
        }

        final String logTracker = "Verify that the element '" + element.getVariableName() + "' is selected";
        final String logAssert = "Element '" + element.getVariableName() + "' should be Selected !!!\n Element : " + element;

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void isElementNotSelected(final MonkeyWebElement element, final String logFunctional, final boolean exitOnError) {
        final boolean testIsOK;
        if (MonkeyExecutionContext.isIOS()) {
            testIsOK = !((element.getWebElement().getAttribute("value") != null && Boolean.parseBoolean(element.getWebElement().getAttribute("value"))));
        } else {
            testIsOK = !LocalisationHelper.isElementSelected(element);
        }

        final String logTracker = "Verify that the element '" + element + "' is not selected";
        final String logAssert = "Element '" + element + "' shouldn't be Selected !!!";

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void textPresent(String expected, final String logFunctional, final boolean exitOnError) {
        expected = DataMapper.getSessionMapper().mapData(expected);
        final boolean testIsOK = LocalisationHelper.isTextPresent(expected);

        final String logTracker = "Verify that the text '" + expected + "' is present in the page";
        final String logAssert = "Text '" + expected + "' should be present!!! ";

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void textNotPresent(String expected, final String logFunctional, final boolean exitOnError) {
        expected = DataMapper.getSessionMapper().mapData(expected);
        final boolean testIsOK = !LocalisationHelper.isTextPresent(expected);

        final String logTracker = "Verify that the text '" + expected + "' is not present in the page";
        final String logAssert = "Text '" + expected + "' shouldn't be present!!! ";

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void elementPresent(final MonkeyWebElement element, final String logFunctional, final boolean exitOnError) {
        final boolean testIsOK = LocalisationHelper.isElementPresent(element);
//        softAssert.assertEquals(testIsOK,true);
        final String logTracker = "Verify that the element '" + element + "' is present in the page";
        final String logAssert = "element  '" + element.getVariableName() + "' should be present!!!\n Element : " + element;

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void elementNotPresent(final MonkeyWebElement element, final String logFunctional, final boolean exitOnError) {
        final boolean testIsOK = !LocalisationHelper.isElementPresent(element);
//        softAssert.assertEquals(testIsOK,false);
        final String logTracker = "Verify that the element '" + element + "' is not present in the page";
        final String logAssert = "element  '" + element.getVariableName() + "' shouldn't be present!!! \n Element : "
                + element;

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void matchRegex(final String string, final String regex, final String logFunctional, final boolean exitOnError) {
        final boolean testIsOK = string.matches(regex);

        final String logTracker = "Verify that the string '" + string + "' matches regex '" + regex + "'";
        final String logAssert = "String  '" + string + "' should match regex '" + regex + "' !!!";

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void equals(final Object actual, final Object expected, final String logFunctional, final boolean exitOnError) {
        final boolean testIsOK = actual.equals(expected);
//        softAssert.assertEquals(actual, expected);
        final String logTracker = "Verify that object '" + actual + "' equals to object '" + expected
                + "'";
        final String logAssert = "Object  '" + actual + "' should be equal to object '" + expected
                + "' !!!";

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void notEquals(final Object actual, final Object expected, final String logFunctional, final boolean exitOnError) {
        final boolean testIsOK = !actual.equals(expected);

        final String logTracker = "Verify that object '" + actual + "'does not equal to object '"
                + expected + "'";
        final String logAssert = "Object  '" + actual + "' should not be equal to object '" + expected
                + "' !!!";

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void equals(final String actual, final String expected, final String logFunctional, final boolean exitOnError) {
        final boolean testIsOK = actual.equals(expected);
//        softAssert.assertEquals(actual, expected);
        final String logTracker = "Verify that string '" + actual + "' equals to string '" + expected + "'";
        final String logAssert = "String  '" + actual + "' should be equal to string '" + expected + "' !!!";

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void contains(final String container, final String subString, final String logFunctional, final boolean exitOnError) {
        final boolean testIsOK = container.contains(subString);
//        softAssert.assertEquals(testIsOK,true);
        final String logTracker = "Verify that string '" + container + "' contains '" + subString + "'";
        final String logAssert = "String  '" + container + "' should contain '" + subString + "' !!!";

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void contains(final List<String> list, final String string, final String logFunctional, final boolean exitOnError) {
        final boolean testIsOK = list.contains(string);
//        softAssert.assertEquals(testIsOK,true);
        final String logTracker = "Verify that the list of string '" + list + "' contains '" + string + "'";
        final String logAssert = "List of string  '" + list + "' should contain '" + string + "' !!!";

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void containsSubString(final List<String> list, final String string, final String logFunctional, final boolean exitOnError) {
        boolean testIsOK = false;
        for (final String stringInList : list) {
            if (StringUtils.containsIgnoreCase(stringInList, string)) {
                testIsOK = true;
            }
        }
//        softAssert.assertEquals(testIsOK, false);
        final String logTracker = "Verify that the list of string '" + list + "' contains the sub string '"
                + string + "'";
        final String logAssert = "List of string  '" + list + "' should contain the sub string '" + string
                + "' !!!";

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);
    }

    public static void textVisibleInPageByScrolling(final MonkeyMobileElement element, final String text, final int maxDownScroll,
                                                    final String logFunctional, final boolean exitOnError) {
        final boolean testIsOK = TouchAction.scrollDownToText(element, text, maxDownScroll, exitOnError);

        final String logTracker = "Verify that the text '" + text + "' is visible by scolling (max : " + maxDownScroll + ")";
        final String logAssert = "The text  '" + text + "' should be visible after  '" + maxDownScroll + "' scroll !!!";

        MonkeyAssertion.monkeyAssert(testIsOK, logTracker, logAssert, logFunctional, exitOnError);
    }

    private static void monkeyAssert(final boolean test, final String logTracker, final String logAssert, final String logReport,
                                     final boolean exitOnError) {
        LogTrackerEvent.traceAssert(Assertion.class.getName(), logTracker);
        if (test) {
            if (logReport != null) {
//                 if (!exitOnError) {
                     MonkeyLogger.log(Assertion.class.getName(), logReport, LogLevel.PASS);
//                 } else {
//                    MonkeyLogger.log(Assertion.class.getName(), logReport, LogLevel.INFO);
//                 }
            } else {
                MonkeyLogger.log(Assertion.class.getName(), logTracker, LogLevel.DEBUG);
            }
        } else {
            if (exitOnError) {
                if (logReport != null) {
                    Assert.fail(logReport);
                } else {
                    Assert.fail(logAssert);
                }
            } else {
                if (logReport != null)
                    MonkeyLogger.log(Assertion.class.getClass().getName(), logReport, LogLevel.FAIL);
            }
        }
    }
}
