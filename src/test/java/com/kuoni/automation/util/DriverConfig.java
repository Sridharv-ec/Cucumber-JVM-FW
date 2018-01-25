package com.kuoni.automation.util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.security.krb5.internal.crypto.Des;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverConfig {

    WebDriver driver = null;
    private static URL serverUrl;
    public String platform = System.getProperty("platform");
    protected String device = System.getProperty("device");
    WebDriverUtil webDriverUtil = new WebDriverUtil(driver);
    public WebDriver getDriver(String device) throws InterruptedException, MalformedURLException {

        if(device.equalsIgnoreCase("firfox")){
            DesiredCapabilities caps = DesiredCapabilities.firefox();
            caps.setCapability("acceptInsecureCerts", true);
            DesiredCapabilities capabilities = new FirefoxOptions().addTo(caps);
            System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//src//test//resources//webdriver//geckodriver.exe");
            driver = new FirefoxDriver();

            //((FirefoxDriver)driver).manage().window().maximize();
            driver.manage().window().maximize();

        }else if(device.equalsIgnoreCase("chrome")){
            ChromeOptions chromeOptions = new ChromeOptions();
            //if(System.getProperty("os.name") .toLowerCase().indexOf("win")>0){
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\webdriver\\chromedriver.exe");
            System.out.println(System.getProperty("user.dir")+"\\src\\test\\resources\\webdriver\\chromedriver.exe");
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-popup-blocking");
            Thread.sleep(2000);
            driver = new ChromeDriver(chromeOptions);
            //((FirefoxDriver)driver).manage().window().maximize();
            driver.manage().window().maximize();
        }
        else if( device.equalsIgnoreCase("onePlus2")){
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("automationName",
                    "Appium");
            capabilities.setCapability("version",
                    "6.0.1");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
                    "Android");
            capabilities.setCapability("udid",
                    "bb05b20b");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
                    "OnePlus2");
            capabilities.setCapability("deviceReadyTimeout",
                    "5");
            //capabilities.setCapability("app", app.getAbsolutePath());
            //capabilities.setCapability(MobileCapabilityType.DEVICE_READY_TIMEOUT,
            //        CommonUtils.DEVICE_READY_TIMEOUT);
            /*capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
                    "com.mmt.travel.app.home.ui.SplashActivity");
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
                    "com.makemytrip");*/
            capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
            //**********Set mobile capabilites ends************************

            serverUrl = new URL("http://127.0.0.1:4723/wd/hub");
            System.out.println(serverUrl);
            driver = new AndroidDriver(serverUrl, capabilities);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }

        return driver;
    }
}
