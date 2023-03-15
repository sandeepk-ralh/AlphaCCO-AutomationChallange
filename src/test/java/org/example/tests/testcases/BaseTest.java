package org.example.tests.testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.tests.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest extends ConfigReader {

    protected static WebDriver driver;

    protected static String baseURL;

    protected static String emailAlphaCCO;

    protected static String password;

    protected static String wrongEmail;

    protected static String wrongPassword;

    protected static String newPassword;

    protected static String outlookPassword;

    protected static String outlookUrl;

    protected static String targetBrowser;

    @BeforeSuite
    public void readConfigsBaseTest() {
        baseURL = ConfigReader.getBaseUrl();
        emailAlphaCCO = ConfigReader.getEmailAlphaCCO();
        password = ConfigReader.getPassword();
        wrongEmail = ConfigReader.getInvalidUsername();
        wrongPassword = ConfigReader.getInvalidPassword();
        newPassword = ConfigReader.getNewPassword();
        outlookPassword = ConfigReader.getOutlookPassword();
        outlookUrl = ConfigReader.getOutlookUrl();
    }

    @BeforeMethod
    public void driverSetup() {
        targetBrowser = System.getProperty("targetBrowser");
        if(targetBrowser == null) {
            targetBrowser = System.getenv("targetBrowser");
        }
        if (targetBrowser.equalsIgnoreCase("chrome") ) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(baseURL);
        } if (targetBrowser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(baseURL);
        }
    }

    @AfterMethod
    public void quitDriver() {
        try {
            driver.quit();
        } catch (org.openqa.selenium.WebDriverException e) {
            driver.quit();
        }
    }
}
