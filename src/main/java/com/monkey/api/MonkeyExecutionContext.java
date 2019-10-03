package com.monkey.api;

import com.monkey.core.enumeration.BrowserMode;
import com.monkey.core.enumeration.SupportedVarEnv;
import com.monkey.core.session.ExecutionManager;
import com.monkey.impl.drivers.DriverType;

import java.util.Map;

public class MonkeyExecutionContext {

    public static Map<String, String> getExecutionContext() {
        return ExecutionManager.getExecutionContext();
    }

    public static String getLanguage() {
        return ExecutionManager.getExecutionContext().get(SupportedVarEnv.language.name()).replace("-", "_");
    }

    public static String getBrowserMode() {
        return ExecutionManager.getExecutionContext().get(SupportedVarEnv.browserMode.name());
    }

    public static boolean isTabletMode() {
        return MonkeyExecutionContext.getBrowserMode().equals(BrowserMode.tablette.name());
    }

    public static boolean isDesktopMode() {
        return MonkeyExecutionContext.getBrowserMode().equals(BrowserMode.desktop.name());
    }

    public static boolean isSmartphoneMode() {
        return MonkeyExecutionContext.getBrowserMode().equals(BrowserMode.smartphone.name());
    }

    public static boolean isIOS() {
        return ExecutionManager.getConfiguration().getDriverType().equals(DriverType.iosDriver);
    }

    public static boolean isAndroid() {
        return ExecutionManager.getConfiguration().getDriverType().equals(DriverType.androidDriver);
    }

}
