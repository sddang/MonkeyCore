
package com.monkey.core.page;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.monkey.api.enumeration.Selector;
import com.monkey.api.page.MonkeyWebElement;
import com.monkey.core.exception.ExceptionCode;
import com.monkey.core.exception.MonkeyException;
import com.monkey.core.session.ExecutionManager;


public abstract class MonkeyAbstractElement {

	private String identifier;
	private Selector selector;
	protected String inputValue;
	private WebElement element;
	private List<WebElement> listElement;
	private String variableName = "dynamicVar";

	/**
	 * Default Constructor
	 */
	public MonkeyAbstractElement() {

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
	public MonkeyAbstractElement(final String identifier, final Selector selector, final String tagName, final String inputValue) {
        this.identifier = identifier;
		this.selector = selector;
		this.inputValue = inputValue;
        element = null;
        listElement = null;
	}

	/**
	 * Initialize all the fields of the test elements
	 */
	public void initializeWebElement(final boolean allowDisabledElements) {
		long timeOut = ExecutionManager.getConfiguration().getTimeOut();
		if(timeOut > 20){
			timeOut = 20;
		}
		final WebElement element = LocalisationHelper.findWebElement(this.getIdentifier(), this.getSelector(), this.getVariableName(), false, false, timeOut);

		if (!allowDisabledElements && !LocalisationHelper.isElementenabled(element)) {
			throw new MonkeyException(ExceptionCode.DISABLED_ELEMENT_FIRE_EVENT_ERROR, toString());
		}
        this.setWebElement(element);

	}

	public void initializeWebElementList() {
		final List<WebElement> listWebElement = LocalisationHelper.findElements(this.getIdentifier(), this.getSelector(),
                this.getVariableName());

        this.setListElement(listWebElement);
	}

	/**
	 * Return the identifier
	 * 
	 * @return the identifier
	 */
	public String getIdentifier() {
		return this.identifier;
	}

	/**
	 * Set the identifier
	 * 
	 * @param identifier
	 * @return
	 */
	public MonkeyWebElement setIdentifier(final String identifier) {
		this.identifier = identifier;
		return (MonkeyWebElement) this;
	}

	/**
	 * Return the selector value
	 * 
	 * @return
	 */
	public Selector getSelector() {
		return this.selector;
	}

	/**
	 * Set the selector
	 * 
	 * @param selector
	 * @return
	 */
	public MonkeyWebElement setSelector(final Selector selector) {
		this.selector = selector;
		return (MonkeyWebElement) this;
	}

	/**
	 * return the input value
	 * 
	 * @return
	 */
	public String getInputValue() {
		return this.inputValue;
	}

	/**
	 * Set an input data for the element
	 * 
	 * @param inputValue
	 * @return
	 */
	public MonkeyWebElement setInputValue(final String inputValue) {
		this.inputValue = inputValue;
		return (MonkeyWebElement) this;
	}

	/**
	 * Return the selenium web element object
	 * 
	 * @return
	 */
	public WebElement getWebElement() {
		if (this.element == null)
            this.initializeWebElement(true);
		return this.element;
	}
	
	public WebElement getInitWebElement() {
        this.initializeWebElement(true);
		return this.element;
	}

	/**
	 * Set the selenium web element object
	 * 
	 * @param element
	 */
	public void setWebElement(final WebElement element) {
		this.element = element;
	}

	/**
	 * Return the the variable name. It is the identifier of the element in the
	 * TestPage It will be used to return custom messages
	 * 
	 * @return the variableName
	 */
	public String getVariableName() {
		return this.variableName;
	}

	/**
	 * Set the variable name element
	 * 
	 * @param variableName
	 * 
	 */
	public void setVariableName(final String variableName) {
		this.variableName = variableName;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer(300);
		sb.append(this.variableName).append(".\n ");
		sb.append("TestElement : {identifier :").append(this.identifier).append(",");
		sb.append("selector :").append(this.selector).append(",");
		// sb.append("inputValue :")
		// .append(MoziumExecutionManager.getDriver().getDataMapper()
		// .mapData(inputValue)).append(",");
		// sb.append("defaultValue :").append(defaultValue).append(",");
		// sb.append("variableName :").append(variableName).append(",");
		// sb.append("WebElement :").append(element).append(",");
		sb.append("}");
		return sb.toString();
	}

	public List<WebElement> getListElement() {
		if (this.listElement == null)
            this.initializeWebElementList();
		return this.listElement;
	}
	
	public List<WebElement> getInitListElement() {
        this.initializeWebElementList();
		return this.listElement;
	}

	public void setListElement(final List<WebElement> listElement) {
		this.listElement = listElement;
	}

}
