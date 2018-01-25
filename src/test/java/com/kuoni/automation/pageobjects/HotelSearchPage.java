package com.kuoni.automation.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HotelSearchPage extends BasePage {
    public HotelSearchPage(WebDriver driver) {
        super(driver);
    }
    private Logger logger = Logger.getLogger("HotelSearchPage");
    private By hotelSearchPage = By.xpath(".//input[@id='hotel-search__input-destination']");
    private By hotelSearchTab = By.xpath(".//li[@class='search-tabs__tab search-tabs__tab--active']/button/span");
    private By HotelTextField = By.xpath(".//*[@id='hotel-search__input-destination']");
    private By HotelAutoSuggest = By.xpath(".//*[@id='hotel-search__destination']//li[@class='auto-suggest__option icon icon-location']");
    private By findButton = By.xpath(".//*[@id='search-tabs__hotel-content']//input[@class='input-tabs__input hotel-search__submit btn btn--submit btn--blue btn--full loadmask_on-click']");

    public boolean verifyHomePageDisplayed(){
        try {
            waitForElementDisplayed(hotelSearchPage);
            logger.debug("Search page displayed");
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    public String getHotelSearchTabText(){
        String hotelSearchTabText = getElementText(hotelSearchTab);
        System.out.println("Hotel search tab text is "+hotelSearchTabText);
        logger.debug("Hotel search tab text is "+hotelSearchTabText);
        return hotelSearchTabText;
    }

    public void enterHotelDestination(String hotel){
        waitForElementDisplayed(HotelTextField);
        enterText(HotelTextField,hotel);
        logger.info("City name entered in Hotel text location field");
    }

    public void selectFromAutoSuggest(String hotel){
        waitForElementDisplayed(HotelAutoSuggest);
        By element = By.xpath("//*[@id='hotel-search__destination']//li[contains(.,'"+hotel+"')]");
        System.out.println("//*[@id='hotel-search__destination']//strong[contains(.,'"+hotel+"')]");
        waitForElementDisplayed(element);
        clickElement(element);
        logger.debug("City is selected from autosuggest");
    }

    public void clickOnFindButton(){
        waitForElementDisplayed(findButton);
        clickElement(findButton);
        logger.debug("Clicked on find button from search page");
    }

    public void selectHotelCheckInCheckOutDate(int checkIn, int checkOut) throws InterruptedException {
        By checkInElement = By.xpath(".//*[@id='hotel-search__input-check-in']");
        waitForElementDisplayed(checkInElement);
        clickElement(checkInElement);
        selectHotelDate(checkIn);
        logger.debug("Check in date selected");
        Thread.sleep(2000);
        selectHotelDate(checkOut);
        logger.debug("Check out date selected");
        Thread.sleep(2000);
    }

    public void selectHotelDate(int iDate){
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        int iDay = Integer.parseInt(dateFormat.format(date));
        By checkInDate = By.xpath(".//div[@class='date-range-selector__calendar hasDatepicker']//td[@data-handler='selectDay']//a");
        waitForElementDisplayed(checkInDate);
        List<WebElement> selectChechInDate = driver.findElements(checkInDate);
        selectChechInDate.get(iDay + iDate).click();
    }
}
