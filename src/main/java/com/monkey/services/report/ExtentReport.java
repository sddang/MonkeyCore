package com.monkey.services.report;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;

import com.monkey.api.MonkeyLogger;
import com.monkey.api.enumeration.LogLevel;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.utils.Utils;
import com.monkey.services.ScreenShotTaker;
import com.monkey.services.log.LogTrackerEvent;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport {

    public static final String EXTENT_REPORT_PATH = "./target/extent-reports/";
    public static final String EXTENT_REPORT_EXTENSION = ".html";
    public static final String EXTENT_REPORT_PATH_TO_DATA_FILE_PATH = "../classes/";
    public static final String PROJECT_RELATIVE_PATH = "../../";

    @SuppressWarnings("unchecked")
    public static MonkeyExtentReport createReport(final String suiteName) {
        final MonkeyExtentReport report = new MonkeyExtentReport(ExtentReport.EXTENT_REPORT_PATH + suiteName + ExtentReport.EXTENT_REPORT_EXTENSION,
                true);

        /* Add desired capabilitises to report SystemInfo */
        final Map<String, String> configd = new HashMap<String, String>();
        final DesiredCapabilities desiredCapabilities = ExecutionManager.getConfiguration().getCapabilities();
        final Map<String, Object> caps = desiredCapabilities.asMap();
        for (final String capabilityName : caps.keySet()) {
            if (desiredCapabilities.getCapability(capabilityName) != null) {
                configd.put(capabilityName, desiredCapabilities.getCapability(capabilityName).toString());
            } else {
                configd.put(capabilityName, "");
            }
        }
        report.addSystemInfo(configd);

        /* Add executionContext to report SystemInfo */
        report.addSystemInfo(ExecutionManager.getExecutionContext());

        report.assignProject(suiteName);
        return report;
    }

    public static void closeExtentReport() {
        if (ExecutionManager.getReport() != null) {
            ExecutionManager.getReport().flush();

            if (ExecutionManager.getConfiguration().isExtentx()) {
                ExecutionManager.getReport().x();
            }

            try {
                ExecutionManager.getReport().close();
            } catch (final Exception e) {
                // Shouldn't throw an error when the report is empty : issue
                // created
                e.printStackTrace();
                LogTrackerEvent.trace(ExtentReport.class.getName(), "Error : pb happen when calling ExtentReport.close() : " + e.getMessage());
            }
        }
    }

    public static void startTestReport(final ITestResult testResult, final String dataFilePath) {
        if (ExecutionManager.getReport() != null) {
            String dataLink = "No data file";
            if (dataFilePath != null) {
                dataLink = "<a href=\"" + ExtentReport.EXTENT_REPORT_PATH_TO_DATA_FILE_PATH + dataFilePath + "\">Data file : "
                        + dataFilePath + "</a>";
            }
            String testDescription = testResult.getMethod().getDescription();
            if (testDescription == null) {
                testDescription = dataLink;
            } else {
                testDescription = testDescription + "<br>" + dataLink;
            }

            ExecutionManager.getMonkeyDriver().setTestReport(ExecutionManager.getReport()
                    .startTest(testResult.getInstanceName() + "." + testResult.getName(), testDescription));
            for (final String group : testResult.getMethod().getGroups())
                ExecutionManager.getMonkeyDriver().getTestReport().assignCategory(group);
            ExecutionManager.getMonkeyDriver().getTestReport().assignCategory(testResult.getInstanceName());
        }
    }

    /**
     * This method is called in the retry case
     *
     * @param testName
     */
    public static void removeExtraReportTest() {
        ExecutionManager.getReport().getReportTestList().remove(ExecutionManager.getMonkeyDriver().getTestReport());
    }

    public static void startTestReport(final String testName) {
        if (ExecutionManager.getReport() != null) {
            ExecutionManager.getMonkeyDriver().setTestReport(ExecutionManager.getReport().startTest(testName));
        }
    }

    public static void endTest() {
        if (ExecutionManager.getReport() != null) {
            try {
                ExecutionManager.getReport().endTest(ExecutionManager.getMonkeyDriver().getTestReport());
            } catch (final Exception e) {
                e.printStackTrace();
                MonkeyLogger.log(ScreenShotTaker.class.getName(), "Error : Pb happen when closing extentReport for an executed test!!!\n" + e.getMessage(), LogLevel.FATAL);
            }
        }
    }

    public static void assignGroups(final String[] groups) {
        if (ExecutionManager.getMonkeyDriver().getTestReport() != null) {
            for (final String group : groups)
                ExecutionManager.getMonkeyDriver().getTestReport().assignCategory(group);
        }
    }

    public static void log(final LogStatus logStatus, final String step) {
        if (ExecutionManager.getMonkeyDriver().getTestReport() != null) {
            ExecutionManager.getMonkeyDriver().getTestReport().log(logStatus, step, "");
        }
    }

    public static void logWithScreenshot(final LogStatus logStatus, final String step, final String screenshotPath) {
        if (ExecutionManager.getMonkeyDriver().getTestReport() != null) {
            ExecutionManager.getMonkeyDriver().getTestReport().log(logStatus, step, ExecutionManager.getMonkeyDriver()
                    .getTestReport().addScreenCapture(ExtentReport.PROJECT_RELATIVE_PATH + screenshotPath));
        }
    }

    public static void logWithScreenshot(final LogStatus logStatus, final Throwable t, final String screenshotPath) {
        if (ExecutionManager.getMonkeyDriver().getTestReport() != null) {
            ExecutionManager.getMonkeyDriver().getTestReport().log(logStatus, ExtentReport.throwableToString(t), ExecutionManager
                    .getMonkeyDriver().getTestReport().addScreenCapture(ExtentReport.PROJECT_RELATIVE_PATH + screenshotPath));
        }
    }

    private static String throwableToString(final Throwable t) {
        return "<pre>" + Utils.getStackTrace(t) + "</pre>";
    }


//	public static void logIfUnknowTest(boolean isSuccess) {
//		if(isSuccess){
//			ExecutionManager.getmonkeyDriver().getTestReport().log(LogStatus.PASS, "End test with success!");
//			return;
//		}
//		if (ExecutionManager.getmonkeyDriver().getTestReport().getRunStatus().equals(LogStatus.UNKNOWN)){
//			ExecutionManager.getmonkeyDriver().getTestReport().log(LogStatus.INFO, "End test");
//		}
//					
//	}

    public static void logFailedTest(final Throwable t) {
        ExecutionManager.getMonkeyDriver().getTestReport().log(LogStatus.FAIL, ExtentReport.throwableToString(t));
    }

    public static void logSuccessedTest() {
        switch (ExecutionManager.getMonkeyDriver().getTestReport().getRunStatus()) {
            case ERROR:
                ExecutionManager.getMonkeyDriver().getTestReport().log(LogStatus.ERROR, "End test with error!");
                break;
            case WARNING:
                ExecutionManager.getMonkeyDriver().getTestReport().log(LogStatus.WARNING, "End test with warning!");
                break;
            default:
                ExecutionManager.getMonkeyDriver().getTestReport().log(LogStatus.PASS, "End test with success!");
                break;
        }
    }

    public static void logSkippedTest() {
        ExecutionManager.getMonkeyDriver().getTestReport().log(LogStatus.WARNING, "Test is skipped!");
    }

}
