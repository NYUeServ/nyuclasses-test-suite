package sakai.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * This class exists because Selenium WebDriver does not have a built-in Javascript/JQuery wait.
 * This is adapted from https://github.com/swtestacademy/JSWaiter
 */
public class PageWaiter {

    private static WebDriver jsWaitDriver;
    private static WebDriverWait jsWait;
    private static JavascriptExecutor jsExec;
    private static String waitPlatform;

    //Get the driver from relevant test
    public static void setDriver (WebDriver driver, String platform) {
        jsWaitDriver = driver;
        jsWait = new WebDriverWait(jsWaitDriver, 10);
        jsExec = (JavascriptExecutor) jsWaitDriver;
        waitPlatform = platform;
    }

    //Wait for JQuery Load
    public static void waitForJQueryLoad() {
        //Wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor) jsWaitDriver)
                .executeScript("return jQuery.active") == 0);

        //Get JQuery is Ready
        boolean jqueryReady = (Boolean) jsExec.executeScript("return jQuery.active==0");

        //Wait JQuery until it is Ready!
        if(!jqueryReady) {
            jsWait.until(jQueryLoad);
        }
    }

    //Wait Until JS Ready
    public static void waitUntilJSReady() {
        WebDriverWait wait = new WebDriverWait(jsWaitDriver,15);
        JavascriptExecutor jsExec = (JavascriptExecutor) jsWaitDriver;

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) jsWaitDriver)
                .executeScript("return document.readyState").toString().equals("complete");

        //Get JS is Ready
        boolean jsReady = (Boolean) jsExec.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if(!jsReady) {
            wait.until(jsLoad);
        }
    }

    //Wait Until JQuery and JS Ready
    public static void waitUntilJQueryReady() {
        JavascriptExecutor jsExec = (JavascriptExecutor) jsWaitDriver;

        Boolean jQueryDefined = (Boolean) jsExec.executeScript("return typeof jQuery != 'undefined'");
        if (jQueryDefined == true) {
            //Pre Wait for stability (Optional)
            sleep(20);

            //Wait JQuery Load
            waitForJQueryLoad();

            //Wait JS Load
            waitUntilJSReady();

            //Post Wait for stability (Optional)
            sleep(20);
        }  else {
            System.out.println("jQuery is not defined on this site!");
        }
    }

    //Wait until page is ready
    public static void waitUntilPageReady()
    {
        if(waitPlatform.equalsIgnoreCase("chrome"))
        {
            SakaiLogger.logInfo("Chrome Platform: Waiting for jQuery/Javascript on page");
            PageWaiter.waitUntilJQueryReady();
        }
        else
        {
            SakaiLogger.logInfo("Firefox Platform: Waiting for page redirection to settle");
            DocumentSettleCondition<Boolean> settleCondition = new DocumentSettleCondition<>(
                    ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.forwarding")));

            new FluentWait<WebDriver>(jsWaitDriver)
                    .withTimeout(30, TimeUnit.SECONDS)
                    .pollingEvery(settleCondition.getSettleTime(), TimeUnit.MILLISECONDS)
                    .ignoring(WebDriverException.class)
                    .until(settleCondition);
        }
    }


    public static void sleep (long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
