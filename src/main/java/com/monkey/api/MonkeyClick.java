package com.monkey.api;

import com.monkey.api.page.MonkeyWebElement;
import com.monkey.core.task.common.MasterClickTask;

public class MonkeyClick {

	public static void clickOnListByIndex(final MonkeyWebElement monkeyElement, final int index) {
		final MasterClickTask masterClickTask = new MasterClickTask();
		masterClickTask.setName(MasterClickTask.CLICK_ON_LIST_BY_INDEX);
		masterClickTask.setElement(monkeyElement);
		masterClickTask.setIndex(index);
		masterClickTask.runTask();
	}

	public static void clickOnListByText(final MonkeyWebElement monkeyElement, final String text) {
		final MasterClickTask masterClickTask = new MasterClickTask();
		masterClickTask.setName(MasterClickTask.CLICK_ON_LIST_BY_TEXT);
		masterClickTask.setElement(monkeyElement);
		masterClickTask.setText(text);
		masterClickTask.runTask();
	}
	
	public static void clickOnListByIndexSubElement(final MonkeyWebElement monkeyElement, final int index, final MonkeyBy subElementBy) {
		final MasterClickTask masterClickTask = new MasterClickTask();
		masterClickTask.setName(MasterClickTask.CLICK_ON_LIST_SUBELEMENT);
		masterClickTask.setElement(monkeyElement);
		masterClickTask.setIndex(index);
		masterClickTask.setSubElementBy(subElementBy);
		masterClickTask.runTask();
	}
	
	public static void clickOnSubElement(final MonkeyWebElement monkeyElement, final MonkeyBy subElementBy){
		final MasterClickTask masterClickTask = new MasterClickTask();
		masterClickTask.setName(MasterClickTask.CLICK_ON_SUBELEMENT);
		masterClickTask.setElement(monkeyElement);
		masterClickTask.setSubElementBy(subElementBy);
		masterClickTask.runTask();
	}
	
	public static void clickOnSubElementListByIndex(final MonkeyWebElement monkeyElement, final MonkeyBy subElementListBy, final int index){
		final MasterClickTask masterClickTask = new MasterClickTask();
		masterClickTask.setName(MasterClickTask.CLICK_ON_SUBELEMENT_LIST_BY_INDEX);
		masterClickTask.setElement(monkeyElement);
		masterClickTask.setSubElementBy(subElementListBy);
		masterClickTask.setIndex(index);
		masterClickTask.runTask();
	}
	
	public static void clickOnSubElementListByText(final MonkeyWebElement monkeyElement, final MonkeyBy subElementListBy, final String text){
		final MasterClickTask masterClickTask = new MasterClickTask();
		masterClickTask.setName(MasterClickTask.CLICK_ON_SUBELEMENT_LIST_BY_TEXT);
		masterClickTask.setElement(monkeyElement);
		masterClickTask.setSubElementBy(subElementListBy);
		masterClickTask.setText(text);
		masterClickTask.runTask();
	}
	
	public static void clickOnCoordinate(final int x, final int y) {
		final MasterClickTask masterClickTask = new MasterClickTask();
		masterClickTask.setName(MasterClickTask.CLICK_ON_COORDINATE);
		masterClickTask.setX(x);
		masterClickTask.setY(y);
		masterClickTask.setInitElement(false);
		masterClickTask.runTask();
	}
}
