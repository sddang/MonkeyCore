package com.monkey.core.task.mobile.element;

import com.monkey.api.MonkeyExecutionContext;
import com.monkey.core.task.AbstractTask;
import io.appium.java_client.ios.IOSElement;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;


public class TypeTask extends AbstractTask {

    boolean clean = true;
    boolean iosSendKey;
    private String description;
    private String inputValue;

    /**
     * Execute the type action of the elements using the selenum implementation
     * The Type action of monkey is more improved
     */

    @Override
    public void execute() {
//        final String value = DataMapper.getSessionMapper().mapData(this.getElement().getInputValue());
        this.type();
    }

    private void clean(final WebElement element) {
        final boolean isEmpty = MonkeyExecutionContext.isIOS() ? (element.getText() != null)
                : (!element.getText().isEmpty());
        if (isEmpty) {
            element.clear();
        }
    }

    private void type() {
        WebElement element = this.getElement().getWebElement();
        this.description = "Type data :\"" + this.inputValue + "\" in the input \n[ " + this.getElement() + " ]";
        if (StringUtils.isBlank(this.inputValue)) {
            return;
        }
        if (this.clean) {
            this.clean(element);
        }
        if (MonkeyExecutionContext.isAndroid()) {
            element.sendKeys(this.inputValue);
        } else {
            final IOSElement iOSelement = (IOSElement) element;
            iOSelement.click();
            iOSelement.setValue(this.inputValue);
        }
    }

    public TypeTask setClean(final boolean clean) {
        this.clean = clean;
        return this;
    }

    public TypeTask setIosSendKey(final boolean iosSendKey) {
        this.iosSendKey = iosSendKey;
        return this;
    }

    public TypeTask setInputValue(String value) {
        this.inputValue = value;
        return this;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getName() {
        return "TypeValue";
    }

}
