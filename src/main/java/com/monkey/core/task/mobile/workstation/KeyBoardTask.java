package com.monkey.core.task.mobile.workstation;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.monkey.api.MonkeyExecutionContext;
import com.monkey.api.MonkeyLogger;
import com.monkey.api.enumeration.LogLevel;
import com.monkey.api.mobile.workstation.KeyBoard;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.AbstractTask;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.HideKeyboardStrategy;

public class KeyBoardTask extends AbstractTask {

    private String name;
    private String description;

    @SuppressWarnings("unchecked")
    @Override
    public void execute() {
        switch (this.name) {
            case KeyBoard.HIDE_KEYBOARD:
                this.description = "Hide keyboard";
                try {
                    if (this.getElement() != null) {
                        this.getElement().click();
                    } else {
                        if (MonkeyExecutionContext.isAndroid()) {
                            ((AndroidDriver<WebElement>) (ExecutionManager.getMonkeyDriver())).hideKeyboard();
                        }

                        if (MonkeyExecutionContext.isIOS()) {
                            ((IOSDriver<WebElement>) (ExecutionManager.getMonkeyDriver()))
                                    .hideKeyboard(HideKeyboardStrategy.TAP_OUTSIDE, "Done");
                        }
                    }
                } catch (final Exception e) {
                    MonkeyLogger.log(getClass().getName(), "Keyboard does not appear...", LogLevel.INFO);
                }
                break;
            case KeyBoard.TAP_ENTER:
                this.description = "Tap enter on keyboard";
                if (MonkeyExecutionContext.isAndroid())
                    ((AndroidDriver<WebElement>) (ExecutionManager.getMonkeyDriver())).pressKeyCode(AndroidKeyCode.ENTER);

                if (MonkeyExecutionContext.isIOS()) {
                    ((IOSDriver<WebElement>) (ExecutionManager.getMonkeyDriver())).getKeyboard().sendKeys(Keys.ENTER);
                }
                break;

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

    public void setName(final String name) {
        this.name = name;
    }

}
