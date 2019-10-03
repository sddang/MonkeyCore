package com.monkey.services.log;

import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;
import org.testng.ITestNGMethod;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class LogTrackerEvent {

    /**
     * Trace the log with the info level
     *
     * @param logger
     * @param info
     */
    private static void info(final String className, final String info) {
        LoggerFactory.getLogger(className).info(info);
    }

    public static void traceStartMonkeyMessage(final String className) {
        try {
            final InputStream is = LogTrackerEvent.class.getClassLoader().getResourceAsStream("MonkeyStartMessage.txt");
            LogTrackerEvent.info(className, IOUtils.toString(is, StandardCharsets.UTF_8));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static void traceStartTest(final String className, final ITestNGMethod method) {
        final String message = "\n \nSTART TEST :================= " + method.getTestClass().getName()
                + " // << " + method.getMethodName() + " >> ==========\n\n";
        LogTrackerEvent.info(className, message);
    }

    public static void traceEndTest(final String className, final ITestNGMethod method) {
        final String message = "\n \nEND TEST :================= " + method.getTestClass().getName()
                + " // << " + method.getMethodName() + " >> ==========\n\n";
        LogTrackerEvent.info(className, message);
    }

    /**
     * Trace the current task
     *
     * @param className
     * @param description
     */
    public static void traceTask(final String className, final String description) {
        final String message = "\n      [Task ------------------- || " + description + " ]";
        LogTrackerEvent.info(className, message);
    }

    /**
     * Trace the current assersion
     *
     * @param className
     * @param description
     */
    public static void traceAssert(final String className, final String description) {
        final String message = "\n      [Assert ----------------- || " + description + " ]";
        LogTrackerEvent.info(className, message);
    }

    /**
     * Log information for the given class
     *
     * @param className
     * @param description
     */
    public static void trace(final String className, final String description) {
        LogTrackerEvent.info(className, description);
    }

}
