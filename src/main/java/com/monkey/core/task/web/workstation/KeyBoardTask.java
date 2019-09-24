
package com.monkey.core.task.web.workstation;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.monkey.api.MonkeyExecutionContext;
import com.monkey.api.mobile.workstation.TouchAction;
import com.monkey.api.web.workstation.KeyBoard;
import com.monkey.api.web.workstation.Navigator;
import com.monkey.core.enumeration.Protocol;
import com.monkey.core.enumeration.Supportedbrowsers;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.AbstractTask;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;


public class KeyBoardTask extends AbstractTask {

	private String name;
	private Keys keys;
	private String description;

	/**
	 * Fire the event of action on the keyBoard with the selenium implementation
	 */
	@Override
	public void execute() {
		
		if (ExecutionManager.getProtocol().equals(String.valueOf(Protocol.appium))) {
			
			if (this.name.equals(KeyBoard.KEYBOARD_PRESS_KEY)) {
                this.description = "Press Key << " + this.getKeys() + " >>";
				// TODO add mobile management here
			} else if (this.name.equals(KeyBoard.KEYBOARD_PRESS_ESCAPE)) {
                this.description = "Press key Escape";
				if(MonkeyExecutionContext.isAndroid())
					((AndroidDriver<WebElement>) (ExecutionManager.getMonkeyDriver())).pressKeyCode(AndroidKeyCode.ENTER);
				if(MonkeyExecutionContext.isIOS())
					((IOSDriver<WebElement>) ExecutionManager.getMonkeyDriver()).getKeyboard().sendKeys(Keys.ESCAPE);
				// TODO add mobile management here
			} else if (this.name.equals(KeyBoard.KEYBOARD_PRESS_PAGE_DOWN)) {
                this.description = "Press key PageDown";
				TouchAction.scroll(TouchAction.directionE.DOWN);
			} else if (this.name.equals(KeyBoard.KEYBOARD_PRESS_PAGE_UP)) {
                this.description = "Press key PageUp";
				TouchAction.scroll(TouchAction.directionE.UP);
			} else if (this.name.equals(KeyBoard.KEYBOARD_PRESS_COMPOSITE_KEYS)) {

			}
		} else {
			Actions actionBuilder = new Actions(ExecutionManager.getMonkeyDriver());
			switch (this.name){
				case KeyBoard.KEYBOARD_PRESS_KEY:
					this.description = "Press Key << " + this.getKeys() + " >>";
					actionBuilder.sendKeys(this.getKeys()).build().perform();
					break;
				case KeyBoard.KEYBOARD_PRESS_ESCAPE:
					this.description = "Press key Escape";
					actionBuilder.sendKeys(Keys.ESCAPE).build().perform();
					break;
				case KeyBoard.KEYBOARD_PRESS_PAGE_DOWN:
					this.description = "Press key PageDown";
					if (Navigator.isCurrentBrowser(Supportedbrowsers.internetexplorer)) {
						ExecutionManager.getMonkeyDriver()
								.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,"
										+ "document.body.scrollHeight,document.documentElement.clientHeight));");

					}
					actionBuilder.sendKeys(Keys.PAGE_DOWN).build().perform();
					break;
				case KeyBoard.KEYBOARD_PRESS_PAGE_UP:
					this.description = "Press key PageUp";
					if (Navigator.isCurrentBrowser(Supportedbrowsers.internetexplorer)) {
						ExecutionManager.getMonkeyDriver().executeScript("window.scrollTo(0,0);");
					}
					actionBuilder.sendKeys(Keys.PAGE_UP).build().perform();
					break;
			}
		}
	}

	/**
	 * Press composite keys at the same time
	 * 
	 * @param args
	 */
	public void pressCompositeKeys(final CharSequence... args) {
		Actions actionBuilder = new Actions(ExecutionManager.getMonkeyDriver());
		actionBuilder.sendKeys(args).build().perform();
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
	 * Set the name of the action
	 * 
	 * @param name
	 * @return
	 */
	public AbstractTask setName(final String name) {
		this.name = name;
		return this;
	}

	/**
	 * Return the keyboard keys
	 * 
	 * @return
	 */
	public Keys getKeys() {
		return this.keys;
	}

	/**
	 * 
	 * @param keys
	 */
	public void setKeys(final Keys keys) {
		this.keys = keys;
	}

}
