package com.monkey.core.task.web.workstation;

import com.monkey.api.Wait;
import com.monkey.api.page.MonkeyWebElement;
import com.monkey.api.web.workstation.KeyBoard;
import com.monkey.api.web.workstation.Mouse;
import com.monkey.core.enumeration.Protocol;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.AbstractTask;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.interactions.PointerInput;

import java.time.Duration;


public class MouseTask extends AbstractTask {

    private String name;
    private MonkeyWebElement extraElement;
    private int x;
    private int y;
    private String description;

    /**
     * Fire the events that are allowed on the Mouse using the Seleiunm
     * implementation
     */
    @Override
    public void execute() {
        if (ExecutionManager.getProtocol().equals(Protocol.appium)) {
            // TODO add mobile management here
        } else {
            final Locatable element = (Locatable) this.getElement().getWebElement();
            final PointerInput mouse = new PointerInput(PointerInput.Kind.MOUSE, "mouse");
            int i = 0;
            while (i < 5) {
                try {
                    final Actions actionBuilder = new Actions(ExecutionManager.getMonkeyDriver());

                    switch (this.name) {
                        case Mouse.MOUSE_DOWN:
                            this.description = "Mouse down on the element << " + this.getElement() + " >>";
                            actionBuilder.tick(mouse.createPointerMove(Duration.ofMillis(1), PointerInput.Origin.viewport(), element.getCoordinates().onPage().getX(), element.getCoordinates().onPage().getY()));
                            actionBuilder.tick(mouse.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                            actionBuilder.build().perform();
                            break;
                        case Mouse.MOUSE_HOVER:
                            this.description = "Mouse Hover on the element << " + this.getElement() + " >>";
                            actionBuilder.tick(mouse.createPointerMove(Duration.ofMillis(1), PointerInput.Origin.viewport(), element.getCoordinates().onPage().getX(), element.getCoordinates().onPage().getY()));
                            actionBuilder.tick(mouse.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                            actionBuilder.build().perform();
                            break;
                        case Mouse.MOUSE_CONTEXT_CLICK:
                            this.description = "ContextClick on << " + this.getElement() + " >>";
                            actionBuilder.contextClick(this.getElement().getWebElement()).build().perform();
                            break;
                        case Mouse.GOTO_POSITION:
                            this.description = "Mouse move to the position:  X << " + this.getX() + " >> / Y << " + this.getY() + " >>";
                            actionBuilder.tick(mouse.createPointerMove(Duration.ofMillis(1), PointerInput.Origin.viewport(), this.getX(), this.getY()));
                            actionBuilder.perform();
                            break;
                        case Mouse.MOUSE_CLICK_AND_HOLD:
                            this.description = "Mouse Click And Hold on the element:   << " + this.getElement() + " >>";
                            actionBuilder.clickAndHold(this.getElement().getWebElement());
                            actionBuilder.perform();
                            break;
                        case Mouse.MOUSE_CLICK:
                            this.description = "Mouse Click on the element:   << " + this.getElement() + " >>";
                            actionBuilder.click(this.getElement().getWebElement());
                            actionBuilder.perform();
                            break;
                        case Mouse.MOUSE_MOVE_TO_ELEMENT:
                            this.description = "Move to the element : << " + this.getElement() + " >>";
                            actionBuilder.moveToElement(this.getElement().getWebElement()).perform();
                            break;
                        case Mouse.MOUSE_RELEASE:
                            this.description = "Release mouse from the element : << " + this.getElement() + " >>";
                            actionBuilder.release(this.getElement().getWebElement()).build().perform();
                        case Mouse.DRAG_AND_DROP:
                            this.description = "Mouse drag and drop : source element << " + this.getElement()
                                    + " >> / target element << " + this.getExtraElement() + " >>";
                            actionBuilder.dragAndDrop(this.getElement().getWebElement(), this.getExtraElement().getWebElement());
                            actionBuilder.perform();
                            break;
                    }
                    Wait.implicitWait(1);
                } catch (final Exception e) {
                    i = i + 1;
                    if (i == 1 || i == 2) {
                        KeyBoard.pageDown();
                    } else {
                        KeyBoard.pageUp();
                    }
                }
            }
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
     * Set the name of the action
     *
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * The second element that is used in the dragAndDrop action
     *
     * @return
     */
    public MonkeyWebElement getExtraElement() {
        return this.extraElement;
    }

    /**
     * @param extraElement
     */
    public void setExtraElement(final MonkeyWebElement extraElement) {
        this.extraElement = extraElement;
    }

    /**
     * @return
     */
    public int getX() {
        return this.x;
    }

    /**
     * @param x
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * @return
     */
    public int getY() {
        return this.y;
    }

    /**
     * @param y
     */
    public void setY(final int y) {
        this.y = y;
    }

}
