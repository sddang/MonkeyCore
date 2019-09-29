package com.monkey.core.enumeration;

import java.util.Random;

public enum BrowserMode {
    desktop, smartphone, tablette;

    private static BrowserMode defaultBrowserMode = BrowserMode.desktop;

    public static BrowserMode getRandomBrowserMode() {
        final BrowserMode[] browserModes = BrowserMode.values();
        final Random generator = new Random();
        return browserModes[generator.nextInt(browserModes.length)];
    }

    public static BrowserMode getCalculatedBrowserMode() {
        return BrowserMode.defaultBrowserMode;
    }

    public static void setDefaultBrowserMode(final BrowserMode defaultBrowserMode) {
        BrowserMode.defaultBrowserMode = defaultBrowserMode;
    }
}
