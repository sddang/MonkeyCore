package com.monkey.api.mobile.workstation;

import com.monkey.api.page.MonkeyMobileElement;
import com.monkey.api.page.MonkeyWebElement;
import com.monkey.core.task.mobile.workstation.TouchTask;

public class TouchAction {

	private static final double SWIPE_WIDTH_RIGHT_PERCENT_DEFAULT = 0.9;
	private static final double SWIPE_WIDTH_LEFT_PERCENT_DEFAULT = 0.1;
	private static final double SCROLL_HEIGHT_DOWN_PERCENT_DEFAULT = 0.6;
	private static final double SCROLL_HEIGHT_UP_PERCENT_DEFAULT = 0.3;

	public static final String SWIPE_TO_TEXT = "swipeToText";
	public static final String SCROLL_TO_TEXT = "scrollToText";
	public static final String SCROLL_TO_TEXT_IN_FIRST_POSITION = "scrollToTextInFirstPosition";
	public static final String SCROLL_TO_ELEMENT = "scrollToElement";
	public static final String SWIPE = "swipe";
	public static final String SCROLL = "scroll";
	public static final String TAP = "tap";
	// public static final String iOSSCROLL_TO_TEXT = "iOSscroll";

	public enum directionE {
		UP, DOWN
    }

	public static void tap(final int finger, final int x, final int y, final int duration) {
		final TouchTask touchTask = new TouchTask();
		touchTask.setName(TouchAction.TAP);
		touchTask.setDuration(100);
		touchTask.setFinger(finger);
		touchTask.setX(x);
		touchTask.setY(y);
		touchTask.runTask();
	}

	public static void swipeToText(final MonkeyMobileElement element, final String text, final double screenWidthStartPercent,
                                   final double screenWidthEndPercent, final int duration, final int maxSwipe, final boolean exitOnError) {

		final TouchTask touchTask = new TouchTask();
		touchTask.setElement(element);
		touchTask.setName(TouchAction.SWIPE_TO_TEXT);
		touchTask.setText(text);
		touchTask.setScreenWidthStartPercent(screenWidthStartPercent);
		touchTask.setScreenWidthEndPercent(screenWidthEndPercent);
		touchTask.setDuration(duration);
		touchTask.setMaxIteration(maxSwipe);
		touchTask.setExitOnError(exitOnError);
		touchTask.runTask();

	}

	public static void swipeRightToText(final MonkeyMobileElement element, final String text, final int maxSwipe, final boolean exitOnError) {
		final TouchTask touchTask = new TouchTask();
		touchTask.setElement(element);
		touchTask.setName(TouchAction.SWIPE_TO_TEXT);
		touchTask.setText(text);
		touchTask.setScreenWidthStartPercent(TouchAction.SWIPE_WIDTH_RIGHT_PERCENT_DEFAULT);
		touchTask.setScreenWidthEndPercent(TouchAction.SWIPE_WIDTH_LEFT_PERCENT_DEFAULT);
		touchTask.setMaxIteration(maxSwipe);
		touchTask.setExitOnError(exitOnError);
		touchTask.runTask();
	}

	public static void scrollToText(final MonkeyWebElement element, final String text, final double screenHeightStartPercent,
                                    final double screenHeightEndPercent, final int duration, final int maxScroll, final boolean exitOnError) {

		final TouchTask touchTask = new TouchTask();
		touchTask.setElement(element);
		touchTask.setName(TouchAction.SCROLL_TO_TEXT);
		touchTask.setText(text);
		touchTask.setScreenHeightStartPercent(screenHeightStartPercent);
		touchTask.setScreenHeightEndPercent(screenHeightEndPercent);
		touchTask.setDuration(duration);
		touchTask.setMaxIteration(maxScroll);
		touchTask.setExitOnError(exitOnError);
		touchTask.runTask();
	}

	public static boolean scrollDownToText(final MonkeyWebElement element, final String text, final int maxScroll, final boolean exitOnError) {
		final TouchTask touchTask = new TouchTask();
		touchTask.setElement(element);
		touchTask.setName(TouchAction.SCROLL_TO_TEXT);
		touchTask.setText(text);
		touchTask.setScreenHeightStartPercent(TouchAction.SCROLL_HEIGHT_DOWN_PERCENT_DEFAULT);
		touchTask.setScreenHeightEndPercent(TouchAction.SCROLL_HEIGHT_UP_PERCENT_DEFAULT);
		touchTask.setMaxIteration(maxScroll);
		touchTask.setExitOnError(exitOnError);
		touchTask.runTask();
		return touchTask.isElementFound();

	}

	/*
	 * public static boolean iOSscrollToText(String text, boolean exitOnError) {
	 * TouchTask touchTask = new TouchTask();
	 * touchTask.setName(iOSSCROLL_TO_TEXT); touchTask.setText(text);
	 * touchTask.setExitOnError(exitOnError); touchTask.runTask(); return
	 * touchTask.isElementFound();
	 * 
	 * }
	 */

	public static void scrollToTextInFirstPosition(final MonkeyWebElement element, final String text,
                                                   final double screenHeightStartPercent, final double screenHeightEndPercent, final int duration, final int maxScroll,
                                                   final boolean exitOnError) {
		final TouchTask touchTask = new TouchTask();
		touchTask.setElement(element);
		touchTask.setName(TouchAction.SCROLL_TO_TEXT_IN_FIRST_POSITION);
		touchTask.setText(text);
		touchTask.setScreenHeightStartPercent(screenHeightStartPercent);
		touchTask.setScreenHeightEndPercent(screenHeightEndPercent);
		touchTask.setDuration(duration);
		touchTask.setMaxIteration(maxScroll);
		touchTask.setExitOnError(exitOnError);
		touchTask.runTask();

	}

	public static void scrollDownToTextInFirstPosition(final MonkeyWebElement element, final String text, final int maxScroll,
                                                       final boolean exitOnError) {
		final TouchTask touchTask = new TouchTask();
		touchTask.setElement(element);
		touchTask.setName(TouchAction.SCROLL_TO_TEXT_IN_FIRST_POSITION);
		touchTask.setText(text);
		touchTask.setScreenHeightStartPercent(TouchAction.SCROLL_HEIGHT_DOWN_PERCENT_DEFAULT);
		touchTask.setScreenHeightEndPercent(TouchAction.SCROLL_HEIGHT_UP_PERCENT_DEFAULT);
		touchTask.setMaxIteration(maxScroll);
		touchTask.setExitOnError(exitOnError);
		touchTask.runTask();
	}

	public static void scrollToElement(final MonkeyWebElement element, final double screenHeightStartPercent,
                                       final double screenHeightEndPercent, final int duration, final int maxScroll, final boolean exitOnError) {
		final TouchTask touchTask = new TouchTask();
		touchTask.setElement(element);
		touchTask.setName(TouchAction.SCROLL_TO_ELEMENT);
		touchTask.setScreenHeightStartPercent(screenHeightStartPercent);
		touchTask.setScreenHeightEndPercent(screenHeightEndPercent);
		touchTask.setDuration(duration);
		touchTask.setMaxIteration(maxScroll);
		touchTask.setExitOnError(exitOnError);
		touchTask.runTask();
	}

	public static void scrollDownToElement(final MonkeyWebElement element, final int maxScroll) {
		final TouchTask touchTask = new TouchTask();
		touchTask.setElement(element);
		touchTask.setName(TouchAction.SCROLL_TO_ELEMENT);
		touchTask.setScreenHeightStartPercent(TouchAction.SCROLL_HEIGHT_DOWN_PERCENT_DEFAULT);
		touchTask.setScreenHeightEndPercent(TouchAction.SCROLL_HEIGHT_UP_PERCENT_DEFAULT);
		touchTask.setMaxIteration(maxScroll);
		touchTask.setInitElement(false);
		touchTask.runTask();
	}

	public static void scrollDownToElement(final MonkeyWebElement element, final int maxScroll, final double screenWidthStartPercent,
                                           final double screenWidthEndPercent, final int duration) {
		final TouchTask touchTask = new TouchTask();
		touchTask.setElement(element);
		touchTask.setName(TouchAction.SCROLL_TO_ELEMENT);
		touchTask.setScreenHeightStartPercent(screenWidthStartPercent);
		touchTask.setScreenHeightEndPercent(screenWidthEndPercent);
		touchTask.setMaxIteration(maxScroll);
		touchTask.setDuration(duration);
		touchTask.setInitElement(false);
		touchTask.runTask();
	}

	public static void scrollUpToElement(final MonkeyWebElement element, final int maxScroll) {
		final TouchTask touchTask = new TouchTask();
		touchTask.setElement(element);
		touchTask.setName(TouchAction.SCROLL_TO_ELEMENT);
		touchTask.setScreenHeightStartPercent(TouchAction.SCROLL_HEIGHT_UP_PERCENT_DEFAULT);
		touchTask.setScreenHeightEndPercent(TouchAction.SCROLL_HEIGHT_DOWN_PERCENT_DEFAULT);
		touchTask.setMaxIteration(maxScroll);
		touchTask.setInitElement(false);
		// Set by default duration to 2000 ms in TouchTask
		// touchTask.setDuration(2);
		touchTask.runTask();
	}

	public static void swipe(final double screenWidthStartPercent, final double screenWidthEndPercent, final int duration) {
		final TouchTask touchTask = new TouchTask();
		touchTask.setName(TouchAction.SWIPE);
		touchTask.setScreenWidthStartPercent(screenWidthStartPercent);
		touchTask.setScreenWidthEndPercent(screenWidthEndPercent);
		touchTask.setDuration(duration);
		touchTask.setInitElement(false);
		touchTask.runTask();
	}

	public static void swipe(final double screenWidthStartPercent, final double screenWidthEndPercent, final double screenHeightPercent,
                             final int duration) {
		final TouchTask touchTask = new TouchTask();
		touchTask.setName(TouchAction.SWIPE);
		touchTask.setScreenWidthStartPercent(screenWidthStartPercent);
		touchTask.setScreenWidthEndPercent(screenWidthEndPercent);
		touchTask.setScreenHeightPercent(screenHeightPercent);
		touchTask.setDuration(duration);
		touchTask.setInitElement(false);
		touchTask.runTask();
	}

	public static void scroll(final directionE direction) {
		final TouchTask touchTask = new TouchTask();
		touchTask.setName(TouchAction.SCROLL);
		if (direction == directionE.DOWN) {
			touchTask.setScreenHeightStartPercent(TouchAction.SCROLL_HEIGHT_DOWN_PERCENT_DEFAULT);
			touchTask.setScreenHeightEndPercent(TouchAction.SCROLL_HEIGHT_UP_PERCENT_DEFAULT);
		} else if (direction == directionE.UP) {
			touchTask.setScreenHeightStartPercent(TouchAction.SCROLL_HEIGHT_UP_PERCENT_DEFAULT);
			touchTask.setScreenHeightEndPercent(TouchAction.SCROLL_HEIGHT_DOWN_PERCENT_DEFAULT);
		}
		// Set by default duration to 2000 ms in TouchTask
		touchTask.setDuration(4);
		touchTask.setInitElement(false);
		touchTask.runTask();
	}

	public static void swipeLeftToText(final MonkeyMobileElement element, final String text, final int maxSwipe, final boolean exitOnError) {
		final TouchTask touchTask = new TouchTask();
		touchTask.setElement(element);
		touchTask.setName(TouchAction.SWIPE_TO_TEXT);
		touchTask.setText(text);
		touchTask.setScreenWidthStartPercent(TouchAction.SWIPE_WIDTH_LEFT_PERCENT_DEFAULT);
		touchTask.setScreenWidthEndPercent(TouchAction.SWIPE_WIDTH_RIGHT_PERCENT_DEFAULT);
		touchTask.setMaxIteration(maxSwipe);
		touchTask.setExitOnError(exitOnError);
		touchTask.runTask();
	}

	public static void swipe(final double screenWidthStartPercent, final double screenHeighStartPercent,
                             final double screenWidthEndPercent, final double screenHeightEndPercent, final int duration) {
		final TouchTask touchTask = new TouchTask();
		touchTask.setName(TouchAction.SWIPE);
		touchTask.setScreenWidthStartPercent(screenWidthStartPercent);
		touchTask.setScreenHeightStartPercent(screenHeighStartPercent);
		touchTask.setScreenWidthEndPercent(screenWidthEndPercent);
		touchTask.setScreenHeightEndPercent(screenHeightEndPercent);
		touchTask.setDuration(duration);
		touchTask.setInitElement(false);
		touchTask.runTask();
	}

}
