package com.monkey.core.utils;

import com.monkey.core.exception.ExceptionCode;
import com.monkey.core.exception.MonkeyException;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Utils {

    public static void createFolderIfNotExist(final String path) {
        try {
            final File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdir();
            }
        } catch (final Exception e) {
            throw new MonkeyException(ExceptionCode.NOT_ACCESSIBLE_FILE_ERROR, e.getMessage());
        }
    }

    public static void cleanDirectory(final String path) {
        try {
            final File directory = new File(path);
            if (directory.exists()) {
                directory.delete();
            }
            directory.mkdir();
        } catch (final Exception e) {
            throw new MonkeyException(ExceptionCode.NOT_ACCESSIBLE_FILE_ERROR, e.getMessage());
        }
    }

    public static File createFile(final String filePath) {
        final File file = new File(filePath);
        try {
            file.createNewFile();
            return file;
        } catch (final IOException e) {
            throw new MonkeyException(ExceptionCode.ERROR_WHEN_CREATING_FILE, filePath, e.getMessage());
        }
    }

    public static String getStackTrace(final Throwable t) {
        if (t == null) {
            return null;
        }

        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);

        return sw.toString();
    }

    public static void isFileExisted(final String filePathString) {
        final File f = new File(filePathString);
        if (!f.exists()) {
            Assert.fail("no file found");
        }
    }

}
