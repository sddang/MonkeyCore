package com.monkey.api.page;

import com.monkey.api.enumeration.Selector;
import com.monkey.core.task.mobile.element.*;

/**
 * This class is the structure that will encapsulate the HTML components of the
 * pages. It expose the DSL of the html elements actions.
 */
public class MonkeyMobileElement extends MonkeyWebElement {

    public MonkeyMobileElement() {

    }

    /**
     * Constructor with all fields of the element
     *
     * @param identifier
     * @param selector
     */
    public MonkeyMobileElement(final String identifier, final Selector selector) {
        super(identifier, selector);
    }

    @Override
    public void click() {
        new ClickTask().setElement(this).runTask();
    }

    @Override
    public void clear() {
        new ClearTask().setElement(this).runTask();
    }

    @Override
    public void clear(final boolean checkCleaned) {
        final ClearTask ct = new ClearTask();
        ct.setElement(this);
        ct.getElement().click();
        while (getWebElement().getText().length() > 0) {
            ct.runTask();
        }
    }

//    @Override
//    public void type() {
//        new TypeTask().setElement(this).runTask();
//    }

    @Override
    public void type(String value) {
        final TypeTask type = new TypeTask();
        type.setElement(this);
        type.setInputValue(value);
//        type.setIosSendKey(iosSendKey);
        type.runTask();
    }

    @Override
    public void check(final boolean enabled) {
        new CheckTask(enabled).setElement(this).runTask();
    }

    public void swipe(final int startX, final int startY, final int endX, final int endY) {
        final SwipeTask swipeTask = new SwipeTask();
        swipeTask.setElement(this);
        swipeTask.setOffSetStartX(startX);
        swipeTask.setOffSetStartY(startY);
        swipeTask.setOffSetEndX(endX);
        swipeTask.setOffSetEndY(endY);
        swipeTask.runTask();
    }

//    public void swipe(final SwipeElementDirection direction, final int offsetFromStartBorder, final int offsetFromEndBorder,
//                      final int duration) {
//        final SwipeTask swipeTask = new SwipeTask();
//        swipeTask.setElement(this);
//        swipeTask.setDirection(direction);
//        swipeTask.setOffsetFromStartBorder(offsetFromStartBorder);
//        swipeTask.setOffsetFromEndBorder(offsetFromEndBorder);
//        swipeTask.setDuration(duration);
//         swipeTask.setInitElement(false);
//        swipeTask.runTask();
//    }

    public MonkeyMobileElement setInputValue(final String inputValue) {
        this.inputValue = inputValue;
        return this;
    }

}