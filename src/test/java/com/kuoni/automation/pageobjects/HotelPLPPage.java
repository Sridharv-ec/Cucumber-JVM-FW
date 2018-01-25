package com.kuoni.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HotelPLPPage extends BasePage {
    public HotelPLPPage(WebDriver driver) {
        super(driver);
    }

    private By hotelPLP = By.xpath(".//*[@id='main-content-container']//a[@class='result-listing__hotel-name']");

    public boolean verifyHotelNames(){
        try{
            waitForElementDisplayed(hotelPLP);
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    public void clickOnAddToItinerary(int num){
        By addToItineraryBy = By.xpath(".//tbody[@class='hotel-rooms__room listing-option']//button[@class='add-to-itinerary__btn btn--multiplelines btn btn--small btn--blue listing-option__btn icon-btn']");
        waitForElementDisplayed(addToItineraryBy);
        List<WebElement> addToItinerary = driver.findElements(addToItineraryBy);
        addToItinerary.get(num).click();
    }


}
