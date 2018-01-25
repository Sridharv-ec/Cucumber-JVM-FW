package com.kuoni.automation.testrunner;

import com.kuoni.automation.teststeps.BaseSteps;
import com.kuoni.automation.util.DriverConfig;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;


@CucumberOptions(
        features = {"src/test/resources/features/TravellerItinerary.feature"},
        //tags = {"@HotelPLP"},
        glue = {"com.kuoni.automation.teststeps"},
        plugin = {"html:target/Reports/report.html"},
        format = {"pretty","html:target/cucumber_qurate","json:target/cucumber_QURATE.json"}
         )
public class TravelerItineraryRunner extends BaseSteps{

    /*private TestNGCucumberRunner testNGCucumberRunner;
    static Browser browser = new Browser();

    @Parameters({"browserName"})
    @BeforeClass(alwaysRun = true)
    public void setUpClass(String browserName) throws Exception {
        //generalUtil.startRecordingVideo();
        BaseSteps.driver = browser.getDriver(browserName);
        softAssert = new SoftAssert();
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
*/
    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    /*@AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        BaseSteps.driver.quit();
        //softAssert.assertAll();
        //generalUtil.stopVedioRecording();
        testNGCucumberRunner.finish();
    }*/

}
