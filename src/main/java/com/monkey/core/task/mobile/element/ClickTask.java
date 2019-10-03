package com.monkey.core.task.mobile.element;

import com.monkey.core.task.AbstractTask;

public class ClickTask extends AbstractTask {

    @Override
    public void execute() {
        this.getElement().getWebElement().click();
        //MobileElement webElement = (MobileElement) getElement().getWebElement();
        //webElement.tap(1, 200);
    }

    @Override
    public String getDescription() {
        return "click on element \n[ " + this.getElement() + " ]";
    }

    @Override
    public String getName() {
        return "ClickElement";
    }

}
