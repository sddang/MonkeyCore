package com.monkey.core.session;

import com.monkey.api.annotation.InjectData;
import com.monkey.core.assertion.MonkeyAssertion;
import com.monkey.core.driver.MonkeyDriverSetup;
import com.monkey.core.task.TaskStatus;
import com.monkey.services.RetryAnalyzer;
import com.monkey.services.ScreenShotTaker;
import com.monkey.services.documentation.DocumentationEvent;
import com.monkey.services.documentation.DocumentationGenerator;
import com.monkey.services.documentation.DocumentationType;
import com.monkey.services.log.LogTrackerEvent;
import com.monkey.services.report.ExtentReport;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.internal.ConstructorOrMethod;

import java.io.IOException;
import java.lang.reflect.Method;


public class TestManager implements ITestListener {

    /**
     * This method is fired after the execution of the test
     */
    // @Override
    // public void afterInvocation(IInvokedMethod method, ITestResult
    // testResult) {
    // if (ExecutionManager.monkeyDriverStarted()) {
    // if (testResult.getThrowable() != null) {
    // String screenshotFilePath = ScreenShotTaker.takeScreenshots();
    // DocumentationEvent.addEvent(method.getTestMethod().getMethodName(),
    // testResult.getThrowable().getMessage(), screenshotFilePath,
    // TaskStatus.FAILED,
    // DocumentationType.ALL);
    // ExtentReport.logWithScreenshot(LogStatus.FAIL, testResult.getThrowable(),
    // screenshotFilePath);
    //
    // }
    //
    // RetryAnalyzer analyzer = (RetryAnalyzer)
    // method.getTestMethod().getRetryAnalyzer();
    // if (analyzer != null && analyzer.canRetry(testResult)) {
    // if (!testResult.isSuccess()) {
    // ExtentReport.removeExtraReportTest();
    // monkeyDriverSetup.closeDriver();
    // } else {
    // ExtentReport.logIfUnknowTest();
    // DocumentationGenerator.generateDocumentation(method);
    // monkeyDriverSetup.closeDriver();
    // LogTrackerEvent.traceEndTest(this.getClass().getName(), method);
    // ExtentReport.endTest();
    // }
    // } else {
    // ExtentReport.logIfUnknowTest();
    // DocumentationGenerator.generateDocumentation(method);
    // monkeyDriverSetup.closeDriver();
    // LogTrackerEvent.traceEndTest(this.getClass().getName(), method);
    // ExtentReport.endTest();
    // }
    // }
    // }
    private String getFormattedFileName(final ITestNGMethod method) {
        return method.getTestClass().getName() + "." + method.getMethodName();
    }

    /**
     * Fired before starting execution of the test when the retry count is >1
     *
     * @param method
     */
    protected void beforeAnalyserRetry(final ITestResult testResult, final ITestNGMethod method) {
        boolean isRunAnalyser = false;
        isRunAnalyser = ExecutionManager.getConfiguration().getRetryTestingCount() > 1;
        if (isRunAnalyser && method.getRetryAnalyzer(testResult) == null) {
            method.setRetryAnalyzerClass(RetryAnalyzer.class);
        }
    }

    /**
     * Return the path of the Json file that describe the data of the test
     *
     * @param method
     * @return
     */
    private String getDataMapperPath(final ITestNGMethod method) {
        final Method m = this.getMethod(method);
        if (m == null || m.getAnnotation(InjectData.class) == null) {
            return null;
        }
        final String jsonFile = m.getAnnotation(InjectData.class).json();
        return jsonFile;
    }

    /**
     * Return the test object in the runtime
     *
     * @param method
     * @return
     */
    private Method getMethod(final ITestNGMethod method) {
        if (!method.isTest()) {
            return null;
        }
        final ConstructorOrMethod com = method.getConstructorOrMethod();
        if (com.getMethod() == null) {
            return null;
        }
        return com.getMethod();
    }

    @Override
    public void onTestStart(final ITestResult testResult) {
        LogTrackerEvent.traceStartMonkeyMessage(getClass().getName());
        final ITestNGMethod method = testResult.getMethod();
        this.beforeAnalyserRetry(testResult, method);
        MonkeyDriverSetup.start();
        ExtentReport.startTestReport(testResult, this.getDataMapperPath(method));
        MonkeyDriverSetup.set(this.getDataMapperPath(method), this.getFormattedFileName(method));
        LogTrackerEvent.traceStartTest(getClass().getName(), method);
    }

    private void onTestEnd(final ITestResult testResult, final boolean isSuccess) throws InterruptedException, IOException {
        final ITestNGMethod method = testResult.getMethod();
        try {
            LogTrackerEvent.traceEndTest(getClass().getName(), method);
            DocumentationGenerator.generateDocumentation(method);
            ExtentReport.endTest();
        } catch (final Exception e) {
            ExtentReport.endTest();
        } finally {
//            MonkeyAssertion.getSoftAssert().assertAll();
            MonkeyDriverSetup.closeDriver();
        }

    }

    @Override
    public void onTestSuccess(final ITestResult result) {
        ExtentReport.logSuccessfulTest();
        try {
            this.onTestEnd(result, true);
        } catch (final InterruptedException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(final ITestResult testResult) {
        final ITestNGMethod method = testResult.getMethod();
        if (ExecutionManager.monkeyDriverStarted()) {
            if (testResult.getThrowable() != null) {
                final String screenshotFilePath = ScreenShotTaker.takeScreenshots();
                DocumentationEvent.addEvent(method.getMethodName(), testResult.getThrowable().getMessage(),
                        screenshotFilePath, TaskStatus.FAILED, DocumentationType.ALL);
                ExtentReport.logWithScreenshot(LogStatus.FAIL, testResult.getThrowable(), screenshotFilePath);
            }

            final RetryAnalyzer analyzer = (RetryAnalyzer) method.getRetryAnalyzer(testResult);
            if (analyzer != null && analyzer.canRetry(testResult)) {
                ExtentReport.removeExtraReportTest();
                MonkeyDriverSetup.closeDriver();
            } else {
                try {
                    this.onTestEnd(testResult, false);
                } catch (final InterruptedException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onTestSkipped(final ITestResult result) {
        // TODO Auto-generated method stub
        ExtentReport.logSkippedTest();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(final ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStart(final ITestContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFinish(final ITestContext context) {
        // TODO Auto-generated method stub

    }

}
