/*
package com.kuoni.automation.util;

import io.appium.java_client.CommandExecutionHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidMobileCommandHelper;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.http.HttpClient;

import java.net.URL;

public class MyBrowser extends AndroidDriver implements WebDriver {
    public MyBrowser(HttpCommandExecutor executor, Capabilities capabilities) {
        super(executor, substituteMobilePlatform(capabilities, "Android"));
    }

    public MyBrowser(URL remoteAddress, Capabilities desiredCapabilities) {
        super(remoteAddress, substituteMobilePlatform(desiredCapabilities, "Android"));
    }

    public MyBrowser(URL remoteAddress, HttpClient.Factory httpClientFactory, Capabilities desiredCapabilities) {
        super(remoteAddress, httpClientFactory, substituteMobilePlatform(desiredCapabilities, "Android"));
    }

    public MyBrowser(AppiumDriverLocalService service, Capabilities desiredCapabilities) {
        super(service, substituteMobilePlatform(desiredCapabilities, "Android"));
    }

    public MyBrowser(AppiumDriverLocalService service, HttpClient.Factory httpClientFactory, Capabilities desiredCapabilities) {
        super(service, httpClientFactory, substituteMobilePlatform(desiredCapabilities, "Android"));
    }

    public MyBrowser(AppiumServiceBuilder builder, Capabilities desiredCapabilities) {
        super(builder, substituteMobilePlatform(desiredCapabilities, "Android"));
    }

    public MyBrowser(AppiumServiceBuilder builder, HttpClient.Factory httpClientFactory, Capabilities desiredCapabilities) {
        super(builder, httpClientFactory, substituteMobilePlatform(desiredCapabilities, "Android"));
    }

    public MyBrowser(HttpClient.Factory httpClientFactory, Capabilities desiredCapabilities) {
        super(httpClientFactory, substituteMobilePlatform(desiredCapabilities, "Android"));
    }

    public MyBrowser(Capabilities desiredCapabilities) {
        super(substituteMobilePlatform(desiredCapabilities, "Android"));
    }

    public void endTestCoverage(String intent, String path) {
        CommandExecutionHelper.execute(this, AndroidMobileCommandHelper.endTestCoverageCommand(intent, path));
    }

    public void openNotifications() {
        CommandExecutionHelper.execute(this, AndroidMobileCommandHelper.openNotificationsCommand());
    }

    public void toggleLocationServices() {
        CommandExecutionHelper.execute(this, AndroidMobileCommandHelper.toggleLocationServicesCommand());
    }
}
*/
