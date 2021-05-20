package com.nawadata.nfUnitTestLibrary.formInputHelper;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import com.nawadata.nfUnitTestLibrary.WebDriverWrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// package nfUnitTestLibrary;
// import internal.GlobalVariable;
// import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint;
// import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase;
// import static com.kms.katalon.core.testdata.TestDataFactory.findTestData;
// import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject;
// import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject;

// import com.kms.katalon.core.annotation.Keyword;
// import com.kms.katalon.core.checkpoint.Checkpoint;
// import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords;
// import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords;
// import com.kms.katalon.core.model.FailureHandling;
// import com.kms.katalon.core.testcase.TestCase;
// import com.kms.katalon.core.testdata.TestData;
// import com.kms.katalon.core.testobject.TestObject;
// import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords;
// import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords;
// import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords;

public class FindDate {
    public static void FillDate(WebDriver driver, WebDriverWrapper driverExt, WebElement dateField, LocalDate date) {
        LocalDate  randomDate = date;
        int randomYear = randomDate.getYear();
        Month randomMonth = randomDate.getMonth();
        int randomDay = randomDate.getDayOfMonth();
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String randDate = formatter.format(randomDate);

        String ComponentID = dateField.getAttribute("data-componentid");

        driver.findElement(By.id(String.format("%s-trigger-picker", ComponentID))).click();

        WebElement datePickerComponent = driver.findElement(By.id(String.format("%s-picker", ComponentID)));

        datePickerComponent.findElement(By.xpath("descendant::*[@data-ref=\'middleBtnEl\']/descendant::*[@data-ref=\'btnInnerEl\']")).click();
        
        WebElement monthPickerComponent = datePickerComponent.findElement(By.xpath("*[contains(@id, 'monthpicker')]"));
        
        WebElement yearPickerComponent = monthPickerComponent.findElement(By.xpath("descendant::*[@data-ref='yearEl']"));
        WebElement yearPickerNavPrevComponent = yearPickerComponent.findElement(By.xpath("descendant::*[@data-ref='prevEl']"));
        WebElement yearPickerNavNextComponent = yearPickerComponent.findElement(By.xpath("descendant::*[@data-ref='nextEl']"));
        
        // Wait for the elements to be interactable
        driverExt.Wait(java.time.Duration.ofSeconds(1));
        
        while (true) {
            String earliestYearAvailableStr = yearPickerComponent.findElement(By.xpath("*/*[@class='x-monthpicker-item-inner']")).getText();
            int earliestYearAvailable = Integer.parseInt(earliestYearAvailableStr);
            
            if (randomYear < earliestYearAvailable) {
                yearPickerNavPrevComponent.click();
                continue;
            }
            
            if (randomYear >= earliestYearAvailable + 10) {
                yearPickerNavNextComponent.click();
                continue;
            }
            
            break;
        }
        
        yearPickerComponent.findElement(By.xpath(String.format("descendant::*[text()='%s']", randomYear))).click();
        
        WebElement monthListParent = datePickerComponent.findElement(By.xpath("*[contains(@id, 'monthpicker')]/descendant::*[@data-ref='monthEl']"));
        monthListParent.findElement(By.xpath(String.format("descendant::*[text()='%s']", DateTimeFormatter.ofPattern("MMM").format(randomDate)))).click();
        
        datePickerComponent.findElement(By.xpath("*[contains(@id, 'monthpicker')]/descendant::span[text()='OK']")).click();
        
        // Wait for the elements to be interactable
        driverExt.Wait(java.time.Duration.ofSeconds(1));
        
        WebElement dateListParent = datePickerComponent.findElement(By.xpath("descendant::*[@data-ref='innerEl']/table/tbody"));
        dateListParent.findElement(By.xpath(String.format("descendant::*[contains(@class, 'x-datepicker-active')]/*[text()='%s']", randomDay))).click();
    }
}