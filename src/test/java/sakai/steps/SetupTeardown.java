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
import sakai.utilities.DriverAPI;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.fail;

public class SetupTeardown extends DriverAPI{

    private DriverAPI api;

    public SetupTeardown(DriverAPI api)
    {
        this.api = api;
    }

    @Before
    public void startUp(Scenario scenario)
    {
        SakaiLogger.logInfo("=========== " + scenario.getName() + " ===========");
        api.setPlatform(System.getenv("sakai_browser"));

        if(api.getPlatform() != null && api.getPlatform().equalsIgnoreCase("chrome"))
        {
            SakaiLogger.logInfo("Initializing testing environment");
            SakaiLogger.logInfo("Using driver: Chrome");

            //Setup Chrome browser configuration and run in headless
            ChromeDriverManager.getInstance().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");

            //Setup global variables and page waiter
            WebDriver driver = new ChromeDriver(options);
            api.setDriver(driver);
            PageWaiter.setDriver(driver, "chrome");
        }
        else if(api.getPlatform() != null && api.getPlatform().equalsIgnoreCase("firefox"))
        {
            SakaiLogger.logInfo("Initializing testing environment");
            SakaiLogger.logInfo("Using driver: Firefox");

            //Setup Firefox browser configuration and run in headless
            FirefoxDriverManager.getInstance().setup();
            FirefoxBinary binary = new FirefoxBinary();
            binary.addCommandLineOptions("--headless");
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary(binary);

            //Setup global variables and page waiter
            WebDriver driver = new FirefoxDriver(options);
            api.setDriver(driver);
            PageWaiter.setDriver(driver, "firefox");
        }
        else
        {
            SakaiLogger.logInfo("No compatible browser environment found");
            fail("No compatible browser environment found");
        }
    }

    @After
    public void tearDown(Scenario scenario)
    {
        if(api.getPlatform() == null || (!api.getPlatform().equalsIgnoreCase("chrome") && !api.getPlatform().equalsIgnoreCase("firefox")))
        {
            SakaiLogger.logInfo("Scenario failed because no browser environment was defined");
            SakaiLogger.logInfo("=========== " + scenario.getName() + " ===========");
        }
        else if(scenario.isFailed())
        {
            WebDriver driver = api.getDriver();
            SakaiLogger.logErr("Scenario failed =( - (" + scenario.getName() + ")");
            File screencap = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screencap, new File("target/screencap/" + scenario.getName() + "_" + new Date() + ".png"));
                SakaiLogger.logInfo("Cleaning the environment");
                driver.manage().deleteAllCookies();
                driver.quit();
                SakaiLogger.logInfo("=========== " + scenario.getName() + " ===========");
            }
            catch(IOException e)
            {

            }
        }
        else
        {
            WebDriver driver = api.getDriver();
            SakaiLogger.logInfo("Cleaning the environment");
            driver.manage().deleteAllCookies();
            driver.quit();
            SakaiLogger.logInfo("=========== " + scenario.getName() + " ===========");
        }
    }
}
