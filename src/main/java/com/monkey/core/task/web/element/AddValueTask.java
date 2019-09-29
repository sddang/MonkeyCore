
/**
 * This package contains all the tasks that manage all the actions in monkey
 * Here we find the implementation of Selenium
 */
package com.monkey.core.task.web.element;

import org.openqa.selenium.WebElement;

import com.monkey.core.task.AbstractTask;
import com.monkey.services.data.DataMapper;

public class AddValueTask extends AbstractTask {
    private String description;

    /**
     * Add value to the element with the selenium implementation
     */
    @Override
    public void execute() {
        final WebElement element = this.getElement().getWebElement();
        final String value = DataMapper.getSessionMapper().mapData(
                this.getElement().getInputValue());
        this.description = "Add value : << " + value + " >> to the element << "
                + this.getElement() + " >>";
        element.sendKeys(value);

    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getName() {
        return "AddValue";
    }

}
