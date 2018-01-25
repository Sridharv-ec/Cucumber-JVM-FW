package com.kuoni.automation.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.v6.B;

import javax.annotation.Nullable;

public class WebDriverUtil {

    public static WebDriver driver;


    public WebDriverUtil(WebDriver driver){
        this.driver = driver;
    }

    public void navigateToUrl(String url){
        driver.get(url);
        //logger.debug("URL is launched");
    }

    public boolean isElementDisplayed(By element){
        boolean status = false;
        try{
            return driver.findElement(element).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public void clickElement(By element){
        driver.findElement(element).click();
    }



    public void enterText(By element , String sText){
        driver.findElement(element).sendKeys(sText);
    }

    public void clickBrowserBackButton(){
        driver.navigate().back();
    }

    public void waitForElementEnabled(By element){
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementDisplayed(By element){
        WebDriverWait wait = new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public void waitForElementVisible(By element){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitForPageLoad(){
        ExpectedCondition<Boolean> pageLoadCondtion = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver,90);
    }

    public String getCurrentPageTitle(){
        return driver.getTitle();
    }

    public String getElementText(By element){
        waitForElementDisplayed(element);
        WebElement webElement = driver.findElement(element);
        String elementText = webElement.getText();
        System.out.println("Element text displayed is "+elementText);
        return elementText;
    }

    public void waitForAjaxIconToDisappear(){
        By loadBy = By.xpath(".//div[@id='ajax-action-block']");
        int i = 0;
        try{
            while ((i < 60) && (driver.findElement(loadBy).isDisplayed()==true)){
                Thread.sleep(1000);
                i++;
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void mouseMoveToElement(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

}
