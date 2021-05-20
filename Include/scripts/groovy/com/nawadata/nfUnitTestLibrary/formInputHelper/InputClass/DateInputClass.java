package com.nawadata.nfUnitTestLibrary.formInputHelper.InputClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.nawadata.nfUnitTestLibrary.WebDriverWrapper;
import com.nawadata.nfUnitTestLibrary.formInputHelper.FillDateClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DateInputClass extends BasicInputClass {
    public DateInputClass(WebDriver _driver, WebDriverWrapper _driverExt, WebElement _element) {
        this.driver = _driver;
        this.driverExt = _driverExt;
        this.element = _element;
        this.componentID = _element.getAttribute("data-componentid");
    }

    public DateInputClass SendText(String text) {
        return SendText(text, false);
    }

    public DateInputClass SendText(String text, Boolean ignoreErrors) {
        WebElement element = this.element;

        LocalDate dateText;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            dateText = LocalDate.parse(text, formatter);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Text argument didn't follow the pattern of 'dd-MM-yyyy'. Please recheck your input", e);
        }

        FillDateClass.FillDate(driver, driverExt, element, dateText);

        return this;
    }
}
