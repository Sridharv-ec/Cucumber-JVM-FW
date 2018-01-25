package com.kuoni.automation.pageobjects;

import com.kuoni.automation.util.WebDriverUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends WebDriverUtil {
    private Logger logger = Logger.getLogger("BasePage");
    public BasePage(WebDriver driver) {
        super(driver);
    }
    public String platform = System.getProperty("platform");
    private By profile = By.xpath(".//a[@class='site-header__icon-btn icon icon-profile-header']");
    private By logout = By.xpath(".//div[@class='site-header__dropdown-navigation user-settings']//a[@class='btn--blue']");
    private By menu = By.xpath(".//a[@class='site-header__mobile-menu-btn icon icon-menu-burger");

    public void logOut(){
        PropertyConfigurator.configure("Log4j.properties");
        if(platform.equalsIgnoreCase("mobile")){
            waitForElementDisplayed(menu);
            clickElement(menu);
            waitForElementDisplayed(By.xpath(".//a[@class='link-login mobile-menu' and contains(text(),'Profile')]"));
            clickElement(By.xpath(".//a[@class='link-login mobile-menu' and contains(text(),'Profile')]"));
            waitForElementDisplayed(By.xpath(".//li[@class='site-header__collapsing-menu mm-opened']//a[@class='btn--blue']"));
            clickElement(By.xpath(".//li[@class='site-header__collapsing-menu mm-opened']//a[@class='btn--blue']"));
        }else {
            waitForElementDisplayed(profile);
            clickElement(profile);
            logger.debug("clicked on Profile from header");
            waitForElementDisplayed(logout);
            logger.info("clicked on logout");
            clickElement(logout);
        }
    }
    public void clickOnCloseLightBox() throws InterruptedException{
        Thread.sleep(4000);
        By closeBy = By.xpath(".//div[@class='lightbox__container']//button[@class='lightbox__close-button icon icon--after icon-cross-thin--after']");
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.presenceOfElementLocated(closeBy));
        driver.findElement(closeBy).click();
        logger.debug("clicked on close from lightbox");
    }
}