package com.monkey.api.mobile;

import java.util.Set;

import org.openqa.selenium.WebElement;

import com.monkey.api.Wait;
import com.monkey.core.exception.ExceptionCode;
import com.monkey.core.exception.MonkeyException;
import com.monkey.core.session.ExecutionManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Driver {

    @SuppressWarnings("unchecked")
    public static AppiumDriver<WebElement> getAppiumDriver() {
        return (AppiumDriver<WebElement>) ExecutionManager.getMonkeyDriver();
    }

    public static String getContext() {
        return Driver.getAppiumDriver().getContext();
    }

    public static Boolean isContextNative() {
        final AppiumDriver<WebElement> driver = Driver.getAppiumDriver();
        final Set<String> contexts = driver.getContextHandles();
        return driver.getContext().equals(contexts.toArray()[0]);
    }

    public static void switchContextToNative() {
        Driver.switchContext(0);
    }

    public static void switchContextToLastWebview() {
        // AppiumDriver<WebElement> driver = getAppiumDriver();

        final Set<String> contexts = Driver.getAppiumDriver().getContextHandles();
        if (contexts.size() == 1) {
            throw new MonkeyException(ExceptionCode.WEB_VIEW_CONTEXT_NOT_FOUND);
        }
        Driver.getAppiumDriver().context((String) contexts.toArray()[contexts.size() - 1]);
    }

    public static void switchToNonBlankWebContext() {
        final Set<String> contexts = Driver.getAppiumDriver().getContextHandles();
        if (contexts.size() == 1) {
            throw new MonkeyException(ExceptionCode.WEB_VIEW_CONTEXT_NOT_FOUND);
        }
        String tempContext = "";
        for (int i = contexts.size() - 1; i >= 1; i--) {
            Driver.getAppiumDriver().context((String) contexts.toArray()[i]);
            tempContext = ExecutionManager.getMonkeyDriver().getPageSource();
            if (!tempContext.equals("<html><head></head><body></body></html>") && !tempContext.equals("")
                    && tempContext != null) {
                return;
            }
            Wait.implicitWait(2);
        }
    }

    public static void switchContext(final int contextIndex) {
        final AppiumDriver<WebElement> driver = Driver.getAppiumDriver();
        final Set<String> contexts = driver.getContextHandles();
        if (contexts.size() < contextIndex) {
            throw new MonkeyException(ExceptionCode.WEB_VIEW_CONTEXT_INDEX_NOT_FOUND, String.valueOf(contextIndex),
                    contexts.toString());
        }
        driver.context((String) contexts.toArray()[contextIndex]);
    }

    public static void switchContext(final String contextName) {
        final AppiumDriver<WebElement> driver = Driver.getAppiumDriver();
        driver.context(contextName);

    }

    public static void switchWebViewWindowToLast() {
        final AppiumDriver<WebElement> driver = Driver.getAppiumDriver();
        final AndroidDriver<?> androidDriver = (AndroidDriver<?>) driver;
        final Set<String> windows = androidDriver.getWindowHandles();
        androidDriver.switchTo().window((String) windows.toArray()[windows.size() - 1]);
    }

    public static void switchWebViewWindow(final int windowNum) {
        final AppiumDriver<WebElement> driver = Driver.getAppiumDriver();
        final AndroidDriver<?> androidDriver = (AndroidDriver<?>) driver;
        final Set<String> windows = androidDriver.getWindowHandles();
        final int webViewWindowsNb = windows.size();

        if (windowNum < 1 || webViewWindowsNb < windowNum) {
            throw new MonkeyException(ExceptionCode.WRONG_WINDOW_NUM_TO_SWITCH, "1", String.valueOf(webViewWindowsNb));
        }

        androidDriver.switchTo().window((String) windows.toArray()[windowNum - 1]);
    }

    public static void hideKeyboard() {
        Driver.getAppiumDriver().hideKeyboard();
    }

    public static void waitUntilWebviewLoaded() {
        int i = 0;
        final AppiumDriver<WebElement> driver = Driver.getAppiumDriver();
        Set<String> contexts = driver.getContextHandles();
        while (contexts.size() < 2 && i < 6) {
            Wait.implicitWait(10);
            contexts = driver.getContextHandles();
            i++;
        }
    }

    public static void resetApp() {
        Driver.getAppiumDriver().resetApp();
    }

    public static void startActivity(final String appPackage, final String appActivity) {
        final AppiumDriver<WebElement> driver = Driver.getAppiumDriver();
        final AndroidDriver<?> androidDriver = (AndroidDriver<?>) driver;
        androidDriver.startActivity(appPackage, appActivity);
    }
}
