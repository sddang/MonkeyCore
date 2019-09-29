
package com.monkey.core.task.mobile.element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.monkey.api.Assertion;
import com.monkey.api.Wait;
import com.monkey.core.task.AbstractTask;
import com.monkey.services.data.DataMapper;
import org.testng.Assert;


public class SelectTask extends AbstractTask {

    private String description;

    /**
     * Execute the select event using the Selenium implementation
     */
    @Override
    public void execute() {

        final WebElement webElement = this.getElement().getWebElement();

        final String value = DataMapper.getSessionMapper().mapData(this.getElement().getInputValue());
        this.description = "Select the option << " + value + " >> for the select << " + this.getElement() + " >>";
        final Select select = new Select(webElement);
        try {
            select.selectByVisibleText(value);
        } catch (final Exception e) {
            try {
                select.selectByValue(value);
            } catch (final Exception ee) {
                try {
                    select.selectByIndex(Integer.valueOf(value));
                } catch (final Exception eee) {
                    Assert.fail(eee.getMessage());
                }
            }
        }
        Wait.implicitWait(2);

    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getName() {
        return "SelectOption";
    }

}
