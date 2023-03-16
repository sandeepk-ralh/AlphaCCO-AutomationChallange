package org.example.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class OutLookEmailPage extends BasePage {

    @FindBy(css = "div > nav > ul > li:nth-child(2)")
    protected WebElement signInButton;

    @FindBy(css = "input[name='loginfmt']")
    protected WebElement emailInputArea;

    @FindBy(id = "idSIButton9")
    protected WebElement nextOrSigninButton;

    @FindBy(css = "input[name='passwd']")
    protected WebElement passwordInputArea;

    @FindBy(id = "idBtn_Back")
    protected WebElement signedInConfirmationButton;

    @FindBy(css = "div[class='ONAp_'] > div > div > div > div > div:nth-child(1)")
    protected WebElement firstInboxMessage;

    @FindBy(css = "tr:nth-child(2) > td:nth-child(1) > div:nth-child(1) > div:nth-child(2)")
    protected WebElement getPassword;

    @FindBy(css = "div:nth-child(2) > table > tbody > tr > td > a")
    protected WebElement loginBtn;

    public OutLookEmailPage(WebDriver driver) {
        super(driver);
    }

    public String getTempPasswordOutlookMail(String outlookUrl, String outlookEmail, String outlookPwd) {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        driver.get(outlookUrl);
        clickOnElement(signInButton);
        enterText(emailInputArea, outlookEmail);
        clickOnElement(nextOrSigninButton);
        enterText(passwordInputArea, outlookPwd);
        clickOnElement(nextOrSigninButton);
        clickOnElement(signedInConfirmationButton);
        pauseForAWhile(4);
        refreshPage();
        waitUntilElementToBeClickable(firstInboxMessage);
        clickOnElement(firstInboxMessage);
        waitForPageToLoad();
        String elementText = getPassword.getText();
        String[] lines = elementText.split("\n");
        String single = lines[1].trim();
        int indexOfSpace = single.indexOf(" ");
        String tempPassword = single.substring(indexOfSpace + 1);
        clickOnElement(loginBtn);
        moveToAnotherTab(driver);
        return tempPassword;
    }
}
