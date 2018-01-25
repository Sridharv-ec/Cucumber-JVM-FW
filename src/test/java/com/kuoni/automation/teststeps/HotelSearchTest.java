package com.kuoni.automation.teststeps;

import com.kuoni.automation.pageobjects.HotelSearchPage;
import com.kuoni.automation.pageobjects.LoginPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HotelSearchTest extends BaseSteps{

    @Given("^I have launched agent site application$")
    public void iHaveLaunchedAgentSiteApplication() {
        loginPage.navigateToUrl(loginUrl);
    }


    @When("^I login with valid credentials$")
    public void iLoginWithValidCredentials() {
        loginPage.login(prepaidAgentId,prepaidUserName,prepaidPassword);
    }

    @And("^Search for an hotel$")
    public void searchForAnHotel() {
        hotelSearchPage.enterHotelDestination(hotelcity);
        hotelSearchPage.selectFromAutoSuggest(hotelcityAutosuggest);
        hotelSearchPage.clickOnFindButton();
    }

    @Then("^Landed in Hotel PLP page$")
    public void landedInHotelPLPPage() {
       softAssert.assertTrue(hotelPLPPage.verifyHotelNames(),"Hotel PLP page did not display after search hotels");
       logout();
    }

    @And("^Enter hotel name$")
    public void enterHotelName() {
        hotelSearchPage.enterHotelDestination(hotelcity);
        hotelSearchPage.selectFromAutoSuggest(hotelcityAutosuggest);
    }


    @And("^Enter check in and CheckOut date and search$")
    public void enterCheckInAndCheckOutDateAndSearch() throws InterruptedException {
        hotelSearchPage.selectHotelCheckInCheckOutDate(30,40);
        hotelSearchPage.clickOnFindButton();
    }
}
