package com.kuoni.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class TravelerPage extends BasePage {
    public TravelerPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToTravelerSite(String url){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(url);
    }

    public void clickOnRegisterHere(){
        By registerBy = By.cssSelector("div[class='login__terms']>:first-child");
        waitForElementDisplayed(registerBy);
        clickElement(registerBy);
    }

    public void enterEmailAddress(String emailId){
        By emailBy = By.xpath(".//div[@class='lightbox__content']//input[@placeholder='Email Address']");
        waitForElementDisplayed(emailBy);
        enterText(emailBy,emailId);
    }

    public void clickOnRegisterButton() throws InterruptedException {
        By registerBy = By.xpath(".//div[@class='lightbox__content']//button[@class='btn btn--blue one-whole']");
        waitForElementDisplayed(registerBy);
        clickElement(registerBy);
        Thread.sleep(5000);

    }

    public void enterPassword(String password){
        By passwordBy = By.id("password");
        waitForElementDisplayed(passwordBy);
        enterText(passwordBy,password);
    }

    public void enterConfirmPassword(String password){
        By passwordBy = By.id("password-confirm");
        waitForElementDisplayed(passwordBy);
        enterText(passwordBy,password);
    }

    public void clickOnSave(){
        By saveBy = By.xpath(".//input[@value='Save']");
        waitForElementDisplayed(saveBy);
        clickElement(saveBy);
    }

    public boolean verifyTravelerItinerary(){
        try {
            By itBy = By.xpath("//header[@class='traveller-itinerary__header']");
            waitForElementDisplayed(itBy);
            return true;
        }catch(Exception ex){
            return false;
        }
    }

}
