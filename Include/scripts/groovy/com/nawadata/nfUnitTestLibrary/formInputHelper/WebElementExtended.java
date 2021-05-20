package com.nawadata.nfUnitTestLibrary.formInputHelper;

import com.nawadata.nfUnitTestLibrary.WebDriverWrapper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementExtended {
    private WebDriver driver;
    private WebDriverWrapper driverExt;
    private WebElement element;

    public WebElementExtended(WebDriver _driver, WebDriverWrapper _driverExt, WebElement _element) {
        this.driver = _driver;
        this.driverExt = _driverExt;
        this.element = _element;
    }

    public ShouldBeClass ShouldBe() {
        return new ShouldBeClass(this.driver, this.driverExt, this.element);
    }

    public WebElement GetWebElement() {
        return this.element;
    }
}