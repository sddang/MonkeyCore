package com.monkey.services;

import com.monkey.core.session.ExecutionManager;
import com.monkey.core.session.TestListenerUtil;
import com.monkey.services.log.LogTrackerEvent;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;


public class RetryAnalyzer implements IRetryAnalyzer {

    private static Integer maxRetries;
    private final Map<Integer, Integer> retryCount = new HashMap<>();

    public static int getMaxRetriesAllowed() {
        final int count = ExecutionManager.getConfiguration().getRetryTestingCount();
        return RetryAnalyzer.setMaxRetries(count > 1 ? count - 1 : count);
    }

    public static Integer getMaxRetries() {
        return RetryAnalyzer.maxRetries;
    }

    public static Integer setMaxRetries(final Integer maxRetries) {
        RetryAnalyzer.maxRetries = maxRetries;
        return maxRetries;
    }

    @Override
    public boolean retry(final ITestResult result) {
        // only re-try failures
        if (result.getStatus() == ITestResult.FAILURE) {
            String testName = TestListenerUtil.getName(result);
            int count = this.getRetryCount(result);
            int maxRetriesAllowed = RetryAnalyzer.getMaxRetriesAllowed();
            if (count < maxRetriesAllowed) {
                this.retryCount.put(TestListenerUtil.getId(result), count + 1);
                LogTrackerEvent.trace(RetryAnalyzer.class.getName(),
                        "Retrying test (attempt " + (count + 1) + "/" + (maxRetriesAllowed + 1) + "): " + testName);
                return true;
            } else {
                LogTrackerEvent.trace(RetryAnalyzer.class.getName(),
                        "Failing test after " + count + " retries: " + testName);
            }
        }

        return false;
    }

    public boolean canRetry(final ITestResult result) {
        return result.getStatus() == ITestResult.FAILURE && this.getRetryCount(result) < RetryAnalyzer.getMaxRetriesAllowed();
    }

    private int getRetryCount(final ITestResult result) {
        int testId = TestListenerUtil.getId(result);
        return this.retryCount.containsKey(testId) ? this.retryCount.get(testId) : 0;
    }
}
