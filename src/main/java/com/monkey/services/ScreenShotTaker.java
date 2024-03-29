package com.monkey.services;

import com.monkey.api.Wait;
import com.monkey.api.mobile.Driver;
import com.monkey.core.config.MonkeyConfig;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.utils.Utils;
import com.monkey.services.log.LogTrackerEvent;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

//import com.monkey.api.monkeyLogger;
//import com.monkey.api.enumeration.LogLevel;
//import com.monkey.core.exception.ExceptionCode;
//import com.monkey.core.exception.MonkeyException;

public class ScreenShotTaker {

    public static final String SCREENSHOT_PATH = "target/screenshots/";
    public static final String SCREENSHOT_EXTENSION = ".png";

    public static String takeScreenshots() {
        String screenshotFilePath = null;
        try {
            final MonkeyConfig config = ExecutionManager.getConfiguration();
            switch (config.getProtocol()) {
                case selenium:
                    screenshotFilePath = ScreenShotTaker.takeScreenShot();
                    break;
                case appium:
                    screenshotFilePath = ScreenShotTaker.takeScreenShotAppium();
                    break;
            }
        } catch (final Exception e) {
//			throw new monkeyException(ExceptionCode.SCREENSHOT_GENERATION_ERROR,
//					e.getMessage() == null ? e.getCause().getMessage() : e.getMessage());
            e.printStackTrace();
            LogTrackerEvent.trace(ScreenShotTaker.class.getName(), "Error : Pb happen when taken screenshot!!!\n" + e.getMessage());
        }
        return screenshotFilePath;
    }

    private static String takeScreenShot() throws Exception {
        RemoteWebDriver driver = (RemoteWebDriver) ExecutionManager.getMonkeyDriver();
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File tmpFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
//        File tmpFile = ExecutionManager.getMonkeyDriver().getScreenshotAs(OutputType.FILE);
        Wait.implicitWait(2);
        String[] keyWords = ExecutionManager.getMonkeyDriver().getTestFileName().split("\\.");
        String shortName = keyWords[keyWords.length - 1] + "_" + keyWords[keyWords.length - 2];
        String screenshotFilePath = ScreenShotTaker.SCREENSHOT_PATH + File.separator
                + shortName + Math.random() + ScreenShotTaker.SCREENSHOT_EXTENSION;
        FileUtils.copyFile(tmpFile, new File(screenshotFilePath));
        tmpFile.delete();
        return screenshotFilePath;
    }

    private static String takeScreenShotAppium() throws Exception {
        String currentContext = Driver.getContext();
        Driver.switchContextToNative();
        File tmpFile = ExecutionManager.getMonkeyDriver().getScreenshotAs(OutputType.FILE);
        String[] keyWords = ExecutionManager.getMonkeyDriver().getTestFileName().split("\\.");
        String shortName = keyWords[keyWords.length - 1] + "_" + keyWords[keyWords.length - 2];
        String screenshotFilePath = ScreenShotTaker.SCREENSHOT_PATH + File.separator
                + shortName + Math.random() + ScreenShotTaker.SCREENSHOT_EXTENSION;
        FileUtils.copyFile(tmpFile, new File(screenshotFilePath));
        tmpFile.delete();
        Driver.switchContext(currentContext);
        return screenshotFilePath;
    }

    /**
     * Clean up the directory of the generated sreenShots and create new one.
     */
    public static void cleanUpScreenShotDir() {
        Utils.cleanDirectory(ScreenShotTaker.SCREENSHOT_PATH);
    }

}
