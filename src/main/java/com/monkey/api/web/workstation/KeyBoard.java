package com.monkey.api.web.workstation;

import com.monkey.core.task.web.workstation.KeyBoardTask;
import org.openqa.selenium.Keys;

/**
 * This class manage the keyboard actions
 */
public class KeyBoard {

    public final static String KEYBOARD_PRESS_KEY = "pressKey";
    public final static String KEYBOARD_PRESS_COMPOSITE_KEYS = "pressCompositekeys";
    public final static String KEYBOARD_PRESS_ESCAPE = "escape";
    public final static String KEYBOARD_PRESS_PAGE_DOWN = "pageDown";
    public final static String KEYBOARD_PRESS_PAGE_UP = "pageUp";

    /**
     * Press the given key
     *
     * @param keys
     */
    public static void pressKey(final Keys keys) {
        final KeyBoardTask task = new KeyBoardTask();
        task.setName(KeyBoard.KEYBOARD_PRESS_KEY);
        task.setKeys(keys);
        task.runTask();
    }

    /**
     * Press some keys simultaneously
     *
     * @param args
     */
    public static void pressCompositeKeys(final CharSequence... args) {
        final KeyBoardTask task = new KeyBoardTask();
        task.setName(KeyBoard.KEYBOARD_PRESS_COMPOSITE_KEYS);
        task.pressCompositeKeys(args);
        task.runTask();
    }

    /**
     * Press escape key
     */
    public static void escape() {
        new KeyBoardTask().setName(KeyBoard.KEYBOARD_PRESS_ESCAPE).runTask();
    }

    /**
     * Press PageDown key
     */
    public static void pageDown() {
        new KeyBoardTask().setName(KeyBoard.KEYBOARD_PRESS_PAGE_DOWN).runTask();
    }

    /**
     * Press the PageUp key
     */
    public static void pageUp() {
        new KeyBoardTask().setName(KeyBoard.KEYBOARD_PRESS_PAGE_UP).runTask();
    }
}
