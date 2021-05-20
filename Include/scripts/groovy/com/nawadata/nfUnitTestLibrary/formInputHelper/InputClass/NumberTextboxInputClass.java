package com.nawadata.nfUnitTestLibrary.formInputHelper.InputClass;

import com.nawadata.nfUnitTestLibrary.WebDriverWrapper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NumberTextboxInputClass extends BasicInputClass {
    public NumberTextboxInputClass(WebDriver _driver, WebDriverWrapper _driverExt, WebElement _element) {
        this.driver = _driver;
        this.driverExt = _driverExt;
        this.element = _element;
        this.componentID = _element.getAttribute("data-componentid");
    }

    public NumberTextboxInputClass SendText(String text) {
        return SendText(text, false);
    }

    public NumberTextboxInputClass SendText(String text, Boolean ignoreErrors) {
        WebElement element = this.element;

        int intText;
        try {
            intText = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Can't read text argument as number. Please recheck your input", e);
        }

        try {
            int valueMin = Integer.parseInt(element.getAttribute("aria-valuemin"));
            
            if (intText < valueMin) {
                throw new IllegalArgumentException("The text length on the argument is smaller than the input minimum. If this is an expected behavior, please add a boolean on the ignoreErrors argument");
            }
        } catch (Exception e) { }

        try {
            int valueMax = Integer.parseInt(element.getAttribute("aria-valuemax"));
            
            if (intText < valueMax) {
                throw new IllegalArgumentException("The text length on the argument is smaller than the input maximum. If this is an expected behavior, please add a boolean on the ignoreErrors argument");
            }
        } catch (Exception e) { }

        element.sendKeys(text);

        return this;
    }
}
