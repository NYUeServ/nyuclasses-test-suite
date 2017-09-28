package sakai.steps;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import sakai.pages.BasePage;
import sakai.pages.HomePage;
import sakai.pages.LoginPage;
import sakai.utilities.JSWaiter;

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
    public void startUp()
    {
        System.out.println("=========== INITIALIZE LOGIN TEST ===========");
        // FirefoxDriverManager.getInstance().setup();
        // driver = new FirefoxDriver();
        ChromeDriverManager.getInstance().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        JSWaiter.setDriver(driver);

        System.out.println("Initializing testing environment");
        System.out.println("Using driver: Chrome");
    }

    @After
    public void tearDown(Scenario scenario)
    {
        if(scenario.isFailed())
        {
            //TODO: Take screenshot
            System.out.println(scenario.getName());
        }
        
        System.out.println("Cleaning the environment");
        driver.manage().deleteAllCookies();
        driver.quit();
        System.out.println("=========== LOGIN TEST FINISHED ===========");

        
    }

}
