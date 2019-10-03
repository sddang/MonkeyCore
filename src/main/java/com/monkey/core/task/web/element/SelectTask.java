package com.monkey.core.task.web.element;

import com.monkey.api.Wait;
import com.monkey.core.task.AbstractTask;
import com.monkey.services.data.DataMapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectTask extends AbstractTask {

    private String description;

    /**
     * Execute the select event using the Selenium implementation
     */
    @Override
    public void execute() {

        final WebElement webElement = this.getElement().getWebElement();

        final String value = DataMapper.getSessionMapper().mapData(this.getElement().getInputValue());
        this.description = "Select the option \"" + value + "\" for the select \n[ " + this.getElement() + " ]";
        final Select select = new Select(webElement);
        try {
            select.selectByVisibleText(value);
        } catch (final Exception e) {
            try {
                select.selectByValue(value);
            } catch (final Exception ee) {
                //try {
                select.selectByIndex(Integer.valueOf(value));
                //} catch (Exception eee) {
                //Assertion.fail(eee.getMessage());
                //}
            }
        }
        Wait.implicitWait(1);

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
