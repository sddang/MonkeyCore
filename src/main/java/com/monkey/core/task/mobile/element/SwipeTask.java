package com.monkey.core.task.mobile.element;

import com.monkey.core.task.AbstractTask;

import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;


public class SwipeTask extends AbstractTask {

    SwipeElementDirection direction;
    int offsetFromStartBorder;
    int offsetFromEndBorder;
    int duration;

    /**
     * Fire the event of clicking element with the selenium implementation. The
     * click of monkey is more improved and works for more cases than the native
     * click of Selenium
     */
    @Override
    public void execute() {
        ((MobileElement) this.getElement().getWebElement()).swipe(this.direction, this.offsetFromStartBorder, this.offsetFromEndBorder, this.duration);
    }

    @Override
    public String getDescription() {
        return "Swipe on element [ " + this.getElement() + "]";
    }

    @Override
    public String getName() {
        return "Swipe";
    }

    public void setDirection(final SwipeElementDirection direction) {
        this.direction = direction;
    }

    public void setOffsetFromStartBorder(final int offsetFromStartBorder) {
        this.offsetFromStartBorder = offsetFromStartBorder;
    }

    public void setOffsetFromEndBorder(final int offsetFromEndBorder) {
        this.offsetFromEndBorder = offsetFromEndBorder;
    }

    public void setDuration(final int duration) {
        this.duration = duration * 1000;
    }


}
