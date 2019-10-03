package com.monkey.core.task.web.element;

import com.monkey.api.Wait;
import com.monkey.core.task.AbstractTask;

public class RadioCheckTask extends AbstractTask {
    /**
     * Execute  enabling or disabling events using the Selenium implementation
     */
    @Override
    public void execute() {
        this.getElement().getWebElement().click();
        Wait.implicitWait(1);
    }

    @Override
    public String getDescription() {
        return "Check the radio \n[ " + this.getElement() + " ]";
    }

    @Override
    public String getName() {
        return "CheckRadioBox";
    }

}
