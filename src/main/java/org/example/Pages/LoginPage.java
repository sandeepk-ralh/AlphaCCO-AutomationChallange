package org.example.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class LoginPage extends BasePage {

    // Login releated selectors
    @FindBy(css = "input[id='MainContent_txtEmail']")
    protected WebElement emailInput;

    @FindBy(css = "input[id='MainContent_txtPassword']")
    protected WebElement passwordInput;

    @FindBy(id = "MainContent_btnLogin")
    protected WebElement loginButton;

    @FindBy(id = "Dashboard")
    protected WebElement dashboardMenu;

    @FindBy(className = "text-danger")
    protected WebElement loginStatusText;

    // Forgot Password releated selectors
    @FindBy(id = "MainContent_btnForgotPassword")
    protected WebElement forgotPasswordButton;

    @FindBy(id = "MainContent_txtModalForgotPasswordUserName")
    protected WebElement emailForForgotPasswordInput;

    @FindBy(id = "MainContent_btnModalForgotPasswordSendRequest")
    protected WebElement requestResetButton;

    @FindBy(css = "div[id='ModalForgotPasswordConfirm'] > div > div > div > h3")
    protected WebElement forgotPasswordConfirmationMessage;

    @FindBy(id = "input-7")
    protected WebElement previousPasswordInput;

    @FindBy(id = "input-11")
    protected WebElement newPasswordInput;

    @FindBy(id = "input-19")
    protected WebElement newPasswordConfirmation;

    @FindBy(css = "#app > div > div > form > div > button")
    protected WebElement updatePasswordButton;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void doLogin(String email, String password) {
        enterText(emailInput, email);
        enterText(passwordInput, password);
        clickOnElement(loginButton);
    }

    public void requestForgotPassword(String email) {
        clickOnElement(forgotPasswordButton);
        pauseForAWhile(1);
        enterText(emailForForgotPasswordInput, email);
        clickOnElement(requestResetButton);
    }

    public void changePasswordWithTempPassword(String email, String tempPassword, String newPassword, String confirmPassword) {
        pauseForAWhile(4);
        enterText(emailInput, email);
        enterText(passwordInput, tempPassword);
        clickOnElement(loginButton);
        enterText(previousPasswordInput, tempPassword);
        enterText(newPasswordInput, newPassword);
        enterText(newPasswordConfirmation, confirmPassword);
        clickOnElement(updatePasswordButton);
    }

    public boolean isDashboardVisible() {
        waitForVisibilityOfElementCss("");
        return dashboardMenu.isDisplayed();
    }

    public String getErrorMessage() {
        return loginStatusText.getText();
    }
}
