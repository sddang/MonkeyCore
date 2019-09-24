

package com.monkey.api.web.browser;

import java.util.Set;

import com.monkey.api.page.MonkeyWebElement;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.web.browser.WindowTask;


/**
 * This class manage the lifeCycle of the window.
 */
public class Window {

	public static final String SET_SIZE = "setSize";
	public static final String MAXIMIZE_WINDOW = "maximizeWindow";
	public static final String CLOSE = "close";
	public static final String SWITCH_TO_CURRENT_WINDOW = "switchToCurrentWindow";
	public static final String SWITCH_TO_FRAME = "switchToFrame";
	public static final String SWITCH_TO_WINDOW = "switchTotWindow";
	public static final String DEFAULT_CONTENT = "defaultContent";
	public static final String SWITCH_TO_ACTIVE_ELEMENT = "switchToActiveElement";

	/**
	 * get the current window handle
	 * 
	 */
	public static String getWindowHandle() {
		return ExecutionManager.getMonkeyDriver().getWindowHandle();
	}

	/**
	 * get the current window handle
	 * 
	 */
	public static Set<String> getWindowHandles() {
		return ExecutionManager.getMonkeyDriver().getWindowHandles();
	}

	/**
	 * Selects either the first frame on the page, or the main document when profil
	 * page contains iframes.
	 * 
	 */
	public static void switchTodefaultContent() {
		final WindowTask windowTask = new WindowTask();
		windowTask.setName(Window.DEFAULT_CONTENT);
		windowTask.runTask();
	}

	/**
	 * Switch to the given window. this action is called when we open profil new
	 * window and we should move the focus of Selenium to this new window
	 * 
	 * @param windowName
	 *            the name of the window
	 */
	public static void switchToWindow(final String windowName) {
		final WindowTask windowTask = new WindowTask();
		windowTask.setName(Window.SWITCH_TO_WINDOW);
		windowTask.runTask();
	}

	/**
	 * Switch to the given Frame name. This method is called when we want to
	 * manipulate fields of an Iframe. We have to move the focus on this Iframe
	 * 
	 * @param frameNameOrId
	 *            the iframe name or id
	 */
	public static void switchToFrame(final String frameNameOrId) {
		final WindowTask windowTask = new WindowTask();
		windowTask.setName(Window.SWITCH_TO_FRAME);
		windowTask.setFrameName(frameNameOrId);
		windowTask.runTask();
	}

	/**
	 * Switch to the given Frame name. This method is called when we want to
	 * manipulate fields of an Iframe. We have to move the focus on this Iframe
	 * This method is used when we can't locate the iframe by it's name or id
	 * 
	 * @param MonkeyWebElement
	 *            the iFrame element.
	 */
	public static void switchToFrame(final MonkeyWebElement element) {
		final WindowTask windowTask = new WindowTask();
		windowTask.setElement(element);
		windowTask.setName(Window.SWITCH_TO_FRAME);
		windowTask.runTask();
	}

	/**
	 * Switch to active element in the window
	 */
	public static void switchToActiveElement() {
		final WindowTask windowTask = new WindowTask();
		windowTask.setName(Window.SWITCH_TO_ACTIVE_ELEMENT);
		windowTask.runTask();
	}

	/**
	 * This method is called when we open new window and then we close it and we
	 * want to set the focus back to he first window.
	 */
	public static void switchToCurrentWindow() {
		final WindowTask windowTask = new WindowTask();
		windowTask.setName(Window.SWITCH_TO_CURRENT_WINDOW);
		windowTask.runTask();
	}

	/**
	 * Close the window
	 */
	public static void close() {
		final WindowTask windowTask = new WindowTask();
		windowTask.setName(Window.CLOSE);
		windowTask.runTask();
	}

	/**
	 * Maximize the window
	 */
	public static void maximizeWindow() {
		final WindowTask windowTask = new WindowTask();
		windowTask.setName(Window.MAXIMIZE_WINDOW);
		windowTask.runTask();
	}

	/**
	 * Set the size of the window with the given large and high params
	 * 
	 * @param large
	 * @param hight
	 */
	public static void setSize(final int large, final int hight) {
		final WindowTask windowTask = new WindowTask();
		windowTask.setName(Window.SET_SIZE);
		windowTask.setLarge(large);
		windowTask.setHight(hight);
		windowTask.runTask();
	}
	
}
