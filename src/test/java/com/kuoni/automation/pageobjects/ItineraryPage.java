package com.kuoni.automation.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ItineraryPage extends BasePage{
    private Logger logger = Logger.getLogger("HotelSearchPage");
    public ItineraryPage(WebDriver driver) {
        super(driver);
    }

    public void selectLeadTravelerTitle(String title){
        By titleBy = By.xpath(".//select[@class='grid-item__select']");
        waitForElementDisplayed(titleBy);
        Select sTitle = new Select(driver.findElement(titleBy));
        sTitle.selectByVisibleText(title);
        logger.info("select lead traveler title");
    }

    public void enterFirstName(String sFristName, int travelerNum){
        By firstNameBy = By.xpath(".//input[@placeholder='First name*']");
        waitForElementDisplayed(firstNameBy);
        int sSize = driver.findElements(firstNameBy).size();
        if(sSize > 1) {
            List<WebElement> firstNames = driver.findElements(firstNameBy);
            WebElement firstName = firstNames.get(travelerNum);
            firstName.clear();
            firstName.sendKeys(sFristName);
            logger.info("Enter first name");
        }else{
            enterText(firstNameBy,sFristName);
            logger.info("Enter first name");
        }
    }

    public void enterLastName(String sLastName, int travelerNum){
        By lastNameBy = By.xpath(".//input[@placeholder='Last name*']");
        waitForElementDisplayed(lastNameBy);
        int sSize = driver.findElements(lastNameBy).size();
        if(sSize > 1) {
            List<WebElement> lastNames = driver.findElements(lastNameBy);
            WebElement lastName = lastNames.get(travelerNum);
            lastName.clear();
            lastName.sendKeys(sLastName);
            logger.info("Enter last name");
        }else{
            enterText(lastNameBy,sLastName);
            logger.info("Enter last name");
        }
    }

    public void enterLeadEmailId(String sEmailId){
        By emailBy = By.xpath(".//*[@id='lead-passenger-email']");
        waitForElementDisplayed(emailBy);
        enterText(emailBy,sEmailId);
        logger.info("Entered lead traveler email id");
    }

    public void enterPhoneNumber(String phoneNumber){
        By phoneNoBy = By.xpath(".//input[@placeholder='Telephone number']");
        waitForElementDisplayed(phoneNoBy);
        enterText(phoneNoBy,phoneNumber);
        logger.info("Entered phone number");
    }

    public void clickOnSaveButton(){
        By saveBy = By.xpath(".//*[@id='main-content-container']//button[contains(@class,'traveller-form__submit btn btn--large btn--blue') or contains(@class,'js-check-form-submit')]");
        waitForElementDisplayed(saveBy);
        clickElement(saveBy);
        logger.info("Clicked on save button");
        waitForAjaxIconToDisappear();
    }

    public void clickOnBookButton(int itemNum) throws InterruptedException {
        Thread.sleep(2000);
        By bookBy = By.xpath(".//*[@id='main-content-container']//div[@class='itinerary-item__main']//button[@class='itinerary-item__book btn btn--medium btn--blue']");
        waitForElementDisplayed(bookBy);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,70)", "");
        int sSize = driver.findElements(bookBy).size();
        if(sSize > 1) {
            List<WebElement> bookButton = driver.findElements(bookBy);
            bookButton.get(itemNum).click();
            logger.info("Clicked on book button");
        }else{
            clickElement(bookBy);
            logger.info("Clicked on book button");
        }

    }

    public void clickOnConfirmBookinAndPayLater() throws InterruptedException {
        Thread.sleep(2000);
        By payLaterBy = By.xpath(".//div[@class='lightbox__content']//button[@class='btn btn--multiplelines one-whole push--bottom  push--right btn-pay-later']");
        waitForElementDisplayed(payLaterBy);
        clickElement(payLaterBy);
        logger.info("Clicked on Confirm booking and Pay later button");
        waitForAjaxIconToDisappear();
        Thread.sleep(5000);
    }

    public String getBookingStutus(int itemNum){
        By bookingStatusBy = By.xpath(".//span[@class='itinerary-status--confirmed']");
        waitForElementDisplayed(bookingStatusBy);
        String bookingStatus;
        int sSize = driver.findElements(bookingStatusBy).size();
        if(sSize > 1) {
            List<WebElement> bookingStatusList = driver.findElements(bookingStatusBy);
            bookingStatus = bookingStatusList.get(itemNum).getText();
            System.out.println("Booking status is " + bookingStatus);
            return bookingStatus;
        }else{
            bookingStatus = driver.findElement(bookingStatusBy).getText();
        }
        return bookingStatus;
    }


    public void enterLeadTravelerDetails(String title, String firstName, String lastName, String emailId,String phoneNumber) throws InterruptedException {
        By removeBy = By.xpath(".//a[@class='traveller-form__traveller-remove btn btn--link btn--link--blue']");
        waitForElementDisplayed(removeBy);
        List<WebElement> removeLink = driver.findElements(removeBy);
        for(int i = 0;i<removeLink.size();i++) {
            removeLink.get(i).click();
            Thread.sleep(1000);
        }
        selectLeadTravelerTitle(title);
        enterFirstName(firstName,0);
        enterLastName(lastName, 0);
        enterLeadEmailId(emailId);
        enterPhoneNumber(phoneNumber);
        Thread.sleep(2000);
        clickOnSaveButton();
    }

    public void enterPassengerTravelerDetails(String title, String firstName, String lastName,int PassengerNum) throws InterruptedException {

        clickOnAddTraveller(PassengerNum);
        selectPassengerTitle(title);
        enterFirstName(firstName,PassengerNum);
        enterLastName(lastName, PassengerNum);
        Thread.sleep(2000);
        clickOnSaveButton();
    }

    public void selectPassengerTitle(String title){
        By titleBy = By.xpath(".//select[@class='select-title']");
        waitForElementDisplayed(titleBy);
        Select sTitle = new Select(driver.findElement(titleBy));
        sTitle.selectByVisibleText(title);
    }

    public void clickOnAddTraveller(int passengerNum){
        By addTravelerBy = By.xpath(".//form[@id='traveller-form__new-pax']//button[@class='traveller-form__addtraveller btn btn--link btn--link--blue']");
        waitForElementDisplayed(addTravelerBy);
        //List<WebElement> addTravelerElements = driver.findElements(addTravelerBy);
        //addTravelerElements.get(passengerNum).click();
        //clickElement(addTravelerBy);

        WebElement addTraveler = driver.findElement(addTravelerBy);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", addTraveler);
        logger.info("Clicked on add traveller button");
    }

    public void clicOnManageItinerary(){
        By manageItineraryBy = By.xpath(".//*[@id='trip-header']//div[@class='custom-select__selection label']");
        waitForElementDisplayed(manageItineraryBy);
        clickElement(manageItineraryBy);
        logger.info("Clicked on manage itinerary drop down");
    }

    public void selectShareItinerary(){
        By shareItineraryBy = By.xpath(".//li[@data-option='share']//a[@class='itinerary-actions-share']");
        waitForElementDisplayed(shareItineraryBy);
        clickElement(shareItineraryBy);
        logger.info("Clicked on share itinerary button");
    }

}
