package org.example.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends BasePage {

    @FindBy(css = "div.nav-item:nth-child(4)")
    protected WebElement userProfileButton;

    @FindBy(css = "div > a:nth-child(4)")
    protected WebElement editProfileButton;

    @FindBy(className = "panel-title")
    protected WebElement profileTitle;

    public MyProfilePage(WebDriver driver) {
        super(driver);
    }

    public void goToUserProfile() {
        pauseForAWhile(3);
        clickOnElement(userProfileButton);
        clickOnElement(editProfileButton);
    }

    public String getProfileTitle() {
        return profileTitle.getText();
    }
}
