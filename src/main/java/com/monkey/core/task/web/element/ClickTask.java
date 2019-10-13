package com.monkey.core.task.web.element;

import com.monkey.api.MonkeyExecutionContext;
import com.monkey.api.Wait;
import com.monkey.api.web.workstation.KeyBoard;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.AbstractTask;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ClickTask extends AbstractTask {

    public boolean allowDisabledElement;

    @Override
    public boolean allowEnabledElement() {
        return this.allowDisabledElement;
    }

    /**
     * Fire the event of clicking element with the selenium implementation. The
     * click of monkey is more improved and works for more cases than the native
     * click of Selenium
     */
    @Override
    public void execute() {
        final WebElement webElement = this.getElement().getWebElement();
        //if (ExecutionManager.getProtocol().equals(Protocol.appium)) {
        //	webElement.click();
        //	Wait.implicitWait(1);
        //} else {
        int i = 0;
        while (i < 5) {
            try {
                webElement.click();
                Wait.implicitWait(1);
                break;
            } catch (final Exception e) {
                i = i + 1;
                if (i == 1 || i == 2) {
                    KeyBoard.pageDown();
                } else {
                    KeyBoard.pageUp();
                }
            }
        }
        if (!(MonkeyExecutionContext.isIOS()) && !(MonkeyExecutionContext.isAndroid()))
            this.waitForPageToLoad();
        //}
    }

    /**
     * Wait untill the page is loaded
     */
    public void waitForPageToLoad() {
        try {
            final org.openqa.selenium.support.ui.Wait<WebDriver> wait = new WebDriverWait(ExecutionManager.getMonkeyDriver(), ExecutionManager.getConfiguration().getTimeOut());//Duration.ofSeconds(ExecutionManager.getConfiguration().getTimeOut()));
            final ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(final WebDriver driver) {
                    Wait.implicitWait(1);
                    final boolean isComplete = ((JavascriptExecutor) driver).executeScript("return document.readyState")
                            .equals("complete");
                    return isComplete;
                }
            };
            wait.until(expectation);

        } catch (final Throwable error) {
        }
    }

    @Override
    public String getDescription() {
        return "Click on element \n[ " + this.getElement() + " ]";
    }

    @Override
    public String getName() {
        return "ClickElement";
    }

}
