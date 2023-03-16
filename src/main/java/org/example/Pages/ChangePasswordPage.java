package org.example.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChangePasswordPage extends BasePage {

    @FindBy(css = "div.nav-item:nth-child(4)")
    protected WebElement userProfileButton;

    @FindBy(id = "lnkChgPassword")
    protected WebElement changePasswordButton;

    @FindBy(id = "input-7")
    protected WebElement previousPasswordInput;

    @FindBy(id = "input-11")
    protected WebElement newPasswordInput;

    @FindBy(id = "input-19")
    protected WebElement newPasswordConfirmation;

    @FindBy(css = "#app > div > div > form > div > button")
    protected WebElement updatePasswordButton;

    @FindBy(css = "#loginForm > div > div > div.panel-heading > div > h3")
    protected WebElement loginMessage;

    public ChangePasswordPage(WebDriver driver) {
        super(driver);
    }

    public boolean changePasswordFromUserProfile(String oldPassword, String newPassword, String confirmPassword) {
        pauseForAWhile(3);
        waitUntilElementToBeClickable(userProfileButton);
        clickOnElement(userProfileButton);
        clickOnElement(changePasswordButton);
        enterText(previousPasswordInput, oldPassword);
        enterText(newPasswordInput, newPassword);
        enterText(newPasswordConfirmation, confirmPassword);
        clickOnElement(updatePasswordButton);
        return true;
    }

    public String getLoginText() {
        waitForVisibilityOfElementCss("#loginForm > div > div > div.panel-heading > div > h3");
        return loginMessage.getText();
    }
}
