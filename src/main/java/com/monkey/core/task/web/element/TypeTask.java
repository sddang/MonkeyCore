package com.monkey.core.task.web.element;

import com.monkey.api.Wait;
import com.monkey.core.task.AbstractTask;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;

public class TypeTask extends AbstractTask {

    private String description;
    private String inputValue;

    /**
     * Execute the type action of the elements using the selenium implementation
     * The Type action of monkey is more improved
     */
    @Override
    public void execute() {
//        String getPreparedData = DataMapper.getSessionMapper().mapData(this.getElement().getInputValue());
        type();
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getName() {
        return "TypeValue";
    }

    public TypeTask setInputValue(String value) {
        this.inputValue = value;
        return this;
    }

    private void type() {
        WebElement element = this.getElement().getWebElement();

        String elementValue = element.getText() != null && !element.getText().equals("") ? element.getText()
                : element.getAttribute("value");
        this.description = "Type data : \"" + this.inputValue + "\" in the input \n[ " + this.getElement() + " ]";
        if (!StringUtils.isBlank(elementValue)) {
            element.clear();
        }
        if (!StringUtils.isBlank(this.inputValue)) {
            element.sendKeys(this.inputValue);
            if (this.inputValue.length() > 300) {
                Wait.implicitWait(1);
            }
        }
    }

}
