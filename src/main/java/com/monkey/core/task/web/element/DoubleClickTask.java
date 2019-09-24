

package com.monkey.core.task.web.element;

import org.openqa.selenium.interactions.Actions;

import com.monkey.api.Wait;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.AbstractTask;

public class DoubleClickTask extends AbstractTask{

	/**
	 * Fire the event of doubleclicking element with the selenium implementation
	 */
	@Override
	public void execute() {
		final Actions actionbuilder = new Actions(ExecutionManager.getMonkeyDriver());
		actionbuilder.doubleClick(this.getElement().getWebElement());
		actionbuilder.perform();
		Wait.implicitWait(3);
	}

	@Override
	public String getDescription() {
		return "DoubleClick the element << " + this.getElement()+" >>";
	}
	
	@Override
	public String getName() {
		return "DoubleClickElement";
	}

}
