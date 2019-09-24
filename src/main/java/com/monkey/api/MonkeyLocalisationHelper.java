package com.monkey.api;

import org.openqa.selenium.WebElement;

import com.monkey.api.page.MonkeyWebElement;
import com.monkey.core.page.LocalisationHelper;

public class MonkeyLocalisationHelper extends LocalisationHelper {

	/**
	 * Return true if the element is present in the Page and it's enabled
	 * 
	 * @param testElement
	 * @return
	 */
	public static boolean isElementPresent(final MonkeyWebElement testElement, final int timeOut) {
		final WebElement element = LocalisationHelper.findWebElement(testElement.getIdentifier(), testElement.getSelector(),
				testElement.getVariableName(), true, false, timeOut);
		return (element != null) && LocalisationHelper.isElementenabled(element);
	}
	
}
