package com.monkey.api.mobile.workstation;

import com.monkey.api.page.MonkeyWebElement;
import com.monkey.core.task.mobile.workstation.KeyBoardTask;

public class KeyBoard {

    public static final String HIDE_KEYBOARD = "hideKeyboard";
    public static final String TAP_ENTER = "tapEnter";


    public static void hideKeyboard() {
        final KeyBoardTask task = new KeyBoardTask();
        task.setName(KeyBoard.HIDE_KEYBOARD);
        task.runTask();
    }

    public static void hideKeyboard(final MonkeyWebElement clickOnElement) {
        final KeyBoardTask task = new KeyBoardTask();
        task.setName(KeyBoard.HIDE_KEYBOARD);
        task.setElement(clickOnElement);
        task.runTask();
    }

    public static void tapEnter() {
        final KeyBoardTask task = new KeyBoardTask();
        task.setName(KeyBoard.TAP_ENTER);
        task.runTask();
    }

}
