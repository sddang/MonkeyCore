

package com.monkey.core.task.common;

import org.openqa.selenium.JavascriptExecutor;

import com.monkey.api.Wait;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.AbstractTask;

public class JsExecutorTask extends AbstractTask {

	private String script;
	private String arguments;
	private String description;
	private Object result;
	private Object object;
	/**
	 * Fire the event of executing JS scripts with the selenium implementation
	 */
	@Override
	public void execute() {
		final JavascriptExecutor js = ExecutionManager.getMonkeyDriver();
		if (this.getObject()!= null) {
            this.description = "Execute js script << " + this.getScript()+ " >> on the object << "+ this.getObject()+" >>";
			try {
                this.setResult(js.executeScript(this.getScript(), this.getObject()));
			}  catch (final Exception e) {
                this.description = "The screen has been at the edge of the scrolling";
			}
		}
		else if (this.getElement() == null) {
            this.description = "Execute js script << " + this.getScript() + " >>";
            this.setResult(js.executeScript(this.getScript()));
		} else {
			if (this.getArguments() != null) {
                this.description = "Execute js script << " + this.getScript()
						+ " >> on the element << " + this.getElement()
						+ " >> with arguments << " + this.getArguments() + " >>";
                this.setResult(js.executeScript(this.getScript(), this.getElement().getWebElement(),
                        this.getArguments()));
			} else {
                this.description = "Execute js script << " + this.getScript()+ " >> on the element << "+ this.getElement()+" >>";
                this.setResult(js.executeScript(this.getScript(), this.getElement().getWebElement()));
			}
		}
		Wait.implicitWait(1);
	}

	@Override
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public String getName() {
		return "JavaScriptExecuter";
	}

	/**
	 * @return the script
	 */
	public String getScript() {
		return this.script;
	}

	/**
	 * @param script
	 *            the script to set
	 */
	public void setScript(final String script) {
		this.script = script;
	}

	/**
	 * @return the arguments
	 */
	public String getArguments() {
		return this.arguments;
	}

	/**
	 * @param arguments
	 *            the arguments to set
	 */
	public void setArguments(final String arguments) {
		this.arguments = arguments;
	}

	public Object getResult() {
		
		return result;
	}

	public void setResult(final Object result) {
		this.result = result;
	}
	
	public Object getObject() {
		return object;
		
	}

	public void setObject(final Object obj) {
        object = obj;
		
	}

}
