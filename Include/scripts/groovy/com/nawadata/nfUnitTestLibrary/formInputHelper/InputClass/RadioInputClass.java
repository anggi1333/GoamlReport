package com.nawadata.nfUnitTestLibrary.formInputHelper.InputClass;

import java.util.List;

import com.nawadata.nfUnitTestLibrary.Extensions;
import com.nawadata.nfUnitTestLibrary.WebDriverWrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RadioInputClass extends BasicInputClass {
    public RadioInputClass(WebDriver _driver, WebDriverWrapper _driverExt, WebElement _element) {
        this.driver = _driver;
        this.driverExt = _driverExt;
        this.element = _element;
    }

    private List<WebElement> GetOptions() {
        return this.element.findElements(By.xpath("descendant::td"));
    }

    public RadioInputClass SelectRandomElement() {
        WebElement selectedOption = ((WebElement)Extensions.GetRandomElement(GetOptions().toArray())).findElement(By.xpath("descendant::input"));
        selectedOption.click();

        return this;
    }

    public RadioInputClass SelectElementOnIndex(int index) {
        List<WebElement> options = GetOptions();
        if (index >= options.size() || index < 0) {
            throw new IndexOutOfBoundsException("This function tries to access index " + index + " from an array of " + options.size() + " elements.");
        }

        WebElement selectedOption = options.get(index).findElement(By.xpath("descendant::input"));
        selectedOption.click();

        return this;
    }
}
