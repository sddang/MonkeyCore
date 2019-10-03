package com.monkey.api;

import com.monkey.api.enumeration.LogLevel;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.TaskStatus;
import com.monkey.services.ScreenShotTaker;
import com.monkey.services.documentation.DocumentationEvent;
import com.monkey.services.documentation.DocumentationType;
import com.monkey.services.log.LogTrackerEvent;
import com.monkey.services.report.ExtentReport;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Reporter;

public class MonkeyLogger {

    public static void trace(final String className, final String description) {
        LogTrackerEvent.trace(className, description);
    }

    public static void log(final String className, final String message, final LogLevel level) {
        final TaskStatus docStatus;
        final LogStatus reportStatus;
        switch (level) {
            case FATAL:
                docStatus = TaskStatus.FAILED;
                reportStatus = LogStatus.FATAL;
                break;
            case ERROR:
                docStatus = TaskStatus.FAILED;
                reportStatus = LogStatus.ERROR;
                break;
            case WARNING:
                docStatus = TaskStatus.SUCCESSFUL;
                reportStatus = LogStatus.WARNING;
                break;
            case FAIL:
                docStatus = TaskStatus.FAILED;
                reportStatus = LogStatus.FAIL;
                break;
            case INFO:
                docStatus = TaskStatus.SUCCESSFUL;
                reportStatus = LogStatus.INFO;
                break;
            case PASS:
                docStatus = TaskStatus.SUCCESSFUL;
                reportStatus = LogStatus.PASS;
                break;
            default:
                docStatus = TaskStatus.SUCCESSFUL;
                reportStatus = LogStatus.UNKNOWN;
        }

        Reporter.log(message);

        if (ExecutionManager.getConfiguration().isGenerateFunctionalDoc()) {
            final String screenshotFilePath = ScreenShotTaker.takeScreenshots();
            DocumentationEvent.addEvent(className, message, screenshotFilePath, docStatus,
                    DocumentationType.FUNCTIONAL);
            ExtentReport.logWithScreenshot(reportStatus, message, screenshotFilePath);
        } else {
            if (reportStatus.equals(LogStatus.FATAL) || reportStatus.equals(LogStatus.ERROR)
                    || reportStatus.equals(LogStatus.FAIL)) {
                final String screenshotFilePath = ScreenShotTaker.takeScreenshots();
                ExtentReport.logWithScreenshot(reportStatus, message, screenshotFilePath);
            } else {
                ExtentReport.log(reportStatus, message);
            }
        }

    }

}
