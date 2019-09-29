package com.monkey.core.task.mobile.element;

import com.monkey.core.task.AbstractTask;

public class ClearTask extends AbstractTask {

    @Override
    public void execute() {
        this.getElement().getWebElement().clear();
    }

    @Override
    public String getDescription() {
        return "clear on element [ " + this.getElement() + "]";
    }

    @Override
    public String getName() {
        return "ClearElement";
    }

}
