package com.monkey.services;

import com.monkey.api.JavaScriptExecutor;
import com.monkey.api.Wait;
import com.monkey.api.page.MonkeyWebElement;
import com.monkey.core.page.LocalisationHelper;
import com.monkey.core.session.ExecutionManager;
import com.monkey.impl.drivers.DriverType;

public class HighlightElementEvent {

    public static void highlightElement(final MonkeyWebElement element) {
        if (ExecutionManager.getConfiguration().getDriverType().equals(DriverType.webDriver)) {
            if (ExecutionManager.getConfiguration().isHighlightActiveElement() && element != null) {
                final String styleValue = LocalisationHelper.getElementAttribut(element, "style");
                for (int i = 0; i < 2; i++) {
                    HighlightElementEvent.decorateElement(element);
                    Wait.implicitWait(10);
                    HighlightElementEvent.resetElementStyle(element, styleValue);
                    HighlightElementEvent.decorateElement(element);
                    Wait.implicitWait(20);
                    HighlightElementEvent.resetElementStyle(element, styleValue);
                }
            }
        }
    }

    private static void resetElementStyle(final MonkeyWebElement element, final String styleValue) {
        JavaScriptExecutor.excuteJS("arguments[0].setAttribute('style', arguments[1]);", element,
                styleValue != null ? styleValue : "");
    }

    private static void decorateElement(final MonkeyWebElement element) {
        JavaScriptExecutor.excuteJS("arguments[0].setAttribute('style', arguments[1]);", element,
                "color: BlanchedAlmond; border: 3px solid red ;");
    }

}
