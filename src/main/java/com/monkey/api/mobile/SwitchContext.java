package com.monkey.api.mobile;

import java.util.Set;

import com.monkey.api.MonkeyExecutionContext;
import com.monkey.api.MonkeyLogger;
import com.monkey.api.Wait;
import com.monkey.api.enumeration.LogLevel;
import com.monkey.api.page.MonkeyWebElement;

public class SwitchContext {

	public static void toNative() {
		Driver.switchContextToNative();
	}

	public static void toWebview() {
		// Wait.implicitWait(20);
		Driver.waitUntilWebviewLoaded();
		if (MonkeyExecutionContext.isAndroid()) {
			Driver.switchContextToLastWebview();
		} else {
			Driver.switchToNonBlankWebContext();
		}
		if (MonkeyExecutionContext.isAndroid()) {
			Driver.switchWebViewWindowToLast();
		}

	}

	public static void switchOtherApp() {
		// App2 capabilities
		final String browserAppPackageName = "com.sec.android.app.sbrowser";
		final String browserAppActivityName = "com.sec.android.app.sbrowser.SBrowserMainActivity";
		Driver.startActivity(browserAppPackageName, browserAppActivityName);
		Wait.implicitWait(3);
	}

	public static void switchToCorrectWebView(final MonkeyWebElement element) {
		Boolean flag = element.isPresent();
		final Set<String> contexts = Driver.getAppiumDriver().getContextHandles();
		for (int i = 1; i < contexts.size(); i++) {
			if (!flag) {
				Driver.switchContext(i);
				Wait.implicitWait(1);
				flag = element.isPresent();
			} else {
				return;
			}
			if (!flag) {
				MonkeyLogger.log(SwitchContext.class.getName(),
						"There is no context contain element '" + element.getVariableName() + "'", LogLevel.ERROR);
			}
		}
	}

}