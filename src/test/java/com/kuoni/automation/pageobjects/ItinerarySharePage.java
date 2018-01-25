package com.kuoni.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ItinerarySharePage extends BasePage {
    public ItinerarySharePage(WebDriver driver) {
        super(driver);
    }



    boolean verifyBookedToggleSelected(){
        try{
            //waitFor(20) {$("#root-wrapper #choose-items-to-share [data-group=share-booked-items] .switch-slider--holder.secondary-item.disabled",0).displayed }
            By toggleBy = By.xpath(".//input[@name='switchDisplayPricingBooked']/ancestor::li[@class='switch-slider--holder secondary-item disabled']");
            waitForElementDisplayed(toggleBy);
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    public void selectBookedItem() throws InterruptedException {
        if (verifyBookedToggleSelected() == false) {
        } else {
            Thread.sleep(2000);
            By bookedItemBy = By.xpath(".//ul[@data-group='share-booked-items']//descendant::span[position()=1]");
            clickElement(bookedItemBy);
        }
    }

    public void clickOnSavaAndUpdateButton(){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,-1000)", "");
        By saveBy = By.xpath(".//div[@class='trip-header__content']//button");
        clickElement(saveBy);
        waitForAjaxIconToDisappear();
    }
}
