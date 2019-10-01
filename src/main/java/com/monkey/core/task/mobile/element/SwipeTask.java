package com.monkey.core.task.mobile.element;

import com.monkey.api.page.MonkeyMobileElement;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.AbstractTask;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.ActionOptions;
import io.appium.java_client.touch.offset.PointOption;

public class SwipeTask extends AbstractTask {

//    int offsetFromStartBorder;
//    int offsetFromEndBorder;
//    int duration;

    private int offSetStartX;
    private int offSetStartY;
    private int offSetEndX;
    private int offSetEndY;

    private AppiumDriver driver;

    /**
     * Fire the event of clicking element with the selenium implementation. The
     * click of monkey is more improved and works for more cases than the native
     * click of Selenium
     */
    @Override
    public void execute() {
        driver = (AppiumDriver) ExecutionManager.getMonkeyDriver();
//        ((MobileElement) this.getElement().getWebElement()).swipe(this.direction, this.offsetFromStartBorder, this.offsetFromEndBorder, this.duration);
        swipe(getOffSetStartX(), getOffSetStartY(), getOffSetEndX(), getOffSetEndY());
    }

    @Override
    public String getDescription() {
        return "Swipe on element [ " + this.getElement() + "]";
    }

    @Override
    public String getName() {
        return "Swipe";
    }

//    public void setDirection(final SwipeElementDirection direction) {
//        this.direction = direction;
//    }
//
//    public void setOffsetFromStartBorder(final int offsetFromStartBorder) {
//        this.offsetFromStartBorder = offsetFromStartBorder;
//    }
//
//    public void setOffsetFromEndBorder(final int offsetFromEndBorder) {
//        this.offsetFromEndBorder = offsetFromEndBorder;
//    }
//
//    public void setDuration(final int duration) {
//        this.duration = duration * 1000;
//    }

    /**
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     */
    private void swipe(int startX, int startY, int endX, int endY) {
        new TouchAction(driver)
                .press(PointOption.point(startX, startY))
                .moveTo(PointOption.point(endX, endY))
                .release()
                .perform();
    }

    public int getOffSetStartX() {
        return offSetStartX;
    }

    public void setOffSetStartX(int offSetStartX) {
        this.offSetStartX = offSetStartX;
    }

    public int getOffSetStartY() {
        return offSetStartY;
    }

    public void setOffSetStartY(int offSetStartY) {
        this.offSetStartY = offSetStartY;
    }

    public int getOffSetEndX() {
        return offSetEndX;
    }

    public void setOffSetEndX(int offSetEndX) {
        this.offSetEndX = offSetEndX;
    }

    public int getOffSetEndY() {
        return offSetEndY;
    }

    public void setOffSetEndY(int offSetEndY) {
        this.offSetEndY = offSetEndY;
    }

}
