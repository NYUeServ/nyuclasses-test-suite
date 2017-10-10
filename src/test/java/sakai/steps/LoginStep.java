package sakai.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sakai.pages.HomePage;
import sakai.pages.LoginPage;
import sakai.utilities.BrowserAPI;
import sakai.utilities.PageWaiter;
import sakai.utilities.SakaiLogger;

import static org.junit.Assert.assertEquals;

public class LoginStep extends BrowserAPI {

    private BrowserAPI browser;

    public LoginStep(BrowserAPI browser)
    {
        this.browser = browser;
    }

    @Given("^I navigate to the login page$")
    public void iNavigateToTheLoginPage()
    {
        SakaiLogger.logInfo("Step: I navigate to the login page");
        LoginPage login = new LoginPage(browser.getDriver()).navigateToPage();
        browser.setLoginPage(login);

    }

    @When("^I login as student$")
    public void iLoginAsStudent()
    {
        SakaiLogger.logInfo("Step: I login as student");
        HomePage home = browser.getLoginPage().loginAsStudent();
        PageWaiter.waitUntilPageReady();
        assertEquals("NYU Classes : My Workspace : Overview", browser.getDriver().getTitle());
        browser.setHomePage(home);

    }

    @And("^I close new feature popup if needed$")
    public void iCloseWhatSNewPopupIfNeeded()
    {
        SakaiLogger.logInfo("Step: I close new feature popup if needed");
        browser.getHomePage().closeNewFeaturePopUp();
    }

    @Then("^I should see Sakai logo$")
    public void iShouldSeeNYUClassesLogo() {
        SakaiLogger.logInfo("Step: I should see Sakai logo");
        browser.getHomePage().checkForSakaiBanner();
    }

    @Given("^I am logged in$")
    public void iAmLoggedIn() {
        SakaiLogger.logInfo("Step: Given I am logged in");
        iNavigateToTheLoginPage();
        iLoginAsStudent();
    }

    @When("^I log out$")
    public void iLogOut() {
        SakaiLogger.logInfo("Step: When I log out from home page");
        LoginPage login = browser.getHomePage().logout();
        browser.setLoginPage(login);
    }

    @Then("^I should see logged out$")
    public void iShouldSeeLoggedOut() {
        SakaiLogger.logInfo("Step: I should see logged out");
        browser.getLoginPage().checkForLoggedOutBanner();
    }
}
