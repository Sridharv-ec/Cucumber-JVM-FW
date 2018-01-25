package com.kuoni.automation.teststeps;

import com.kuoni.automation.pageobjects.*;
import com.kuoni.automation.properties.EnvironmentProperties;
import com.kuoni.automation.util.DriverConfig;
import com.kuoni.automation.util.DBUtil;
import com.kuoni.automation.util.DriverConfig;
import com.kuoni.automation.util.GeneralUtil;
import cucumber.api.java.Before;
import cucumber.api.testng.TestNGCucumberRunner;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.sql.*;
import java.util.concurrent.TimeUnit;

public class BaseSteps extends DriverConfig {

    protected TestNGCucumberRunner testNGCucumberRunner;
    protected  static DriverConfig deviceConfig = new DriverConfig();

    protected static WebDriver driver;
    public SoftAssert softAssert = new SoftAssert();
    public EnvironmentProperties envProp = new EnvironmentProperties();
    public BasePage basePage = new BasePage(driver);
    public LoginPage loginPage = new LoginPage(driver);
    public HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
    public HotelPLPPage hotelPLPPage = new HotelPLPPage(driver);
    public ItineraryPage itineraryPage = new ItineraryPage(driver);
    public ItinerarySharePage itinerarySharePage = new ItinerarySharePage(driver);
    public TravelerPage travelerPage = new TravelerPage(driver);
    public GeneralUtil generalUtil = new GeneralUtil();

    public String loginUrl=envProp.getValueForProperty("nova.html.login");
    public String prepaidAgentId=envProp.getValueForProperty("prepayment.agentid");
    public String prepaidUserName=envProp.getValueForProperty("prepayment.username");
    public String prepaidPassword=envProp.getValueForProperty("prepayment.password");
    public String hotelcity = envProp.getValueForProperty("nova.html.city");
    public String hotelcityAutosuggest = envProp.getValueForProperty("nova.html.citySuggest");

    public String leadTitle = envProp.getValueForProperty("lead.traveler.title");
    public String leadFirstName = envProp.getValueForProperty("lead.traveler.firstname");
    public String leadLastName = envProp.getValueForProperty("lead.traveler.lastname");
    public String leadEmailId = envProp.getValueForProperty("lead.traveler.email");
    public String leadPhoneNumber = envProp.getValueForProperty("lead.traveler.phone.number");

    public String passengerTitle = envProp.getValueForProperty("lead.traveler.title");
    public String passengerFirstName = envProp.getValueForProperty("lead.traveler.firstname");
    public String passengerLastName = envProp.getValueForProperty("lead.traveler.lastname");
    public String travellerloginurl=envProp.getValueForProperty("traveller.login.url");
    public String travellerresetpasswordurl=envProp.getValueForProperty("traveller.resetpassword.url");
    public String travellercreatepasswordurl=envProp.getValueForProperty("traveller.createpassword.url");
    public String travelleritineraryoverviewurl=envProp.getValueForProperty("traveller.itineraryoverview.url");
    public String travellerregisteruserurl=envProp.getValueForProperty("traveller.registeruser.url");
    public String atgdburl=envProp.getValueForProperty("live_rw_oracle_url");
    public String atgdbusername=envProp.getValueForProperty("live_rw_oracle_uname");
    public String atgdbpassword=envProp.getValueForProperty("live_rw_oracle_pwd");
    private Logger logger = Logger.getLogger("BaseSteps");

    //@Parameters({"browserName"})
    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        //generalUtil.startRecordingVideo();
        BaseSteps.driver = deviceConfig.getDriver(device);
        softAssert = new SoftAssert();
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        BaseSteps.driver.quit();
        //softAssert.assertAll();
        //generalUtil.stopVedioRecording();
        testNGCucumberRunner.finish();
    }


    void logout(){
        basePage.logOut();
    }

    public void addOneHotelMoveToItineraryPage(String cityCode, String city, int checkIn, int checkOut, String travelerEmailId) throws InterruptedException {
        PropertyConfigurator.configure("Log4j.properties");
        hotelSearchPage.enterHotelDestination(cityCode);
        logger.info("Entered hotel city");
        hotelSearchPage.selectFromAutoSuggest(city);
        logger.debug("Selected hotel city from autosuggest");
        hotelSearchPage.selectHotelCheckInCheckOutDate(checkIn,checkOut);
        logger.debug("Select check in and check out dates");
        hotelSearchPage.clickOnFindButton();
        logger.debug("Click on find button");
        hotelPLPPage.clickOnAddToItinerary(0);
        itineraryPage.enterLeadTravelerDetails(leadTitle,leadFirstName,leadLastName,travelerEmailId,leadPhoneNumber);
        itineraryPage.enterPassengerTravelerDetails(passengerTitle,passengerFirstName,passengerLastName,0);
        itineraryPage.clickOnBookButton(0);
        itineraryPage.clickOnConfirmBookinAndPayLater();
        clickOnCloseLightBox();
    }

    public void navigateToOtherWebSite(String url){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(url);
    }

    public void clickOnCloseLightBox() throws InterruptedException{
        Thread.sleep(4000);
        By closeBy = By.xpath(".//div[@class='lightbox__container']//button[@class='lightbox__close-button icon icon--after icon-cross-thin--after']");
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.presenceOfElementLocated(closeBy));
        driver.findElement(closeBy).click();
    }

    public Connection getDBConnection(){
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(atgdburl,atgdbusername,atgdbpassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public ResultSet executeDBQuery(String sqlQuery){
        Connection conn = getDBConnection();
        ResultSet resultSet = null;
        try {
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void screenShot(boolean isScreenShot){
        if(isScreenShot){
            String strScreenShot = generalUtil.targetScreenShotPath();

            try{
                Thread.sleep(2000);
                File srcFile;
                srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(srcFile,new File(strScreenShot));
                Thread.sleep(2000);
                System.out.println("Screen shot taken");


            }catch (Exception ex){
                ex.printStackTrace();
                System.out.println("Screen shot not taken");
                System.out.println("Error Message is: "+ex.getMessage());
            }
        }
    }

}
