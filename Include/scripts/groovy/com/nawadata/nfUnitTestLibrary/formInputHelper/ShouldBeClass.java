package com.nawadata.nfUnitTestLibrary.formInputHelper;

import com.nawadata.nfUnitTestLibrary.WebDriverWrapper;
import com.nawadata.nfUnitTestLibrary.formInputHelper.Exceptions.IncorrectTypeException;
import com.nawadata.nfUnitTestLibrary.formInputHelper.InputClass.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShouldBeClass {
    private WebDriver driver;
    private WebDriverWrapper driverExt;
    private WebElement element;

    public ShouldBeClass(WebDriver _driver, WebDriverWrapper _driverExt, WebElement el) {
        this.driver = _driver;
        this.driverExt = _driverExt;
        this.element = el;
    }

    public RadioInputClass Radio() throws IncorrectTypeException {
        try {
            WebElement RadioInputEl = this.element.findElement(By.xpath("ancestor::*[@role='radiogroup']"));
            return new RadioInputClass(this.driver, this.driverExt, RadioInputEl);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            throw new IncorrectTypeException("Input element is not a radio", e);
        }
    }

    public DropdownInputClass Dropdown() throws IncorrectTypeException {
        try {
            WebElement DropdownInputEl = this.element.findElement(By.xpath("../input[@role='combobox' and contains(@aria-owns,'-picker-listEl') and @aria-autocomplete='list']"));
            return new DropdownInputClass(this.driver, this.driverExt, DropdownInputEl);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            throw new IncorrectTypeException("Input element is not a dropdown", e);
        }
    }

    public TextboxInputClass Textbox() throws IncorrectTypeException {
        try {
            WebElement TextboxInputEl = this.element.findElement(By.xpath("(../input[@role='textbox' and not(@aria-owns)] | ../textarea)"));
            return new TextboxInputClass(this.driver, this.driverExt, TextboxInputEl);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            throw new IncorrectTypeException("Input element is not a textbox", e);
        }
    }

    public NumberTextboxInputClass NumberTextbox() throws IncorrectTypeException {
        try {
            WebElement NumberTextboxInputEl = this.element.findElement(By.xpath("../input[@role='spinbutton']"));
            return new NumberTextboxInputClass(this.driver, this.driverExt, NumberTextboxInputEl);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            throw new IncorrectTypeException("Input element is not a number", e);
        }
    }

    public DateInputClass Date() throws IncorrectTypeException {
        try {
            WebElement DateInputEl = this.element.findElement(By.xpath("../input[@role='combobox' and contains(@aria-owns,'-picker-eventEl') and @aria-autocomplete='none']"));
            return new DateInputClass(this.driver, this.driverExt, DateInputEl);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            throw new IncorrectTypeException("Input element is not a date", e);
        }
    }
}
