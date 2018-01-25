package com.kuoni.automation.teststeps;

import com.kuoni.automation.teststeps.BaseSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HotelBookingTest extends BaseSteps{

    /*@Given("^I have launched agent site application$")
    public void iHaveLaunchedAgentSiteApplication() {
        loginPage.navigateToUrl(loginUrl);
    }*/

    @When("^I login with prepaid valid credentials$")
    public void iLoginWithPrepaidValidCredentials()  {
        loginPage.login(prepaidAgentId,prepaidUserName,prepaidPassword);
    }

    @And("^Enter hotel city$")
    public void enterHotelCity()  {
        hotelSearchPage.enterHotelDestination(hotelcity);
        hotelSearchPage.selectFromAutoSuggest(hotelcityAutosuggest);
    }

    @And("^Select check in and CheckOut date and search$")
    public void selectCheckInAndCheckOutDateAndSearch() throws InterruptedException {
        hotelSearchPage.selectHotelCheckInCheckOutDate(20,23);
        hotelSearchPage.clickOnFindButton();
    }

    @And("^Click On Add to itinerary$")
    public void clickOnAddToItinerary()  {
        hotelPLPPage.clickOnAddToItinerary(0);
    }

    @And("^Enter lead traveller and passenger$")
    public void enterLeadTravellerAndPassenger() throws InterruptedException {
        itineraryPage.enterLeadTravelerDetails(leadTitle,leadFirstName,leadLastName,leadEmailId,leadPhoneNumber);
        itineraryPage.enterPassengerTravelerDetails(passengerTitle,passengerFirstName,passengerLastName,0);
    }

    @And("^Click on Book button$")
    public void click_On_Book_Button() throws InterruptedException {
        itineraryPage.clickOnBookButton(0);
    }

    @And("^Click on Confirm and pay later button$")
    public void clickOnConfirmAndPayLaterButton() throws InterruptedException {
        itineraryPage.clickOnConfirmBookinAndPayLater();
        itineraryPage.clickOnCloseLightBox();
    }

    @Then("^Hotel item should get confirmed$")
    public void hotelItemShouldGetConfirmed()  {
        softAssert.assertTrue(itineraryPage.getBookingStutus(0).equalsIgnoreCase("Confirmed"));
    }
}
