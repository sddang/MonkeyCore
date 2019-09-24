package com.monkey.core.session;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.internal.TestResult;

import com.monkey.services.log.LogTrackerEvent;

public class TestListenerUtil {

	public static int cleanUpDuplicateFailures(final ITestContext testContext) {
		String testContextName = testContext.getName();
		int removedFailures = 0;
		LogTrackerEvent.trace(TestListenerUtil.class.getName(),
				"Cleaning up failures in test context '" + testContextName + "' ...");
		Set<ITestResult> failedTests = testContext.getFailedTests().getAllResults();
		if (failedTests.isEmpty()) {
			LogTrackerEvent.trace(TestListenerUtil.class.getName(),"There are no failures in test context '" + testContextName + "'\n");
		} else {
			// collect all id's from passed test
			Set<Integer> passedTestIds = new HashSet<>();
			Set<ITestResult> passedTests = testContext.getPassedTests().getAllResults();
			LogTrackerEvent.trace(TestListenerUtil.class.getName(),"Analyzing " + passedTests.size() + " passed test(s)");
			for (final ITestResult result : passedTests) {
				int testId = getId(result);
				passedTestIds.add(testId);
				LogTrackerEvent.trace(TestListenerUtil.class.getName(),"  Passed test " + getName(result) + ": #" + testId + " @ "
						+ result.getStartMillis());
			}
			// check which failed test results should be removed
			List<Integer> resultsToBeRemoved = new ArrayList<>();
			Set<Integer> failedTestIds = new HashSet<>();

			LogTrackerEvent.trace(TestListenerUtil.class.getName(),"Analyzing " + failedTests.size() + " failed test(s)");
			for (final ITestResult result : failedTests) {
				int testId = getId(result);
				String name = getName(result);

				// if we saw this test pass or fail before we mark the result
				// for deletion
				if (failedTestIds.contains(testId) || passedTestIds.contains(testId)) {
					LogTrackerEvent.trace(TestListenerUtil.class.getName(),
							"  Adding test " + name + " to be removed: #" + testId + " @ " + result.getStartMillis());
					resultsToBeRemoved.add(testId);
				} else {
					LogTrackerEvent.trace(TestListenerUtil.class.getName(),
							"  Remembering failed test " + name + ": #" + testId + " @ " + result.getStartMillis());
					failedTestIds.add(testId);
				}
			}

			// finally delete all duplicate failures (if any)
			int duplicateFailures = resultsToBeRemoved.size();
			if (duplicateFailures > 0) {
				LogTrackerEvent.trace(TestListenerUtil.class.getName(),"Cleaning up failed tests (expecting to remove " + resultsToBeRemoved.size()
						+ " result(s)) ...");
				for (final ITestResult result : testContext.getFailedTests().getAllResults()) {
					int testId = getId(result);
					String info = getName(result) + ": #" + testId + " @ "
							+ result.getStartMillis();
					if (resultsToBeRemoved.contains(testId)) {
						LogTrackerEvent.trace(TestListenerUtil.class.getName(),"  Removing failed test result " + info);
						testContext.getFailedTests().removeResult(result.getMethod());
						resultsToBeRemoved.remove((Integer) testId);
						removedFailures++;
					} else {
						LogTrackerEvent.trace(TestListenerUtil.class.getName(),"  Not removing failed test result " + info);
					}
				}
			}

			if (removedFailures == duplicateFailures) {
				LogTrackerEvent.trace(TestListenerUtil.class.getName(),
						"Removed " + removedFailures + " failed test result(s) in '" + testContextName + "'\n");
			} else {
				LogTrackerEvent.trace(TestListenerUtil.class.getName(),"Removed " + removedFailures + " failed test result(s) in '" + testContextName
						+ "' (expected to remove " + duplicateFailures + ")\n");
			}
		}

		return removedFailures;
	}

	public static String getName(final ITestResult result) {
		List<String> parameters = new ArrayList<>();
		if (result.getParameters() != null) {
			for (final Object parameter : result.getParameters()) {
				if (parameter instanceof TestResult && ((TestResult) parameter).getStatus() < 0) {
					parameters.add(parameter.getClass().getName() + "@" + parameter.hashCode());
				} else {
					parameters.add(parameter == null ? "null" : parameter.toString());
				}
			}
		}

		return result.getTestClass().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName() + "("
				+ StringUtils.join(parameters, ",") + ")";
	}

	public static int getId(final ITestResult result) {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(result.getTestClass().getRealClass());
		builder.append(result.getMethod().getMethodName());
		builder.append(result.getParameters());
		return builder.toHashCode();
	}
}
