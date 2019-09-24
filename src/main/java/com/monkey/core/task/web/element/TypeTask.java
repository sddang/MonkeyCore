

package com.monkey.core.task.web.element;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;

import com.monkey.api.Wait;
import com.monkey.core.task.AbstractTask;
import com.monkey.services.data.DataMapper;

public class TypeTask extends AbstractTask {

	private String description;

	/**
	 * Execute the type action of the elements using the selenum implementation
	 * The Type action of monkey is more improved
	 */
	@Override
	public void execute() {
		final String value = DataMapper.getSessionMapper().mapData(this.getElement().getInputValue());
		final WebElement element = this.getElement().getWebElement();

		final String elementValue = element.getText() != null && !element.getText().equals("") ? element.getText()
				: element.getAttribute("value");
        this.description = "Type data :\"" + value + "\" in the input [ " + this.getElement() + " ]";
		if (!StringUtils.isBlank(elementValue)) {
			element.clear();
		}
		if (!StringUtils.isBlank(value)) {
			element.sendKeys(value);
			if (value.length() > 300) {
				Wait.implicitWait(1);
			}
		}
	}

	@Override
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public String getName() {
		return "TypeValue";
	}

}
