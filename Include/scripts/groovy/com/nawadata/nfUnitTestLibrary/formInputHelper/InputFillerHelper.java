package com.nawadata.nfUnitTestLibrary.formInputHelper;

import com.nawadata.nfUnitTestLibrary.WebDriverWrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InputFillerHelper {
    private WebDriver driver;
    private WebDriverWrapper driverExt;
    private WebElement parentElement;

    public InputFillerHelper(WebDriver _driver) {
        this.driver = _driver;
        this.driverExt = new WebDriverWrapper(_driver);
    }

    public InputFillerHelper(WebDriver _driver, WebDriverWrapper _driverExt) {
        this.driver = _driver;
        this.driverExt = _driverExt;
    }

    public InputFillerHelper setParentElement(WebElement parentElement) {
        this.parentElement = parentElement;
        return this;
    }

    /**
     * 
     * @param driver
     * @param label
     * @return
     * 
     * @deprecated static function on this class is not supported anymore. Use the
     *             non-static function instead.
     */
    @Deprecated
    public static WebElement GetInputFromLabel(WebDriver driver, String label) {
        return driver.findElement(
                By.xpath("//span[text() = '" + label + "']/ancestor::label/../descendant::*[@data-ref='inputEl']"));
    }

    public WebElementExtended GetInputFromLabel(String label) {
        Boolean haveParent = this.parentElement != null;
        return new WebElementExtended(this.driver, this.driverExt,
                (haveParent ? this.parentElement : this.driver)
                        .findElement(By.xpath((haveParent ? "descendant::" : "//") + "span[text() = '" + label
                                + "']/ancestor::label/../descendant::*[@data-ref='inputEl']")));
    }

    public InputFillerHelper GetWindowFromTitle(String title) {
        Boolean haveParent = this.parentElement != null;
        return new InputFillerHelper(this.driver, this.driverExt).setParentElement(
                (haveParent ? this.parentElement : this.driver).findElement(By.xpath((haveParent ? "descendant::"
                        : "//") + "div[text()='" + title
                        + "' and @data-ref='textEl']/ancestor::*[contains(@class, 'x-window x-layer x-window-default')]")));
    }

    public InputFillerHelper GetGroupFromTitle(String title) {
        Boolean haveParent = this.parentElement != null;
        return new InputFillerHelper(this.driver, this.driverExt)
                .setParentElement((haveParent ? this.parentElement : this.driver)
                        .findElement(By.xpath((haveParent ? "descendant::" : "//") + "div[text()='" + title
                                + "']//ancestor::div[contains(@class, 'x-panel x-panel-default')]")));
    }

    public void ClickButtonWithText(String text) {
        Boolean haveParent = this.parentElement != null;
        (haveParent ? this.parentElement : this.driver)
                .findElement(By.xpath((haveParent ? "descendant::" : "//") + "span[text()='" + text + "']/ancestor::a"))
                .click();
    }
}
