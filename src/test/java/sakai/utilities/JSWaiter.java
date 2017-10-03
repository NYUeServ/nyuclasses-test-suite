package sakai.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class exists because Selenium WebDriver does not have a built-in Javascript/JQuery wait.
 * This is adapted from https://github.com/swtestacademy/JSWaiter
 */
public class JSWaiter {

    private static WebDriver jsWaitDriver;
    private static WebDriverWait jsWait;
    private static JavascriptExecutor jsExec;

    //Get the driver from relevant test
    public static void setDriver (WebDriver driver) {
        jsWaitDriver = driver;
        jsWait = new WebDriverWait(jsWaitDriver, 10);
        jsExec = (JavascriptExecutor) jsWaitDriver;
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
        if(Configuration.getPlatform().equalsIgnoreCase("chrome"))
        {
            SakaiLogger.logInfo("Chrome Platform: Waiting for jQuery/Javascript on page");
            JSWaiter.waitUntilJQueryReady();
        }
        else
        {
            SakaiLogger.logInfo("Firefox Platform: Sleeping thread for page detection");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
