
package com.monkey.core.task.web.element;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.monkey.api.Wait;
import com.monkey.api.enumeration.HTMLType;
import com.monkey.api.web.workstation.KeyBoard;
import com.monkey.api.web.workstation.Navigator;
import com.monkey.core.enumeration.SupportedBrowsers;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.AbstractTask;

public class HoverTask extends AbstractTask {

    /**
     * Fire the event of hovering element with the selenium implementation
     * The Hover of monkeyis more improved
     */
    @Override
    public void execute() {
        final WebElement element = this.getElement().getWebElement();
        int i = 0;
        while (i < 5) {
            try {
                if (Navigator
                        .isCurrentBrowser(SupportedBrowsers.ie)
                        || Navigator
                        .isCurrentBrowser(SupportedBrowsers.firefox)) {
                    final String tagName = element.getTagName();
                    if (!HTMLType.a.name().equals(tagName)
                            && !HTMLType.li.name().equals(tagName)
                            && !HTMLType.button.name().equals(tagName)
                            && !HTMLType.img.name().equals(tagName)) {
                        final Actions actionbuilder = new Actions(
                                ExecutionManager.getMonkeyDriver());
                        actionbuilder.moveToElement(element);
                        actionbuilder.perform();
                    } else {
                        final JavascriptExecutor js = ExecutionManager
                                .getMonkeyDriver();
                        final String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
                        js.executeScript(mouseOverScript, element);
                    }
                } else {

                    final Actions actionbuilder = new Actions(
                            ExecutionManager.getMonkeyDriver());
                    actionbuilder.moveToElement(element);
                    actionbuilder.perform();
                }
                Wait.implicitWait(5);
                break;
            } catch (final Exception e) {
                i = i + 1;
                if (i == 1 || i == 2) {
                    KeyBoard.pageDown();
                } else {
                    KeyBoard.pageUp();
                }
            }
        }
    }

    @Override
    public String getDescription() {
        return "Hover the element << " + this.getElement() + " >>";
    }

    @Override
    public String getName() {
        return "HoverElement";
    }

}
