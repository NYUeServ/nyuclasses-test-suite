package sakai.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxOptions;
import sakai.utilities.Configuration;
import sakai.utilities.SakaiLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import sakai.pages.BasePage;
import sakai.pages.HomePage;
import sakai.pages.LoginPage;
import sakai.utilities.JSWaiter;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class LoginStep {

    private WebDriver driver;
    private BasePage page;
    private LoginPage login;
    private HomePage home;


    @Given("^I navigate to the login page$")
    public void iNavigateToTheLoginPage() {
        page = new BasePage(driver);
        login = page.navigateToLogin();
    }

    @When("^I login as student$")
    public void iLoginAsStudent() {
        home = login.loginAsStudent();
    }

    @Then("^I should see NYU Classes logo$")
    public void iShouldSeeNYUClassesLogo() {
        home.checkForSakaiBanner();
    }

    @Given("^I am logged in$")
    public void iAmLoggedIn() {
        iNavigateToTheLoginPage();
        iLoginAsStudent();
    }

    @When("^I log out$")
    public void iLogOut() {
        login = home.logout();
    }

    @Then("^I should see logged out$")
    public void iShouldSeeLoggedOut() throws Throwable {
        login.checkForLoggedOutBanner();
    }

    @Before
    public void startUp(Scenario scenario)
    {
        SakaiLogger.logInfo("=========== " + scenario.getName() + " ===========");
        Configuration.setPlatform(System.getenv("sakai_browser"));

        if(Configuration.getPlatform() != null && Configuration.getPlatform().equalsIgnoreCase("chrome"))
        {
            SakaiLogger.logInfo("Initializing testing environment");
            SakaiLogger.logInfo("Using driver: Chrome");
            ChromeDriverManager.getInstance().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            driver = new ChromeDriver(options);
            driver.manage().deleteAllCookies();
            JSWaiter.setDriver(driver);
        }
        else if(Configuration.getPlatform() != null && Configuration.getPlatform().equalsIgnoreCase("firefox"))
        {
            SakaiLogger.logInfo("Initializing testing environment");
            SakaiLogger.logInfo("Using driver: Firefox");
            FirefoxDriverManager.getInstance().setup();
            FirefoxBinary binary = new FirefoxBinary();
            binary.addCommandLineOptions("--headless");
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary(binary);
            //driver = new FirefoxDriver(options);
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().deleteAllCookies();
            JSWaiter.setDriver(driver);
        }
        else
        {
            SakaiLogger.logInfo("No compatible browser environment found, your browser specification is: " + Configuration.getPlatform());
            fail("No compatible browser environment found");
        }
    }

    @After
    public void tearDown(Scenario scenario)
    {
        if(Configuration.getPlatform() == null || (!Configuration.getPlatform().equalsIgnoreCase("chrome") && !Configuration.getPlatform().equalsIgnoreCase("firefox")))
        {
            SakaiLogger.logInfo("Scenario failed because no browser environment was defined");
            SakaiLogger.logInfo("=========== " + scenario.getName() + " ===========");
        }
        else if(scenario.isFailed())
        {
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
            SakaiLogger.logInfo("Cleaning the environment");
            driver.manage().deleteAllCookies();
            driver.quit();
            SakaiLogger.logInfo("=========== " + scenario.getName() + " ===========");
        }
    }

}
