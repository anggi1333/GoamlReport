package com.nawadata.nfUnitTestLibrary.formInputHelper.InputClass;

import java.util.List;

import com.nawadata.nfUnitTestLibrary.Extensions;
import com.nawadata.nfUnitTestLibrary.WebDriverWrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DropdownInputClass extends BasicInputClass {
    public DropdownInputClass(WebDriver _driver, WebDriverWrapper _driverExt, WebElement _element) {
        this.driver = _driver;
        this.driverExt = _driverExt;
        this.element = _element;
        this.componentID = _element.getAttribute("data-componentid");
    }

    private List<WebElement> GetOptions() {
        return driver.findElements(By.xpath("//ul[@id = '" + this.componentID + "-picker-listEl']/li"));
    }

    public WebElement PickOption(int index) {
        List<WebElement> options = GetOptions();
        WebElement selectedOption;

        if (index >= options.size()) {
            throw new IndexOutOfBoundsException("This function tries to access index " + index + " from an array of " + options.size() + " elements.");
        }

        if (index < 0) {
            selectedOption = (WebElement)Extensions.GetRandomElement(options.toArray());
        } else {
            selectedOption = options.get(index);
        }

        driverExt.ScrollToElement(selectedOption);
        return selectedOption;
    }

    public DropdownInputClass SelectRandomElement() {
        driver.findElement(By.id(this.componentID + "-trigger-picker")).click();
        driverExt.WaitUntilVisible(By.id(this.componentID + "-picker-listEl"));

        WebElement selectedOption = PickOption(-1);
        selectedOption.click();

        return this;
    }

    public DropdownInputClass SelectElementOnIndex(int index) {
        driver.findElement(By.id(this.componentID + "-trigger-picker")).click();
        driverExt.WaitUntilVisible(By.id(this.componentID + "-picker-listEl"));

        WebElement selectedOption = PickOption(index);
        driverExt.ScrollToElement(selectedOption);
        selectedOption.click();

        return this;
    }

    public DropdownInputClass ClearInput() {
        try {
            driver.findElement(By.id(this.componentID + "-trigger-_trigger1")).click();
        } catch (Exception e) {
            //TODO: handle exception
        }

        return this;
    }

    public DropdownInputClass SelectElementFromText(String text) {
        driver.findElement(By.id(this.componentID + "-trigger-picker")).click();
        driverExt.WaitUntilVisible(By.id(this.componentID + "-picker-listEl"));

        List<WebElement> options = this.element.findElements(By.xpath("//ul[@id = '" + this.componentID + "-picker-listEl']/li[contains(text(), '" + text + "')]"));

        if (options.isEmpty()) 
            throw new IllegalArgumentException("Element search returns empty.");
        WebElement selected = options.get(0);

        System.out.println(selected.getAttribute("id"));

        driverExt.ScrollToElement(selected);
        selected.click();

        return this;
    }
}
