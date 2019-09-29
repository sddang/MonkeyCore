
package com.monkey.core.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.monkey.api.enumeration.HTMLType;
import com.monkey.api.enumeration.Selector;
import com.monkey.api.page.MonkeyWebElement;
import com.monkey.core.exception.ExceptionCode;
import com.monkey.core.exception.MonkeyException;
import com.monkey.core.session.ExecutionManager;
import com.monkey.services.data.DataMapper;
import com.monkey.services.log.LogTrackerEvent;


public class LocalisationHelper {
    /**
     * Search html elements with all of the supported ways and return the
     * selenium element of type WebElement
     *
     * @param key
     * @param selector
     * @return
     */

    public static WebElement findWebElement(String key, Selector selector, String variablename,
                                            boolean noError, boolean nomonkeyException, final long timeOut) throws IllegalArgumentException, MonkeyException {
        if (key == null) {
            throw new IllegalArgumentException("Key shouldn't be null!!! No field can't be found");
        }

        final StringBuffer message = new StringBuffer(300);
        message.append(" << varName : ").append(variablename).append(" || selector : ").append(selector)
                .append(" ||  key : ").append(key).append(" >> \n Selenium error  :\n ");
        WebElement element = null;
        try {
            switch (selector) {
                case name:
                    element = (new WebDriverWait(ExecutionManager.getMonkeyDriver(), timeOut))
                            .until(ExpectedConditions.presenceOfElementLocated(By.name(key)));
                    break;
                case id:
                    element = (new WebDriverWait(ExecutionManager.getMonkeyDriver(), timeOut))
                            .until(ExpectedConditions.presenceOfElementLocated(By.id(key)));
                    break;
                case className:
                    element = (new WebDriverWait(ExecutionManager.getMonkeyDriver(), timeOut))
                            .until(ExpectedConditions.presenceOfElementLocated(By.className(key)));
                    break;
                case partialLinkText:
                    element = (new WebDriverWait(ExecutionManager.getMonkeyDriver(), timeOut))
                            .until(ExpectedConditions.presenceOfElementLocated(By.linkText(key)));
                    break;
                case xpath:
                    element = (new WebDriverWait(ExecutionManager.getMonkeyDriver(), timeOut))
                            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(key)));
                    break;
                case cssSelector:
                    element = (new WebDriverWait(ExecutionManager.getMonkeyDriver(), timeOut))
                            .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(key)));
                    break;
                case tagName:
                    element = (new WebDriverWait(ExecutionManager.getMonkeyDriver(), timeOut))
                            .until(ExpectedConditions.presenceOfElementLocated(By.tagName(key)));
                    break;
            }
            return element;

        } catch (final Exception e) {
            if (noError) {
                return null;
            } else {
                if (nomonkeyException) {
                    throw e;
                } else {
                    throw new MonkeyException(ExceptionCode.FIELD_NOT_FOUND_EXCEPTION,
                            message.append(e.getMessage()).toString());
                }
            }
        }
    }

    public static WebElement findWebElement(String key, Selector selector, String variablename,
                                            boolean noError, boolean nomonkeyException) throws IllegalArgumentException, MonkeyException {
        final long timeOut = ExecutionManager.getConfiguration().getTimeOut();
        return LocalisationHelper.findWebElement(key, selector, variablename, noError, nomonkeyException, timeOut);
    }

    public static WebElement findWebElement(String key, Selector selector, String variablename,
                                            boolean noError) throws IllegalArgumentException, MonkeyException {
        return LocalisationHelper.findWebElement(key, selector, variablename, noError, false);
    }

    public static WebElement findWebElement(String key, Selector selector, String variablename)
            throws IllegalArgumentException, MonkeyException {
        return LocalisationHelper.findWebElement(key, selector, variablename, false);
    }

    public static List<WebElement> findElements(String key, Selector selector, String variablename) {
        if (key == null) {
            throw new IllegalArgumentException("Key shouldn't be null!!! No field can't be found");
        }

        final StringBuffer message = new StringBuffer(300);
        message.append(" << selector : ").append(selector).append(" ||  key : ").append(key)
                .append(" >> \n Selenium error  :\n ");
        List<WebElement> elements = null;
        final long timeOut = ExecutionManager.getConfiguration().getTimeOut();
        try {
            switch (selector) {
                case name:
                    elements = (new WebDriverWait(ExecutionManager.getMonkeyDriver(), timeOut))
                            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name(key)));
                    break;
                case id:
                    elements = (new WebDriverWait(ExecutionManager.getMonkeyDriver(), timeOut))
                            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(key)));
                    break;
                case className:
                    elements = (new WebDriverWait(ExecutionManager.getMonkeyDriver(), timeOut))
                            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(key)));
                    break;
                case partialLinkText:
                    elements = (new WebDriverWait(ExecutionManager.getMonkeyDriver(), timeOut))
                            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText(key)));
                    break;
                case xpath:
                    elements = (new WebDriverWait(ExecutionManager.getMonkeyDriver(), timeOut))
                            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(key)));
                    break;
                case cssSelector:
                    elements = (new WebDriverWait(ExecutionManager.getMonkeyDriver(), timeOut))
                            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(key)));
                    break;
                case tagName:
                    elements = (new WebDriverWait(ExecutionManager.getMonkeyDriver(), timeOut))
                            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName(key)));
                    break;
            }
            return elements;

        } catch (final Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Return true when the given element is enabled
     *
     * @param testElement
     * @return
     */
    public static boolean isElementenabled(final MonkeyWebElement testElement) {
        final WebElement element = testElement.getWebElement();
        return LocalisationHelper.isElementenabled(element);
    }

    /**
     * This methods returns if the element is enabled but it is used only for
     * monkey
     *
     * @param element
     * @return
     */
    public static boolean isElementenabled(final WebElement element) {
        return element.isDisplayed() && element.isEnabled();
    }

    /**
     * Return the label of the element Not implemented yet
     *
     * @param element
     * @return
     */
    public static String getLabel(final WebElement element) {
        return "Not supported yet";
    }

    /**
     * Return the HTML tag of the element
     *
     * @param element
     * @return
     */
    public static HTMLType getHTMLTag(final WebElement element) {
        return HTMLType.valueOf(element.getTagName());
    }

    /**
     * Return the value of the given test element
     *
     * @param testElement
     * @return
     */
    public static String getElementValue(final MonkeyWebElement testElement) {
        final WebElement element = testElement.getWebElement();
        return LocalisationHelper.getElementValue(element);
    }

    /**
     * Return the value of the element
     *
     * @param element
     * @return
     */
    public static String getElementValue(final WebElement element) {
        try {
            final Select select = new Select(element);
            final WebElement e = select.getFirstSelectedOption();
            return e.getText();
        } catch (final Exception e) {
            final String value = element.getText() != null && !element.getText().equals("") ? element.getText()
                    : element.getAttribute("value");
            return value;
        }
    }

    /**
     * Return the attribute value of the given attribute of an element
     *
     * @param testElement
     * @param attribut
     * @return
     */
    public static String getElementAttribut(final MonkeyWebElement testElement, final String attribut) {
        final WebElement element = testElement.getWebElement();
        return LocalisationHelper.getElementAttribut(element, attribut);
    }

    /**
     * Return the attribute value of the element's attribut
     *
     * @param element
     * @param attribut
     * @return
     */
    public static String getElementAttribut(final WebElement element, final String attribut) {
        return element.getAttribute(attribut);
    }

    /**
     * Return true when the element exists in the page and it's enabled
     *
     * @param inputText
     * @return
     */
    public static boolean isTextPresent(String inputText) {
        inputText = DataMapper.getSessionMapper().mapData(inputText);
        inputText = DataMapper.getSessionMapper().mapData(inputText);
        boolean isTextPresent = false;

        isTextPresent = (LocalisationHelper.findWebElement("//*[contains(.,'" + inputText + "')]", Selector.xpath, null, true) != null);
        if (!isTextPresent) {
            LogTrackerEvent.trace(LocalisationHelper.class.getName(), "Text not found yet. Try to find it in body");
            final String allText = LocalisationHelper.findWebElement("body", Selector.tagName, null).getText();
            isTextPresent = (allText != null && allText.contains(inputText));
        }
        return isTextPresent;
    }

    /**
     * Return true if the element is present in the Page and it's enabled
     *
     * @param testElement
     * @return
     */
    public static boolean isElementPresent(final MonkeyWebElement testElement) {
        final WebElement element = LocalisationHelper.findWebElement(testElement.getIdentifier(), testElement.getSelector(),
                testElement.getVariableName(), true, false, 5);
        return (element != null) && LocalisationHelper.isElementenabled(element);
    }

    /**
     * Return true when the element is not present in the page
     *
     * @param testElement
     * @return
     */
    public static boolean isElementNotPresent(final MonkeyWebElement testElement) {
        return !LocalisationHelper.isElementPresent(testElement);
    }

    /**
     * Return true when the element is selected in the page
     *
     * @param testElement
     * @return
     */
    public static boolean isElementSelected(final MonkeyWebElement testElement) {
        final WebElement element = testElement.getWebElement();
        return element.isSelected();
    }

}
