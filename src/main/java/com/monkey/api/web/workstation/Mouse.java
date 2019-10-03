package com.monkey.api.web.workstation;

import com.monkey.api.page.MonkeyWebElement;
import com.monkey.core.task.web.workstation.MouseTask;

/**
 * This class manage the actions of the mouse
 */
public class Mouse {


    public static final String GOTO_POSITION = "gotoPosition";
    public static final String DRAG_AND_DROP = "dragAndDrop";
    public static final String MOUSE_HOVER = "mouseHover";
    public static final String MOUSE_DOWN = "mouseDown";
    public static final String MOUSE_CLICK_AND_HOLD = "clickAnsHold";
    public static final String MOUSE_MOVE_TO_ELEMENT = "moveToElement";
    public static final String MOUSE_RELEASE = "release";
    public static final String MOUSE_CONTEXT_CLICK = "contextClick";
    public static final String MOUSE_CLICK = "click";


    /**
     * ClickAndHold on the given element
     *
     * @param element
     */
    public static void clickAndHold(final MonkeyWebElement element) {

        final MouseTask task = new MouseTask();
        task.setName(Mouse.MOUSE_CLICK_AND_HOLD);
        task.setElement(element);
        task.runTask();
    }

    /**
     * Click on the given element
     *
     * @param element
     */
    public static void click(final MonkeyWebElement element) {

        final MouseTask task = new MouseTask();
        task.setName(Mouse.MOUSE_CLICK);
        task.setElement(element);
        task.runTask();
    }

    /**
     * contextClick on the given element
     *
     * @param element
     */
    public static void contextClick(final MonkeyWebElement element) {

        final MouseTask task = new MouseTask();
        task.setName(Mouse.MOUSE_CONTEXT_CLICK);
        task.setElement(element);
        task.runTask();
    }

    /**
     * Release on the given element
     *
     * @param element
     */
    public static void release(final MonkeyWebElement element) {

        final MouseTask task = new MouseTask();
        task.setName(Mouse.MOUSE_RELEASE);
        task.setElement(element);
        task.runTask();
    }


    /**
     * Move the given element
     *
     * @param element
     */
    public static void moveToElement(final MonkeyWebElement element) {

        final MouseTask task = new MouseTask();
        task.setName(Mouse.MOUSE_MOVE_TO_ELEMENT);
        task.setElement(element);
        task.runTask();
    }


    /**
     * Simulate the event mouseDown of the element
     *
     * @param element
     */
    public static void mouseDown(final MonkeyWebElement element) {

        final MouseTask task = new MouseTask();
        task.setName(Mouse.MOUSE_DOWN);
        task.setElement(element);
        task.runTask();
    }

    /**
     * Simulate the event mouseHover of the element
     *
     * @param element
     */
    public static void mouseHover(final MonkeyWebElement element) {
        final MouseTask task = new MouseTask();
        task.setName(Mouse.MOUSE_HOVER);
        task.setElement(element);
        task.runTask();
    }

    /**
     * Simulate the DragAnDrop action between source field element and target one
     *
     * @param elementSource
     * @param elementTarget
     */
    public static void dragAndDrop(final MonkeyWebElement elementSource, final MonkeyWebElement elementTarget) {
        final MouseTask task = new MouseTask();
        task.setName(Mouse.DRAG_AND_DROP);
        task.setElement(elementSource);
        task.setExtraElement(elementTarget);
        task.runTask();
    }

    /**
     * Move the mouse to the given position
     *
     * @param x
     * @param y
     */
    public static void gotoPosition(final MonkeyWebElement element, final int x, final int y) {
        final MouseTask task = new MouseTask();
        task.setName(Mouse.GOTO_POSITION);
        task.setElement(element);
        task.setX(x);
        task.setY(y);
        task.runTask();
    }
}
