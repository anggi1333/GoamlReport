package com.nawadata.nfUnitTestLibrary.formInputHelper.InputClass;

import com.nawadata.nfUnitTestLibrary.WebDriverWrapper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface BasicInputInterface {
    public void Execute(WebDriver driver, WebDriverWrapper driverExt, WebElement element, Object[] args);
}
