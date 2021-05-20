package com.nawadata.nfUnitTestLibrary.formInputHelper;

import com.nawadata.nfUnitTestLibrary.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FillDateClass {
    public static void FillDate(WebDriver driver, WebDriverWrapper driverExt, WebElement dateField, LocalDate date) {
        LocalDate  randomDate = date;
        int randomYear = randomDate.getYear();
//        Month randomMonth = randomDate.getMonth();
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
        driverExt.Wait(java.time.Duration.ofMillis(400));
        
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
        driverExt.Wait(java.time.Duration.ofMillis(400));
        
        WebElement dateListParent = datePickerComponent.findElement(By.xpath("descendant::*[@data-ref='innerEl']/table/tbody"));
        dateListParent.findElement(By.xpath(String.format("descendant::*[contains(@class, 'x-datepicker-active')]/*[text()='%s']", randomDay))).click();
    }
}