

package com.monkey.core.task.web.element;

import com.monkey.api.MonkeyExecutionContext;
import com.monkey.core.task.AbstractTask;

public class CheckTask extends AbstractTask {

    boolean enabled;

    public CheckTask(final boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Fire the event of checking element with the selenium implementation
     */
    @Override
    public void execute() {
        if (!MonkeyExecutionContext.isIOS()) {
            if (this.enabled ? !this.getElement().getWebElement().isSelected() : this.getElement().getWebElement().isSelected()) {
                this.getElement().getWebElement().click();
            }
        } else {
            this.checkforiOS();
        }
    }

    /**
     * Handle for iOS elements
     */
    private void checkforiOS() {
        final boolean currentState = this.getElement().getWebElement().getAttribute("value") != null && Boolean.parseBoolean(this.getElement().getWebElement().getAttribute("value"));
        if (this.enabled ? !currentState : currentState) {
            this.getElement().click();
        }
    }

    /**
     * Return the description of the Task
     */
    @Override
    public String getDescription() {
        return "Select the checkBox << " + this.getElement() + " >>";
    }

    @Override
    public String getName() {
        return "EnableDisableCheckBox";
    }

}
