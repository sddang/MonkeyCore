

package com.monkey.core.task.common;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.monkey.api.Wait;
import com.monkey.core.exception.ExceptionCode;
import com.monkey.core.exception.MonkeyException;
import com.monkey.core.page.LocalisationHelper;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.AbstractTask;


public class WaitTask extends AbstractTask {

    private String name;
    private long timeOut;
    private String description;

    boolean elementFound;
    private boolean exitOnError = true;

    public WaitTask() {
        this.initElement = false;
    }

    /**
     * Manage the waiting events
     */
    @Override
    public void execute() {

        final WebElement element;

        if (this.name.equals(Wait.IMPLICITE_WAIT)) {
            this.description = "Implicite Wait for << " + this.timeOut + " >> seconde.";
            try {
                if (this.timeOut > 10) {
                    // limit the timeout to 5 second maximum
                    this.timeOut = 10;
                }
                Thread.sleep(this.timeOut * 1000);
            } catch (final InterruptedException e) {
            }
        } else if (this.name.equals(Wait.WAIT_FOR_PAGE_TO_LOAD)) {
            ExecutionManager.getMonkeyDriver().manage().timeouts()
                    .pageLoadTimeout(ExecutionManager.getConfiguration().getTimeOut(), TimeUnit.SECONDS);
        } else if (this.name.equals(Wait.WAIT_UNTIL_VISIBLE_ELEMENT)) {
            this.description = "Wait until element << " + this.getElement() + " >> will be visible in the screen";
            try {
                final long configuredTimeOut = ExecutionManager.getConfiguration().getTimeOut();
                if (this.timeOut > configuredTimeOut || this.timeOut == 0) {
                    // limit the timeOut to the configured timeout
                    this.timeOut = configuredTimeOut;
                }
                final WebDriverWait wwait = new WebDriverWait(ExecutionManager.getMonkeyDriver(), Duration.ofSeconds(this.timeOut));
                element = wwait.until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(final WebDriver d) {
                        final WebElement el = LocalisationHelper.findWebElement(WaitTask.this.getElement().getIdentifier(),
                                WaitTask.this.getElement().getSelector(), WaitTask.this.getElement().getVariableName(), false, true, WaitTask.this.timeOut);
                        if (el.isDisplayed())
                            return el;
                        return null;
                    }
                });
                this.elementFound = (element != null);
            } catch (final Exception e) {
                if (this.exitOnError) {
                    final StringBuffer message = new StringBuffer(300);
                    message.append(" << varName : ").append(this.getElement().getVariableName()).append(" || selector : ")
                            .append(this.getElement().getSelector()).append(" ||  key : ")
                            .append(this.getElement().getIdentifier()).append(" >> \n Selenium error  :\n ");
                    throw new MonkeyException(ExceptionCode.FIELD_NOT_FOUND_EXCEPTION,
                            message.append(e.getMessage()).toString());
                }
            }

        } else if (this.name.equals(Wait.WAIT_UNTIL_VISIBLE_ENABLED_ELEMENT)) {
            this.description = "Wait untill element << " + this.getElement() + " >> will be visible and enabled on the screen";
            try {
                final long configuredTimeOut = ExecutionManager.getConfiguration().getTimeOut();
                if (this.timeOut > configuredTimeOut || this.timeOut == 0) {
                    // limit the timeOut to the configured timeout
                    this.timeOut = configuredTimeOut;
                }
                final WebDriverWait wwait = new WebDriverWait(ExecutionManager.getMonkeyDriver(), Duration.ofSeconds(this.timeOut));
                element = wwait.until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(final WebDriver d) {
                        final WebElement el = LocalisationHelper.findWebElement(WaitTask.this.getElement().getIdentifier(),
                                WaitTask.this.getElement().getSelector(), WaitTask.this.getElement().getVariableName(), false, true, WaitTask.this.timeOut);
                        if (el.isDisplayed() && el.isEnabled())
                            return el;
                        return null;
                    }
                });
                this.elementFound = (element != null);
            } catch (final Exception e) {
                if (this.exitOnError) {
                    final StringBuffer message = new StringBuffer(300);
                    message.append(" << varName : ").append(this.getElement().getVariableName()).append(" || selector : ")
                            .append(this.getElement().getSelector()).append(" ||  key : ")
                            .append(this.getElement().getIdentifier()).append(" >> \n Selenium error  :\n ");
                    throw new MonkeyException(ExceptionCode.FIELD_NOT_FOUND_EXCEPTION,
                            message.append(e.getMessage()).toString());
                }
            }
        }

    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * the name to set
     *
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * return the timeout
     *
     * @return the timeOut
     */
    public long getTimeOut() {
        return this.timeOut;
    }

    /**
     * the timeOut to set
     *
     * @param timeOut
     */
    public void setTimeOut(final long timeOut) {
        this.timeOut = timeOut;
    }

    public void setExitOnError(final boolean exitOnError) {
        this.exitOnError = exitOnError;
    }

    public boolean isElementFound() {
        return this.elementFound;
    }

    protected boolean isVerbose() {
        return true;
    }

}
