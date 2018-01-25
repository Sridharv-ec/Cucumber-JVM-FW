package com.kuoni.automation.pageobjects;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By agencyId = By.id("login-block-agency");
    private By agentUserName = By.id("login-block-user-email");
    private By password = By.id("login-block-password");
    private By LoginButton = By.xpath(".//input[@class='btn btn--submit btn--blue']");
    private By legacyRegistrationPage = By.xpath(".//*[@id='pagetitle']/tbody/tr/td");
    private By acceptCookies = By.xpath(".//div[@id='cookiePolicy']//a[contains(.,'Accept')]");
    private By loginButt1 = By.xpath("//input[@class='btn btn--submit btn--blue']");

    private Logger logger = Logger.getLogger("LoginPage");


    public void login(String sAgencyId, String userName, String sPassword){
       // PropertyConfigurator.configure("Log4j.properties");
        clickAcceptCookies();
        logger.info("click on accept cookies");
        waitForElementDisplayed(agencyId);
        enterText(agencyId,sAgencyId);
        logger.debug("Entered agency id");
        enterText(agentUserName,userName);
        logger.debug("Entered agent user name");
        enterText(password,sPassword);
        logger.debug("Entered agent password");
        clickElement(By.id("login-block-remember"));
        if(platform.equalsIgnoreCase("mobile")){
            mouseMoveToElement(driver.findElement(By.xpath(".//form[@id='login']//input[@class='btn btn--submit btn--blue']")));
            waitForElementEnabled(By.xpath(".//form[@id='login']//input[@class='btn btn--submit btn--blue']"));
            clickElement(loginButt1);

        }else {
            waitForElementDisplayed(LoginButton);
            clickElement(LoginButton);
        }
        logger.debug("Clicked on login button");
    }

    public String getLegacySiteTitle(){
        String legacyText = getCurrentPageTitle();
        System.out.println("Legacy Title "+legacyText);
        return legacyText;
    }

    public void clickAcceptCookies(){
        try {
            waitForElementDisplayed(acceptCookies);
            clickElement(acceptCookies);
        }catch(Exception ex){

            }
    }

}
