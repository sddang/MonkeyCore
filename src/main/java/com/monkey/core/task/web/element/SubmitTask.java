package com.monkey.core.task.web.element;

import com.monkey.api.Wait;
import com.monkey.api.web.workstation.KeyBoard;
import com.monkey.core.enumeration.Protocol;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.AbstractTask;
import org.openqa.selenium.WebElement;


public class SubmitTask extends AbstractTask {
    /**
     * Execute the submit action using the Selenium implementation monkey Submit
     * action is more improved
     */
    @Override
    public void execute() {
        final WebElement webElement = this.getElement().getWebElement();
        if (ExecutionManager.getProtocol().equals(Protocol.appium)) {
            webElement.submit();
            Wait.implicitWait(1);
        } else {
            int i = 0;
            while (i < 5) {
                try {
                    webElement.submit();
                    break;
                } catch (final Exception e) {
                    i = i + 1;
                    Wait.implicitWait(3);
                    if (i == 1 || i == 2) {
                        KeyBoard.pageDown();
                    } else {
                        KeyBoard.pageDown();
                    }
                }
            }
        }
    }

    @Override
    public String getDescription() {
        return "Submit element \n[ " + this.getElement() + " ]";
    }

    @Override
    public String getName() {
        return "SubmitElement";
    }

}
