package com.nawadata.nfUnitTestLibrary.formInputHelper.InputClass;

import com.nawadata.nfUnitTestLibrary.WebDriverWrapper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextboxInputClass extends BasicInputClass {
    public TextboxInputClass(WebDriver _driver, WebDriverWrapper _driverExt, WebElement _element) {
        this.driver = _driver;
        this.driverExt = _driverExt;
        this.element = _element;
        this.componentID = _element.getAttribute("data-componentid");
    }

    public TextboxInputClass SendText(String text) {
        return SendText(text, false);
    }

    public TextboxInputClass SendText(String text, Boolean ignoreErrors) {
        WebElement element = this.element;

        String maxLengthStr = element.getAttribute("maxlength");

        if (maxLengthStr != null){
            int maxLength = Integer.parseInt(maxLengthStr);

            if (text.length() > maxLength && !ignoreErrors) {
                throw new IllegalArgumentException("The text length on the argument is bigger than the input maxlength. \nIf this is an expected behavior, please add a boolean on the ignoreErrors argument");
            }
        }

        element.sendKeys(text);

        return this;
    }
}
