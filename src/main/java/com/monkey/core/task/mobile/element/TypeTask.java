

package com.monkey.core.task.mobile.element;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;

import com.monkey.api.MonkeyExecutionContext;
import com.monkey.core.task.AbstractTask;
import com.monkey.services.data.DataMapper;

import io.appium.java_client.ios.IOSElement;


public class TypeTask extends AbstractTask {

    boolean clean = true;
    boolean iosSendKey;
    private String description;

    /**
     * Execute the type action of the elements using the selenum implementation
     * The Type action of monkey is more improved
     */

    @Override
    public void execute() {
        final String value = DataMapper.getSessionMapper().mapData(this.getElement().getInputValue());
        final WebElement element = this.getElement().getWebElement();
        this.description = "Type data :\"" + value + "\" in the input [ " + this.getElement() + " ]";
        if (StringUtils.isBlank(value))
            return;

        if (this.clean)
            this.clean(element);
        this.type(element, value, this.iosSendKey);
    }

    private void clean(final WebElement element) {
        final boolean isEmpty = MonkeyExecutionContext.isIOS() ? (element.getText() != null)
                : (!element.getText().isEmpty());
        if (isEmpty) {
            element.clear();
        }
    }

    private void type(final WebElement element, final String value, final boolean iosSendKey) {
        if (MonkeyExecutionContext.isAndroid() || iosSendKey) {
            element.sendKeys(value);
        } else {
            final IOSElement iOSelement = (IOSElement) element;
            iOSelement.click();
            iOSelement.setValue(value);
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

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getName() {
        return "TypeValue";
    }

}
