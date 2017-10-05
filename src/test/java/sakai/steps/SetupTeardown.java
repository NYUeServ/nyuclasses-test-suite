package sakai.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import sakai.utilities.PageWaiter;
import sakai.utilities.SakaiLogger;
import sakai.utilities.BrowserAPI;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.fail;

public class SetupTeardown extends BrowserAPI {

    private BrowserAPI browser;

    public SetupTeardown(BrowserAPI browser)
    {
        this.browser = browser;
    }

    @Before
    public void startUp(Scenario scenario)
    {
        SakaiLogger.logInfo("=========== Starting (" + scenario.getName() + ") Scenario ===========");
        browser.setPlatform(System.getenv("sakai_browser"));

        if(browser.getPlatform() != null && browser.getPlatform().equalsIgnoreCase("chrome"))
        {
            SakaiLogger.logInfo("Using driver: Chrome - Initializing testing environment");

            //Setup Chrome browser configuration and run in headless
            ChromeDriverManager.getInstance().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");

            //Setup global variables and page waiter
            WebDriver driver = new ChromeDriver(options);
            browser.setDriver(driver);
            PageWaiter.setDriver(driver, "chrome");
        }
        else if(browser.getPlatform() != null && browser.getPlatform().equalsIgnoreCase("firefox"))
        {
            SakaiLogger.logInfo("Using driver: Firefox - Initializing testing environment");

            //Setup Firefox browser configuration and run in headless
            FirefoxDriverManager.getInstance().setup();
            FirefoxBinary binary = new FirefoxBinary();
            binary.addCommandLineOptions("--headless");
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary(binary);

            //Setup global variables and page waiter
            WebDriver driver = new FirefoxDriver(options);
            browser.setDriver(driver);
            PageWaiter.setDriver(driver, "firefox");
        }
        else
        {
            SakaiLogger.logErr("No compatible browser environment found");
            fail("No compatible browser environment found");
        }
    }

    @After
    public void tearDown(Scenario scenario)
    {
        if(browser.getPlatform() == null || (!browser.getPlatform().equalsIgnoreCase("chrome") && !browser.getPlatform().equalsIgnoreCase("firefox")))
        {
            SakaiLogger.logErr("Scenario failed because no browser environment was defined");
            SakaiLogger.logInfo("=========== Finishing (" + scenario.getName() + ") Scenario ===========");
        }
        else if(scenario.isFailed())
        {
            WebDriver driver = browser.getDriver();
            SakaiLogger.logErr("Scenario failed =( - (" + scenario.getName() + ")");
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                String fileName = scenario.getName() + "_" + new Date() + ".png";
                FileUtils.copyFile(screenshot, new File("target/screenshot/" + fileName));
                SakaiLogger.logInfo("Scenario failure screenshot saved as " + fileName);
                SakaiLogger.logDebug("Scenario finished - Deleting cookies");
                driver.manage().deleteAllCookies();
                driver.quit();
                SakaiLogger.logInfo("=========== Finishing (" + scenario.getName() + ") Scenario ===========");
            }
            catch(IOException e)
            {

            }
        }
        else
        {
            WebDriver driver = browser.getDriver();
            SakaiLogger.logDebug("Scenario finished - Deleting cookies");
            driver.manage().deleteAllCookies();
            driver.quit();
            SakaiLogger.logInfo("=========== Finishing (" + scenario.getName() + ") Scenario ===========");
        }
    }
}
