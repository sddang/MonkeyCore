

package com.monkey.core.task.web.browser;

import org.openqa.selenium.Alert;

import com.monkey.api.Wait;
import com.monkey.api.web.browser.Alerts;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.AbstractTask;

public class AlertTask extends AbstractTask {

	private String name;
	private String keysToSend;
	private String description;
	/**
	 * Fire the all the events of an Alert with the selenium implementation
	 */
	@Override
	public void execute() {
		Alert alert = null;
		try{
			alert = ExecutionManager.getMonkeyDriver().switchTo().alert();
		}catch(final Exception e){
			throw new RuntimeException();
		}
		if (alert != null) {
			Wait.implicitWait(3);
			
			if (this.name.equals(Alerts.ACCEPT_ALERT)) {
                this.description = "Accept Alert";
				alert.accept();
			} else if (this.name.equals(Alerts.DISMISS_ALERT)) {
                this.description = "Dismiss Alert";
				alert.dismiss();
			} else if (this.name.equals(Alerts.GET_ALERT_LABEL)) {
                this.description = "GetText From Alert";
				alert.getText();
			} else if (this.name.equals(Alerts.SENDKEY_TO_ALERT)) {
                this.description = "Send Keys to alert " + this.getKeysToSend();
				alert.sendKeys(this.getKeysToSend());
			}
			Wait.implicitWait(1);
		}

	}

	@Override
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the keysToSend
	 */
	public String getKeysToSend() {
		return this.keysToSend;
	}

	/**
	 * @param keysToSend
	 *            the keysToSend to set
	 */
	public void setKeysToSend(final String keysToSend) {
		this.keysToSend = keysToSend;
	}

}
