

package com.monkey.core.task.web.element;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.monkey.core.task.AbstractTask;

public class ClearTask extends AbstractTask {

	/**
	 * Fire the event of clearing value of an element with the selenium implementation
	 */
	@Override
	public void execute() {
		final WebElement element = this.getElement().getWebElement();
		element.sendKeys(Keys.chord(Keys.CONTROL, "A"), Keys.DELETE);
		final String elementValue = element.getText() != null
				&& !element.getText().equals("") ? element.getText() : element
				.getAttribute("value");
		if (!StringUtils.isBlank(elementValue)) {
			element.clear();
		}
	}

	@Override
	public String getDescription() {
		return "Clear the conent of the element << " + this.getElement() + " >>";
	}
	
	@Override
	public String getName() {
		return "ClearElementValue";
	}

}
