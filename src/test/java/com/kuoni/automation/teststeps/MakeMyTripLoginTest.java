package com.kuoni.automation.teststeps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MakeMyTripLoginTest extends BaseSteps{
    @Given("^I have launched make my trip applicaiton$")
    public void iHaveLaunchedMakeMyTripApplicaiton()  {

    }

    @When("^I am at login page$")
    public void iAmAtLoginPage()  {
        System.out.println("Login page launched");
    }

    @Then("^validate login page$")
    public void validateLoginPage()  {
        System.out.println("Validated login page");
    }
}
