package com.nawadata.nfUnitTestLibrary;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverWrapper {
    private WebDriver driver;
    
    public WebDriverWrapper(
        WebDriver _driver
    ) {
        this.driver = _driver;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void setDriver(WebDriver _driver) {
        this.driver = _driver;
    }

    public void Wait(Duration delay)
    {
        java.time.LocalDateTime now = LocalDateTime.now();
        org.openqa.selenium.support.ui.WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.pollingEvery(Duration.ofMillis(100));
        wait.until(wd -> {
            return Duration.between(now, LocalDateTime.now()).minus(delay).compareTo(Duration.ZERO) > 0;
        });
    }

    public void Wait(Long delay)
    {
        Wait(Duration.ofSeconds(delay));
    }

    public WebElement WaitUntil(Function<WebDriver, WebElement> func, Long timeout) {
        return new WebDriverWait(driver, timeout).until(func);
    }

    public WebElement WaitUntil(Function<WebDriver, WebElement> func) {
        return WaitUntil(func, Extensions.defaultTimeout.getSeconds());
    }

    public void HandleElementTimeoutException(TimeoutException ex, By by) {
        org.openqa.selenium.WebElement element = driver.findElement(by);

        if (element == null) throw new NoSuchElementException(null);
        if (!element.isDisplayed()) throw new ElementNotVisibleException(null);
        if (!element.isEnabled()) throw new ElementNotInteractableException(null);

        throw ex;
    }

    public WebElement WaitUntilVisible(By by)
    {
        try 
        {
            return new WebDriverWait(driver, Extensions.defaultTimeout_inSeconds).until(ExpectedConditions.visibilityOfElementLocated(by));
        } 
        catch (TimeoutException ex)
        {
            HandleElementTimeoutException(ex, by);
            return null;
        }
        catch (Exception e) 
        {
            throw e;
        }
    }

    public Boolean WaitUntilInvisible(By by)
    {
        try 
        {
            return new WebDriverWait(driver, Extensions.defaultTimeout_inSeconds).until(ExpectedConditions.invisibilityOfElementLocated(by));
        } 
        catch (TimeoutException ex)
        {
            HandleElementTimeoutException(ex, by);
            return null;
        }
        catch (Exception e) 
        {
            throw e;
        }
    }

    JavascriptExecutor jsExec;
    public JavascriptExecutor GetJSExecutor() {
        if (jsExec == null) {
            if (driver instanceof JavascriptExecutor) {
                jsExec = (JavascriptExecutor)driver;
            } else {
                throw new IllegalStateException("This driver does not support JavaScript!");
            }
        }
        
        return jsExec;
    }

    public void ExecFillTextbox(Object[]... TextboxValuePair) 
    {
        jsExec.executeScript("for(let[e,a]of arguments)e.value=a;", TextboxValuePair); // Pray to god this works
        org.openqa.selenium.WebElement randomElement = (WebElement)TextboxValuePair[0][0];
        jsExec.executeScript("arguments[0].scrollIntoView(!0);", randomElement);
        randomElement.sendKeys("a" + Keys.BACK_SPACE);
    }

    public WebDriver WaituntilFrameLoads(By by) {
        try {
            return new WebDriverWait(driver, Extensions.defaultTimeout_inSeconds).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
        } catch (Exception e) {
            throw e;
        }
    }

    public void ScrollToElement(WebElement element)
    {
        new Actions(driver).moveToElement(element).perform();
    }
}