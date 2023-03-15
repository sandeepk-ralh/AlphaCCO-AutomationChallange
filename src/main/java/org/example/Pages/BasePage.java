package org.example.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class BasePage {

    protected WebDriver driver;

    protected WebDriverWait webDriverWait;

    protected JavascriptExecutor jse;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        jse = (JavascriptExecutor) driver;
    }

    public void clickOnElement(WebElement webElement) {
        waitUntilElementToBeClickable(webElement);
        webElement.click();
    }

    public void waitUntilElementToBeClickable(WebElement webElement) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void enterText(WebElement webElement, String text) {
        waitUntilElementToBeClickable(webElement);
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void waitForVisibilityOfElementCss(String cssPath) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssPath)));
    }

    public void waitForPageToLoad() {
        FluentWait<WebDriver> driverWait = new WebDriverWait(driver, Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(250));

        try {
            driverWait.until(isTrue -> ((JavascriptExecutor) driver).executeScript("return document.readyState")
                    .equals("complete"));
        } catch (WebDriverException e) {
            System.out.println("Driver exception occured. " + e.getMessage());
        }
    }

    public void pauseForAWhile(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void scrollDown(int numberOfTimes) {
        jse.executeScript("window.scrollBy(0,arguments[0]*350)", numberOfTimes);
    }

    public void moveToAnotherTab(WebDriver driver) {
        String currentWindowHandle = driver.getWindowHandle();
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        // Switch to the new window
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }
}

