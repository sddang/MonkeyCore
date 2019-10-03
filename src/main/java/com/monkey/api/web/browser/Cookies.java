package com.monkey.api.web.browser;

import com.monkey.core.task.web.browser.CookiesTask;

/**
 * This class manage the cookies
 */
public class Cookies {

    public static final String ADD_COOKIES = "addCookies";
    public static final String REMOVE_COOKIES = "removeCookies";
    public static final String REMOVE_ALL_COOKIES = "removeAllCookies";

    /**
     * Add profil cookie to the browser
     *
     * @param cookieName the name of the cookie
     * @param value      the value to be set in the cookie
     */
    public static void addCookies(final String cookieName, final String value) {
        final CookiesTask cookiesTask = new CookiesTask();
        cookiesTask.setName(Cookies.ADD_COOKIES);
        cookiesTask.setCookyName(cookieName);
        cookiesTask.setValue(value);
        cookiesTask.runTask();
    }

    /**
     * Remove profil given cookie
     *
     * @param cookyName the cookie name
     */
    public static void removeCookies(final String cookyName) {
        final CookiesTask cookiesTask = new CookiesTask();
        cookiesTask.setName(Cookies.REMOVE_COOKIES);
        cookiesTask.setCookyName(cookyName);
        cookiesTask.runTask();
    }

    /**
     * Remove all the cookies.
     */
    public static void removeAllCookies() {
        final CookiesTask cookiesTask = new CookiesTask();
        cookiesTask.setName(Cookies.REMOVE_ALL_COOKIES);
        cookiesTask.runTask();
    }

}
