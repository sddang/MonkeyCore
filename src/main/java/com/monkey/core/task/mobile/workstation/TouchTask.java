package com.monkey.core.task.mobile.workstation;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.monkey.api.Wait;
import com.monkey.api.mobile.Driver;
import com.monkey.api.mobile.workstation.TouchAction;
import com.monkey.core.exception.ExceptionCode;
import com.monkey.core.exception.MonkeyException;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.AbstractTask;

import io.appium.java_client.AppiumDriver;

public class TouchTask extends AbstractTask {

    private String name;
    private boolean exitOnError = true;
    private int maxIteration = 15;
    private String text;
    private double screenWidthStartPercent;
    private double screenWidthEndPercent;
    private double screenHeightPercent;
    private double screenHeightStartPercent;
    private double screenHeightEndPercent;
    private int duration = 2000;
    private boolean elementFound;
    private int x;
    private int y;
    private int finger;

    public void setX(final int x) {
        this.x = x;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public void setFinger(final int finger) {
        this.finger = finger;
    }

    @Override
    public boolean allowEnabledElement() {
        return true;
    }

    @Override
    public void execute() {
        WebElement element;
        List<WebElement> elementsList;

        switch (this.name) {

            case TouchAction.SWIPE:

                if (this.screenHeightEndPercent != 0L) {
                    this.swipe(this.screenWidthStartPercent, this.screenHeightStartPercent, this.screenWidthEndPercent, this.screenHeightEndPercent,
                            this.duration);
                } else if (this.screenHeightPercent == 0L && this.screenHeightEndPercent == 0L) {
                    this.swipe(this.screenWidthStartPercent, this.screenWidthEndPercent, this.duration);

                } else if (this.screenHeightPercent != 0L) {
                    this.swipe(this.screenWidthStartPercent, this.screenWidthEndPercent, this.screenHeightPercent, this.duration);
                }
                break;

            case TouchAction.SWIPE_TO_TEXT:
                for (int i = 0; i < this.maxIteration; i++) {
                    element = this.getElement().getInitWebElement();
                    if (element != null && element.isDisplayed()) {

                        if (StringUtils.containsIgnoreCase(element.getText(), this.text)) {
                            this.elementFound = true;
                            break;
                        }
                    }
                    this.swipe(this.screenWidthStartPercent, this.screenWidthEndPercent, this.duration);
                }

                if (!this.elementFound && this.exitOnError) {
                    throw new MonkeyException(ExceptionCode.MAX_SCROOL_REACH, Integer.toString(this.maxIteration));
                }
                break;

            case TouchAction.SCROLL_TO_ELEMENT:
                for (int i = 0; i < this.maxIteration; i++) {
                    if (Wait.waitUntilVisibleElement(this.getElement(), 1L, false)) {
                        this.elementFound = true;
                        break;
                    }
                    this.scroll(this.screenHeightStartPercent, this.screenHeightEndPercent, this.duration);
                }

                if (!this.elementFound && this.exitOnError) {
                    throw new MonkeyException(ExceptionCode.MAX_SCROOL_REACH, Integer.toString(this.maxIteration));
                }
                break;

            case TouchAction.SCROLL_TO_TEXT:
                for (int i = 0; i < this.maxIteration; i++) {
                    elementsList = this.getElement().getInitListElement();
                    if (!elementsList.isEmpty()) {
                        for (int j = 0; j < elementsList.size(); j++) {
                            final WebElement elementTmp = elementsList.get(j);

                            if (elementTmp != null && elementTmp.isDisplayed()) {

                                if (StringUtils.containsIgnoreCase(elementTmp.getText(), this.text)) {
                                    this.elementFound = true;
                                    break;
                                }
                            }
                        }
                        if (this.elementFound)
                            break;
                    }
                    this.scroll(this.screenHeightStartPercent, this.screenHeightEndPercent, this.duration);
                }

                if (!this.elementFound && this.exitOnError) {
                    throw new MonkeyException(ExceptionCode.MAX_SCROOL_REACH, Integer.toString(this.maxIteration));
                }
                break;

            case TouchAction.SCROLL_TO_TEXT_IN_FIRST_POSITION:
                for (int i = 0; i < this.maxIteration; i++) {
                    elementsList = this.getElement().getInitListElement();
                    if (!elementsList.isEmpty()) {
                        final WebElement elementTmp = elementsList.get(0);
                        if (elementTmp != null && elementTmp.isDisplayed()) {
                            if (StringUtils.containsIgnoreCase(elementTmp.getText(), this.text)) {
                                this.elementFound = true;
                                break;
                            }
                        }
                    }
                    this.scroll(this.screenHeightStartPercent, this.screenHeightEndPercent, this.duration);
                }

                if (!this.elementFound && this.exitOnError) {
                    throw new MonkeyException(ExceptionCode.MAX_SCROOL_REACH, Integer.toString(this.maxIteration));
                }
                break;

            case TouchAction.SCROLL:
                this.scroll(this.screenHeightStartPercent, this.screenHeightEndPercent, this.duration);
                break;

            case TouchAction.TAP:
                this.tap(this.finger, this.x, this.y, this.duration);
                break;

        }

    }

    private void tap(final int f, final int condinateX, final int condinatorY, final int duration) {
        final AppiumDriver<WebElement> driver = ((AppiumDriver<WebElement>) ExecutionManager.getMonkeyDriver());
        driver.tap(this.finger, condinateX, condinatorY, duration);
    }

    /*
     * @SuppressWarnings("unchecked") private void iOSscroll(String text) {
     * ((IOSDriver<WebElement>)
     * (ExecutionManager.getmonkeyDriver())).scrollTo(text); }
     */


    private void scroll(final double screenHeightStartPercent, final double screenHeightEndPercent, final int duration) {
        final String currentContext = Driver.getContext();
        Driver.switchContextToNative();
        final Dimension dimensions = ExecutionManager.getMonkeyDriver().manage().window()
                .getSize();

        if (!(0 < screenHeightStartPercent && screenHeightStartPercent < 1)) {
            throw new MonkeyException(ExceptionCode.PERCENT_EXCEPTION, Double.toString(screenHeightStartPercent));
        }
        if (!(0 < screenHeightEndPercent && screenHeightEndPercent < 1)) {
            throw new MonkeyException(ExceptionCode.PERCENT_EXCEPTION, Double.toString(screenHeightEndPercent));
        }

        final Double screenHeightStart = dimensions.getHeight() * screenHeightStartPercent;
        final int scrollStart = screenHeightStart.intValue();
        final Double screenHeightEnd = dimensions.getHeight() * screenHeightEndPercent;
        final int scrollEnd = screenHeightEnd.intValue();

        ((AppiumDriver<WebElement>) ExecutionManager.getMonkeyDriver()).swipe(0, scrollStart, 0, scrollEnd, duration);
        Driver.switchContext(currentContext);
    }

    private void swipe(final double screenWidthStartPercent, final double screenWidthEndPercent, final int duration) {
        this.swipe(screenWidthStartPercent, screenWidthEndPercent, 0.5, duration);
    }

    @SuppressWarnings("unchecked")
    private void swipe(final double screenWidthStartPercent, final double screenWidthEndPercent, final double screenHeightPercent,
                       final int duration) {
        final String currentContext = Driver.getContext();
        Driver.switchContextToNative();
        final Dimension dimensions = ExecutionManager.getMonkeyDriver().manage().window()
                .getSize();

        if (!(0 < screenWidthStartPercent && screenWidthStartPercent < 1)) {
            throw new MonkeyException(ExceptionCode.PERCENT_EXCEPTION, Double.toString(screenWidthStartPercent));
        }
        if (!(0 < screenWidthEndPercent && screenWidthEndPercent < 1)) {
            throw new MonkeyException(ExceptionCode.PERCENT_EXCEPTION, Double.toString(screenWidthEndPercent));
        }

        final Double screenWidthStart = dimensions.getWidth() * screenWidthStartPercent;
        final int swipeStart = screenWidthStart.intValue();
        final Double screenWidthEnd = dimensions.getWidth() * screenWidthEndPercent;
        final int swipeEnd = screenWidthEnd.intValue();

        final Double screenHeightMiddle = dimensions.getHeight() * screenHeightPercent;
        final int height = screenHeightMiddle.intValue();

        ((AppiumDriver<WebElement>) ExecutionManager.getMonkeyDriver()).swipe(swipeStart, height, swipeEnd, height,
                duration);
        Driver.switchContext(currentContext);
    }

    @SuppressWarnings("unchecked")
    private void swipe(final double screenWidthStartPercent, final double screenHeightStartPercent, final double screenWidthEndPercent,
                       final double screenHeightEndPercent, final int duration) {
        final String currentContext = Driver.getContext();
        Driver.switchContextToNative();
        final Dimension dimensions = ExecutionManager.getMonkeyDriver().manage().window()
                .getSize();

        if (!(0 < screenWidthStartPercent && screenWidthStartPercent < 1)) {
            throw new MonkeyException(ExceptionCode.PERCENT_EXCEPTION, Double.toString(screenWidthStartPercent));
        }
        if (!(0 < screenWidthEndPercent && screenWidthEndPercent < 1)) {
            throw new MonkeyException(ExceptionCode.PERCENT_EXCEPTION, Double.toString(screenWidthEndPercent));
        }

        if (!(0 < screenHeightStartPercent && screenHeightStartPercent < 1)) {
            throw new MonkeyException(ExceptionCode.PERCENT_EXCEPTION, Double.toString(screenHeightStartPercent));
        }
        if (!(0 < screenHeightEndPercent && screenHeightEndPercent < 1)) {
            throw new MonkeyException(ExceptionCode.PERCENT_EXCEPTION, Double.toString(screenHeightEndPercent));
        }

        final Double screenWidthStart = dimensions.getWidth() * screenWidthStartPercent;
        final int swipeWidthStart = screenWidthStart.intValue();
        final Double screenWidthEnd = dimensions.getWidth() * screenWidthEndPercent;
        final int swipeWidthEnd = screenWidthEnd.intValue();

        final Double screenHeightStart = dimensions.getHeight() * screenHeightStartPercent;
        final int swipeHeightStart = screenHeightStart.intValue();
        final Double screenHeightEnd = dimensions.getHeight() * screenHeightEndPercent;
        final int swipeHeightEnd = screenHeightEnd.intValue();

        ((AppiumDriver<WebElement>) ExecutionManager.getMonkeyDriver()).swipe(swipeWidthStart, swipeHeightStart,
                swipeWidthEnd, swipeHeightEnd, duration);
        Driver.switchContext(currentContext);
    }

    @Override
    public String getDescription() {
        switch (this.name) {

            case TouchAction.SWIPE:
                return "swipe";
            case TouchAction.SWIPE_TO_TEXT:
                return "swipe to text << " + this.text + " >>";
            case TouchAction.SCROLL_TO_ELEMENT:
                return "scroll to element [ " + this.getElement() + "]";
            case TouchAction.SCROLL_TO_TEXT:
                return "scroll to text << " + this.text + " >>";
            case TouchAction.SCROLL_TO_TEXT_IN_FIRST_POSITION:
                return "scroll to text << " + this.text + " >> in first position";
            case TouchAction.SCROLL:
                return "perform a scroll action";
            case TouchAction.TAP:
                return "tap on the specific cordidate x: " + this.x + " and y: " + this.y + "";
            default:
                return "No description for this task : " + this.name;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setExitOnError(final boolean exitOnError) {
        this.exitOnError = exitOnError;
    }

    public void setMaxIteration(final int maxIteration) {
        this.maxIteration = maxIteration;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public void setScreenWidthStartPercent(final double screenWidthStartPercent) {
        this.screenWidthStartPercent = screenWidthStartPercent;
    }

    public void setScreenWidthEndPercent(final double screenWidthEndPercent) {
        this.screenWidthEndPercent = screenWidthEndPercent;
    }

    public void setScreenHeightPercent(final double screenHeightPercent) {
        this.screenHeightPercent = screenHeightPercent;
    }

    public void setScreenHeightStartPercent(final double screenHeightStartPercent) {
        this.screenHeightStartPercent = screenHeightStartPercent;
    }

    public void setScreenHeightEndPercent(final double screenHeightEndPercent) {
        this.screenHeightEndPercent = screenHeightEndPercent;
    }

    public void setDuration(final int duration) {
        // This method accept millisecond and second
        // 100<=duration<1000 --> millisecond
        // 100>duration>=1000 --> second
        this.duration = duration >= 100 && duration < 1000 ? duration : duration * 1000;
    }

    public void setElementFound(final boolean elementFound) {
        this.elementFound = elementFound;
    }

    public boolean isElementFound() {
        return this.elementFound;
    }

}
