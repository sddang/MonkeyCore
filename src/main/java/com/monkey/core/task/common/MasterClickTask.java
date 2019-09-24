package com.monkey.core.task.common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.monkey.api.MonkeyBy;
import com.monkey.api.page.MonkeyMobileElement;
import com.monkey.api.page.MonkeyWebElement;
import com.monkey.core.exception.ExceptionCode;
import com.monkey.core.exception.MonkeyException;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.AbstractTask;
import com.monkey.core.task.web.element.ClickTask;

public class MasterClickTask extends AbstractTask {

	public static final String CLICK_ON_LIST_BY_INDEX = "clickOnListByIndex";
	public static final String CLICK_ON_LIST_BY_TEXT = "clickOnListByText";
	public static final String CLICK_ON_LIST_SUBELEMENT = "clickOnListSubElement";
	public static final String CLICK_ON_SUBELEMENT = "clickOnSubElement";
	public static final String CLICK_ON_SUBELEMENT_LIST_BY_INDEX = "clickOnSubElementListByIndex";
	public static final String CLICK_ON_SUBELEMENT_LIST_BY_TEXT = "clickOnSubElementListByText";
	public static final String CLICK_ON_COORDINATE = "clickOnCoordinate";

	private String name;
	private int index;
	private String text;
	private By subElementBy;
	private boolean mobileElement;
	private int x;
	private int y;

	public void setY(final int y) {
		this.y = y;
	}

	public void setX(final int x) {
		this.x = x;
	}

	public MasterClickTask() {
        this.initElement = false;
	}

	@Override
	public void execute() {

		if (this.getElement().getClass() == MonkeyMobileElement.class) {
            this.mobileElement = true;
		}

		switch (this.name) {

		case MasterClickTask.CLICK_ON_LIST_BY_INDEX:
            this.clickOnListByIndex(this.getElement().getListElement(), this.index);
			break;

		case MasterClickTask.CLICK_ON_LIST_BY_TEXT:
            this.clickOnListByText(this.getElement().getListElement(), this.text);
			break;

		case MasterClickTask.CLICK_ON_LIST_SUBELEMENT:
            this.clickOnListSubElement(this.getElement().getListElement(), this.index, this.subElementBy);
			break;

		case MasterClickTask.CLICK_ON_SUBELEMENT:
            this.monkeyElementClick(this.getElement().getWebElement().findElement(this.subElementBy));
			break;

		case MasterClickTask.CLICK_ON_SUBELEMENT_LIST_BY_INDEX:
            this.clickOnListByIndex(this.getElement().getWebElement().findElements(this.subElementBy), this.index);
			break;

		case MasterClickTask.CLICK_ON_SUBELEMENT_LIST_BY_TEXT:
            this.clickOnListByText(this.getElement().getWebElement().findElements(this.subElementBy), this.text);
			break;
			
		case MasterClickTask.CLICK_ON_COORDINATE:
            this.clickByCoordinate(this.x, this.y);
			break;
		}

	}

	private void clickByCoordinate(final int coordinateX, final int coordinateY) {
		final Actions act = new Actions(ExecutionManager.getMonkeyDriver());
		act.moveByOffset(coordinateX, coordinateY);
		act.click();
		act.build();
		act.perform();
	}

	@Override
	public String getDescription() {

		switch (this.name) {
		case MasterClickTask.CLICK_ON_LIST_BY_INDEX:
			return "click on the index << " + this.index + " >> in the list of elements [ " + this.getElement() + "]";
		case MasterClickTask.CLICK_ON_LIST_BY_TEXT:
			return "click on element with text << " + this.text + " >> in the list of elements [ " + this.getElement() + "]";
		case MasterClickTask.CLICK_ON_LIST_SUBELEMENT:
			return "click on sub element [ " + this.getElement() + "].get(" + this.index + ").findElement"
					+ this.subElementBy + ")";
		case MasterClickTask.CLICK_ON_SUBELEMENT:
			return "click on sub element [ " + this.getElement() + "].findElement(" + this.subElementBy + ")";
		case MasterClickTask.CLICK_ON_SUBELEMENT_LIST_BY_INDEX:
			return "click on the index << " + this.index + " >> in the list of elements [ " + this.getElement()
					+ "].findElements(" + this.subElementBy + ")";
		case MasterClickTask.CLICK_ON_SUBELEMENT_LIST_BY_TEXT:
			return "click on element with text << " + this.text + " >> in the list of elements [ " + this.getElement()
					+ "].findElements(" + this.subElementBy + ")";
		case MasterClickTask.CLICK_ON_COORDINATE:
			return "click on specified coordinate x: "+ this.x +", y: "+ this.y +"";
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

	public void setIndex(final int index) {
		this.index = index;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public void setSubElementBy(final MonkeyBy subElementBy) {
		this.subElementBy = subElementBy.ToSeleniumBy();
	}

	private void clickOnListByIndex(final List<WebElement> elementList, final int index) {
		if (elementList.isEmpty() || elementList.size() < index)
			throw new MonkeyException(ExceptionCode.LIST_OUT_OF_BOUND_EXCEPTION, Integer.toString(elementList.size()),
					Integer.toString(index));
        this.monkeyElementClick(elementList.get(index));
	}

	private void clickOnListByText(final List<WebElement> elementList, final String text) {
		Boolean elementFound = false;
		for (final WebElement el : elementList) {
			if (el.getText().equals(text)) {
                this.monkeyElementClick(el);
				elementFound = true;
				break;
			}
		}
		if (!elementFound)
			throw new MonkeyException(ExceptionCode.NO_TEXT_ELEMENT_FOUND_IN_LIST, text, "[ " + this.getElement() + "]");
	}

	private void clickOnListSubElement(final List<WebElement> elementList, final int index, final By subElementBy) {
		if (elementList.isEmpty() || elementList.size() < index)
			throw new MonkeyException(ExceptionCode.LIST_OUT_OF_BOUND_EXCEPTION, Integer.toString(elementList.size()),
					Integer.toString(index));
        this.monkeyElementClick(elementList.get(index).findElement(subElementBy));
	}

	private void monkeyElementClick(final WebElement element) {
		if (!this.mobileElement) {
			final MonkeyWebElement monkeyElement = new MonkeyWebElement();
			monkeyElement.setWebElement(element);
			final ClickTask clickTask = new ClickTask();
			clickTask.setElement(monkeyElement);
			clickTask.setInitElement(false);
			clickTask.runTask();
		} else {
			element.click();
		}
	}
}
