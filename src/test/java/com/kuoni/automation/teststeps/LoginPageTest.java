package com.kuoni.automation.teststeps;

import com.kuoni.automation.constant.ErrorMessageAndUIElementText;
import com.kuoni.automation.pageobjects.HotelSearchPage;
import com.kuoni.automation.pageobjects.LoginPage;
import com.kuoni.automation.teststeps.BaseSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginPageTest extends BaseSteps{

    @Given("^I have launched UK agent site application$")
    public void i_have_launched_UK_agent_site_application() {
        loginPage.navigateToUrl(loginUrl);
        screenShot(true);
    }

    @When("^I login with valid credit credentials$")
    public void i_login_with_valid_credit_credentials()  {
        loginPage.login(prepaidAgentId,prepaidUserName,prepaidPassword);
        screenShot(true);
    }

    @Then("^landed in the Home page$")
    public void landed_in_the_Home_page() {
        softAssert.assertTrue(hotelSearchPage.verifyHomePageDisplayed(),"Home page not displayed");
        softAssert.assertTrue(hotelSearchPage.getHotelSearchTabText().equalsIgnoreCase(ErrorMessageAndUIElementText.hotelSearchTabText),"Actual hotel search tab "+hotelSearchPage.getHotelSearchTabText()+" doesn't matches with expected one "+ErrorMessageAndUIElementText.hotelSearchTabText+"");
        logout();
        softAssert.assertAll();
    }

    @When("^I enter invalid credentials$")
    public void iEnterInvalidCredentials() {
        loginPage.login(prepaidAgentId,"govind@gmail.com",prepaidPassword);
    }

    @Then("^error thown on the login page$")
    public void errorThownOnTheLoginPage() {
        softAssert.assertTrue(loginPage.getLegacySiteTitle().equalsIgnoreCase(ErrorMessageAndUIElementText.legacyCreateUserLabel),"Actual legacy site create user text "+loginPage.getLegacySiteTitle()+" doesn't matches with expected one "+ErrorMessageAndUIElementText.legacyCreateUserLabel+"");
        softAssert.assertAll();
    }


}
