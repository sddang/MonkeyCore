
package com.monkey.api.page;

import org.openqa.selenium.Keys;

import com.monkey.api.enumeration.Selector;
import com.monkey.core.page.LocalisationHelper;
import com.monkey.core.page.MonkeyAbstractElement;
import com.monkey.core.page.action.WebAction;
import com.monkey.core.task.web.element.AddValueTask;
import com.monkey.core.task.web.element.CheckTask;
import com.monkey.core.task.web.element.ClearTask;
import com.monkey.core.task.web.element.ClickTask;
import com.monkey.core.task.web.element.DoubleClickTask;
import com.monkey.core.task.web.element.HoverTask;
import com.monkey.core.task.web.element.RadioCheckTask;
import com.monkey.core.task.web.element.SelectTask;
import com.monkey.core.task.web.element.SubmitTask;
import com.monkey.core.task.web.element.TypeTask;
import com.monkey.core.task.web.workstation.KeyBoardTask;

public class MonkeyWebElement extends MonkeyAbstractElement implements WebAction {

	public MonkeyWebElement() {

	}

	/**
	 * Constructor with all fields of the element
	 * 
	 * @param identifier
	 *            the key identifier of the element
	 * @param selector
	 *            the way to find the element by id, name, xpath ...
	 * @param tagName
	 *            the HTML tag name
	 * @param inputValu
	 *            the value to be set to the element
	 */
	public MonkeyWebElement(final String identifier, final Selector selector) {
		super(identifier, selector, null, null);
	}

	/**
	 * Constructor with all fields of the element
	 * 
	 * @param identifier
	 *            the key identifier of the element
	 * @param selector
	 *            the way to find the element by id, name, xpath ...
	 * @param tagName
	 *            the HTML tag name
	 * @param inputValu
	 *            the value to be set to the element
	 */
	public MonkeyWebElement(final String identifier, final Selector selector, final String inputValue) {
		super(identifier, selector, null, inputValue);
	}

	/**
	 * Verify if the element is present in the page.
	 * 
	 * @return boolean: true if the element is present
	 */
	public boolean isPresent() {
		return LocalisationHelper.isElementPresent(this);
	}

	/**
	 * Verify if the element is not present in the page
	 * 
	 * @return
	 */
	public boolean isNotPresent() {
		return LocalisationHelper.isElementNotPresent(this);
	}

	/**
	 * Verify if the element is selected, this method is called for select radio
	 * or check elements
	 * 
	 * @return boolean : true is the element is selected
	 */
	public boolean isSelected() {
		return LocalisationHelper.isElementSelected(this);
	}

	/**
	 * Fire the event click for the current element.
	 */
	@Override
	public void click() {
		new ClickTask().setElement(this).runTask();
	}

	/**
	 * Click on the current element even it's not enabled, may it's parent is
	 * enabled
	 */

	@Override
	public void click(final boolean allowClickOnDisbaledElement) {
		final ClickTask clickTask = new ClickTask();
		clickTask.allowDisabledElement = allowClickOnDisbaledElement;
		clickTask.setElement(this).runTask();
	}

	/**
	 * Add profil value to the value of the element
	 */
	@Override
	public void addValue() {
		new AddValueTask().setElement(this).runTask();

	}

	/**
	 * Add new value of the element.
	 */
	@Override
	public void type() {
		new TypeTask().setElement(this).runTask();
	}
	
	@Override
	public void type(final boolean iosSendKey) {
		new TypeTask().setElement(this).runTask();
	}


	/**
	 * Clear the value of the element
	 */
	@Override
	public void clear() {
		new ClearTask().setElement(this).runTask();
	}
	
	/**
	 * Clear the value of the element
	 */
	@Override
	public void clear(final boolean checkCleaned) {
		
	}

	/**
	 * Check the radio button element if unchecked and disable check if checked
	 */
	@Override
	public void radioCheck() {
		new RadioCheckTask().setElement(this).runTask();

	}

	/**
	 * Check the checkbox element if unchecked and disable check if checked
	 */
	@Override
	public void check(final boolean enabled) {
		new CheckTask(enabled).setElement(this).runTask();
	}

	/**
	 * Hover on the element, used generally for menus
	 */
	@Override
	public void hover() {
		new HoverTask().setElement(this).runTask();
	}

	/**
	 * Used to submit forms. But in some cases we can use it in stead of click
	 * action
	 */
	@Override
	public void submit() {
		new SubmitTask().setElement(this).runTask();

	}

	/**
	 * Select profil selectBox element
	 */
	@Override
	public void select() {
		new SelectTask().setElement(this).runTask();
	}

	/**
	 * Double click the current element
	 */
	@Override
	public void doubleClick() {
		new DoubleClickTask().setElement(this).runTask();

	}

	/**
	 * Press ENTER on the Web element
	 */

	@Override
	public void setConfirmation() {
		final KeyBoardTask task = new KeyBoardTask();
		task.setElement(this);
		task.setName("pressKey");
		task.setKeys(Keys.ENTER);
		task.runTask();

	}
}
