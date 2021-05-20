package com.nawadata.nfUnitTestLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperFunction {
    public static WebElement FindInputBoxFromLabel(WebDriver driver, String label) {
        return driver.findElement(By.xpath("//span[text() = '" + label + "']/ancestor::label/../descendant::*[@data-ref='inputEl']"));
    }
}