
package com.monkey.api;

import com.monkey.api.page.MonkeyWebElement;
import com.monkey.core.task.common.WaitTask;

public class Wait {

    public static final String IMPLICITE_WAIT = "impliciteWait";
    public static final String WAIT_UNTIL_VISIBLE_ELEMENT = "waitUntilVisibleElement";
    public static final String WAIT_UNTIL_VISIBLE_ENABLED_ELEMENT = "waitUntilVisibleEnabledElement";
    public static final String WAIT_FOR_PAGE_TO_LOAD = "waitForPageToLoad";

    /**
     * Wait for the given time
     *
     * @param time is the time on second
     */
    public static void implicitWait(final long time) {
        final WaitTask waitTask = new WaitTask();
        waitTask.setName(Wait.IMPLICITE_WAIT);
        waitTask.setTimeOut(time);
        waitTask.runTask();
    }

    /**
     * Wait for the page to load
     *
     * @param time is the time on second
     */
    public static void waitForPageToLoad(final long time) {
        final WaitTask waitTask = new WaitTask();
        waitTask.setName(Wait.WAIT_FOR_PAGE_TO_LOAD);
        waitTask.setTimeOut(time);
        waitTask.runTask();
    }

    /**
     * Wait until the given element become visible. If this action exceed the timeout it throw
     * an error
     *
     * @param testElement
     * @param timeOut
     */
    public static void waitUntilVisibleElement(final MonkeyWebElement testElement, final long timeOut) {
        final WaitTask waitTask = new WaitTask();
        waitTask.setName(Wait.WAIT_UNTIL_VISIBLE_ELEMENT);
        waitTask.setTimeOut(timeOut);
        waitTask.setElement(testElement);
        waitTask.runTask();
    }

    public static void waitUntilVisibleElement(final MonkeyWebElement testElement, final boolean exitOnError) {
        final WaitTask waitTask = new WaitTask();
        waitTask.setName(Wait.WAIT_UNTIL_VISIBLE_ELEMENT);
        waitTask.setExitOnError(exitOnError);
        waitTask.setElement(testElement);
        waitTask.runTask();
    }

    public static boolean waitUntilVisibleElement(final MonkeyWebElement testElement, final long timeOut, final boolean exitOnError) {
        final WaitTask waitTask = new WaitTask();
        waitTask.setName(Wait.WAIT_UNTIL_VISIBLE_ELEMENT);
        waitTask.setTimeOut(timeOut);
        waitTask.setExitOnError(exitOnError);
        waitTask.setElement(testElement);
        waitTask.runTask();
        return waitTask.isElementFound();
    }

    public static void waitUntilVisibleElement(final MonkeyWebElement testElement) {
        final WaitTask waitTask = new WaitTask();
        waitTask.setName(Wait.WAIT_UNTIL_VISIBLE_ELEMENT);
        waitTask.setElement(testElement);
        waitTask.runTask();
    }

    /**
     * Wait until the given element become enabled. If this action exceed the timeout it throw
     * an error
     *
     * @param testElement
     * @param timeOut
     */
    public static void waitUntilVisibleEnabledElement(final MonkeyWebElement testElement, final long timeOut) {
        final WaitTask waitTask = new WaitTask();
        waitTask.setName(Wait.WAIT_UNTIL_VISIBLE_ENABLED_ELEMENT);
        waitTask.setTimeOut(timeOut);
        waitTask.setElement(testElement);
        waitTask.runTask();
    }

}
