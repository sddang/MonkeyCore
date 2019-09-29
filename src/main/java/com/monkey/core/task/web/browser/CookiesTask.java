

package com.monkey.core.task.web.browser;

import org.openqa.selenium.Cookie;

import com.monkey.api.web.browser.Cookies;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.AbstractTask;

public class CookiesTask extends AbstractTask {

    private String name;
    private String cookyName;
    private String value;
    private String description;

    /**
     * Fire the events of managing cokkies with the selenium implementation
     */
    @Override
    public void execute() {
        if (this.name.equals(Cookies.ADD_COOKIES)) {
            this.description = "Add cookie << " + this.getCookyName() + " >>";
            ExecutionManager.getMonkeyDriver().manage().addCookie(new Cookie(this.getCookyName(), this.getValue()));
        } else if (this.name.equals(Cookies.REMOVE_ALL_COOKIES)) {
            this.description = "Delete all the cookies";
            ExecutionManager.getMonkeyDriver().manage().deleteAllCookies();
        } else if (this.name.equals(Cookies.REMOVE_COOKIES)) {
            this.description = "Remove the cooky << " + this.cookyName + " >>";
            ExecutionManager.getMonkeyDriver().manage().deleteCookieNamed(this.cookyName);
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
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the cookyName
     */
    public String getCookyName() {
        return this.cookyName;
    }

    /**
     * @param cookyName the cookyName to set
     */
    public void setCookyName(final String cookyName) {
        this.cookyName = cookyName;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(final String value) {
        this.value = value;
    }

}
