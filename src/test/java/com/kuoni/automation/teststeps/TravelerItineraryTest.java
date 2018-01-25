package com.kuoni.automation.teststeps;

import com.kuoni.automation.util.DBUtil;
import com.kuoni.automation.util.GeneralUtil;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TravelerItineraryTest extends BaseSteps{
    int checkIn = 10;
    int checkOut = 14;
    String travelerEmailId;
    public DBUtil dbUtil = new DBUtil();
    @Given("^I have logged into the agentsite$")
    public void iHaveLoggedIntoTheAgentsite()  {
        loginPage.navigateToUrl(loginUrl);
        loginPage.login(prepaidAgentId,prepaidUserName,prepaidPassword);
    }

    @When("^I Create an itinerary and share to traveler$")
    public void iCreateAnItineraryAndShareToTraveler() throws InterruptedException {
        travelerEmailId = GeneralUtil.generateEmailId();
        addOneHotelMoveToItineraryPage(hotelcity, hotelcityAutosuggest, checkIn, checkOut,travelerEmailId );
        itineraryPage.clicOnManageItinerary();
        itineraryPage.selectShareItinerary();
        itinerarySharePage.selectBookedItem();
        itinerarySharePage.clickOnSavaAndUpdateButton();
        Thread.sleep(4000);
        logout();
    }

    @And("^Login into traveler site$")
    public void loginIntoTravelerSite() throws InterruptedException {
        navigateToOtherWebSite(travellerloginurl);
        travelerPage.clickOnRegisterHere();
        travelerPage.enterEmailAddress(travelerEmailId);
        travelerPage.clickOnRegisterButton();
        clickOnCloseLightBox();
        String passwordToken = dbUtil.getPasswordToken(travelerEmailId);
        String createPasswordLink = travellerregisteruserurl+passwordToken;
        System.out.println("Traveler create password link is "+createPasswordLink);
        navigateToOtherWebSite(createPasswordLink);
        travelerPage.enterPassword("Password2123");
        travelerPage.enterConfirmPassword("Password2123");
        Thread.sleep(2000);
        travelerPage.clickOnSave();

    }

    @Then("^Validate the itinerary details in traveler page$")
    public void validateTheItineraryDetailsInTravelerPage()  {
        softAssert.assertTrue(travelerPage.verifyTravelerItinerary(),"Traveler itinerary page not displayed");
    }
}
