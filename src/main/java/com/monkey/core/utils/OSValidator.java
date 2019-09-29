package com.monkey.core.utils;

public class OSValidator {
    private static final String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {
        return (OSValidator.OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OSValidator.OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (OSValidator.OS.indexOf("nix") >= 0 || OSValidator.OS.indexOf("nux") >= 0 || OSValidator.OS.indexOf("aix") > 0);
    }

    public static boolean isSolaris() {
        return (OSValidator.OS.indexOf("sunos") >= 0);
    }

}
