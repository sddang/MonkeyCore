package com.monkey.core.task.web.browser;

import com.monkey.api.Wait;
import com.monkey.api.web.browser.Window;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.AbstractTask;
import org.openqa.selenium.Dimension;

import java.util.Set;

public class WindowTask extends AbstractTask {

    private String name;
    private String frameName;
    private int large;
    private int height;

    private String description;

    /**
     * Execute actions of the window using the Selenium implementation
     */
    @Override
    public void execute() {
        final String currentWindow = ExecutionManager.getMonkeyDriver().getWindowHandle();
        final Set<String> AllWindows = ExecutionManager.getMonkeyDriver().getWindowHandles();
        switch (this.name) {
            case Window.CLOSE:
                this.description = "Close Window";
                for (final String win : AllWindows) {
                    if (win.equals(currentWindow)) {
                        ExecutionManager.getMonkeyDriver().switchTo().window(currentWindow).close();
                    }
                }
                break;
            case Window.MAXIMIZE_WINDOW:
                this.description = "Maximize the current window";
                ExecutionManager.getMonkeyDriver().manage().window().maximize();
                break;
            case Window.SET_SIZE:
                this.description = "Resise the current window";
                ExecutionManager.getMonkeyDriver().manage().window().setSize(new Dimension(this.large, this.height));
                break;
            case Window.DEFAULT_CONTENT:
                this.description = "Switch to the default or the first frame";
                ExecutionManager.getMonkeyDriver().switchTo().defaultContent();
                break;
            case Window.SWITCH_TO_CURRENT_WINDOW:
                this.description = "Switch the focus to the current window";
                for (final String win : AllWindows) {
                    if (!win.equals(currentWindow)) {
                        ExecutionManager.getMonkeyDriver().switchTo().window(win);
                    }
                }
                break;
            case Window.SWITCH_TO_FRAME:
                this.description = "Switch to the given Frame << " + this.getFrameName()
                        + " >>";
                Wait.implicitWait(1);
                if (this.getFrameName() != null) {
                    ExecutionManager.getMonkeyDriver().switchTo().frame(this.getFrameName());
                } else {
                    ExecutionManager.getMonkeyDriver().switchTo().frame(this.getElement().getWebElement());
                }
                Wait.implicitWait(1);
                break;
            case Window.SWITCH_TO_WINDOW:
                this.description = "Switch the focus to the old window";

                final Set<String> allWindows = ExecutionManager.getMonkeyDriver().getWindowHandles();
                final int size = allWindows.size();
                if (size == 1) {
                    ExecutionManager.getMonkeyDriver().switchTo().window(allWindows.iterator().next());
                    return;
                }
                String activeWindow;
                try {
                    activeWindow = ExecutionManager.getMonkeyDriver().getWindowHandle();
                } catch (final Exception e) {
                    final Object[] tab = allWindows.toArray();
                    activeWindow = (String) tab[size - 1];
                }
                for (final String win : allWindows) {
                    if (win.equals(activeWindow)) {
                        ExecutionManager.getMonkeyDriver().switchTo().window(win);
                    }
                }
                break;
            case Window.SWITCH_TO_ACTIVE_ELEMENT:
                this.description = "Switch to active element";
                ExecutionManager.getMonkeyDriver().switchTo().activeElement();
                break;
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
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the frameName
     */
    public String getFrameName() {
        return this.frameName;
    }

    /**
     * @param frameName the frameName to set
     */
    public void setFrameName(final String frameName) {
        this.frameName = frameName;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * @param hight the hight to set
     */
    public void setHeight(final int hight) {
        height = hight;
    }

    /**
     * @return the large
     */
    public int getLarge() {
        return this.large;
    }

    /**
     * @param large the large to set
     */
    public void setLarge(final int large) {
        this.large = large;
    }

}
