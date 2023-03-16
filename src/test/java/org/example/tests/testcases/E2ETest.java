package org.example.tests.testcases;

import org.example.Pages.ChangePasswordPage;
import org.example.Pages.LoginPage;
import org.example.Pages.MyProfilePage;
import org.example.Pages.OutLookEmailPage;
import org.example.tests.utils.RandomPasswordGenerator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class E2ETest extends BaseTest {

    @Test(priority = 1)
    public void wrongEmailLoginTest() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.doLogin(wrongEmail, password);
        Assert.assertEquals(loginPage.getErrorMessage(), " Invalid user id or password");
    }

    @Test(priority = 2)
    public void wrongPasswordLoginTest() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.doLogin(emailAlphaCCO, wrongPassword);
        Assert.assertEquals(loginPage.getErrorMessage(), " Invalid user id or password");
    }

    @Test(priority = 3)
    public void positiveLoginTest() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.doLogin(emailAlphaCCO, password);
        Assert.assertTrue(loginPage.isDashboardVisible());
    }

    @Test(priority = 4)
    public void goToProfileTest() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.doLogin(emailAlphaCCO, password);
        MyProfilePage myProfilePage = PageFactory.initElements(driver, MyProfilePage.class);
        myProfilePage.goToUserProfile();
        Assert.assertEquals(myProfilePage.getProfileTitle(), "MY PROFILE");
    }

    @Test(priority = 5)
    public void changePasswordTest() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.doLogin(emailAlphaCCO, password);
        String newPassword = RandomPasswordGenerator.generateRandomPassword();
        ChangePasswordPage changePasswordPage = PageFactory.initElements(driver, ChangePasswordPage.class);
        Assert.assertTrue(changePasswordPage.changePasswordFromUserProfile(password, newPassword));
        RandomPasswordGenerator.updatePasswordInConfigFile(newPassword);
        Assert.assertEquals(changePasswordPage.getLoginText(), "PLEASE LOG IN");
    }

    @Test(priority = 6)
    public void forgotPasswordTest() {
        LoginPage forgotPassword = PageFactory.initElements(driver, LoginPage.class);
        forgotPassword.requestForgotPassword(emailAlphaCCO);
        OutLookEmailPage outLookEmailPage = PageFactory.initElements(driver, OutLookEmailPage.class);
        String tempPassword = outLookEmailPage.getTempPasswordOutlookMail(outlookUrl, emailAlphaCCO, outlookPassword);
        String newPassword = RandomPasswordGenerator.generateRandomPassword();
        Assert.assertTrue(forgotPassword.changePasswordWithTempPassword(emailAlphaCCO, tempPassword, newPassword));
        RandomPasswordGenerator.updatePasswordInConfigFile(newPassword);
        ChangePasswordPage changePasswordPage = PageFactory.initElements(driver, ChangePasswordPage.class);
        Assert.assertEquals(changePasswordPage.getLoginText(), "PLEASE LOG IN");
    }
}
