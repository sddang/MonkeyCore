package com.monkey.core.task.mobile.element;

import com.monkey.api.MonkeyExecutionContext;
import com.monkey.core.task.AbstractTask;

public class CheckTask extends AbstractTask {

    boolean enabled;

    public CheckTask(final boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void execute() {
        if (MonkeyExecutionContext.isAndroid()) {
            if (this.enabled ? this.getElement().getWebElement().getAttribute("checked").equals("false") : this.getElement().getWebElement().getAttribute("checked").equals("true")) {
                this.getElement().click();
            }
        } else {
            final boolean elementStt = this.getElement().getWebElement().getAttribute("value") != null && Boolean.parseBoolean(this.getElement().getWebElement().getAttribute("value"));
            if (this.enabled ? !elementStt : elementStt) {
                this.getElement().click();
            }
        }
    }

    @Override
    public String getDescription() {
        return "click on element [ " + this.getElement() + "]";
    }

    @Override
    public String getName() {
        return "EnableDisableCheckBox";
    }

}
