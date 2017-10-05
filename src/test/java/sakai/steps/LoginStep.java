package sakai.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sakai.pages.BasePage;
import sakai.pages.HomePage;
import sakai.pages.LoginPage;
import sakai.utilities.DriverAPI;
import sakai.utilities.SakaiLogger;

public class LoginStep extends DriverAPI{

    private DriverAPI api;

    public LoginStep(DriverAPI api)
    {
        this.api = api;
    }

    @Given("^I navigate to the login page$")
    public void iNavigateToTheLoginPage()
    {
        SakaiLogger.logInfo("Step: I navigate to the login page");
        BasePage page = new BasePage(api.getDriver());
        LoginPage login = api.setBasePage(page).navigateToLogin();
        api.setLoginPage(login);

    }

    @When("^I login as student$")
    public void iLoginAsStudent()
    {
        SakaiLogger.logInfo("Step: I login as student");
        HomePage home = api.getLoginPage().loginAsStudent();
        api.setHomePage(home);

    }

    @And("^I close new feature popup if needed$")
    public void iCloseWhatSNewPopupIfNeeded()
    {
        SakaiLogger.logInfo("Step: I close new feature popup if needed");
        api.getHomePage().closeNewFeaturePopUp();
    }

    @Then("^I should see Sakai logo$")
    public void iShouldSeeNYUClassesLogo() {
        SakaiLogger.logInfo("Step: I should see Sakai logo");
        api.getHomePage().checkForSakaiBanner();
    }

    @Given("^I am logged in$")
    public void iAmLoggedIn() {
        SakaiLogger.logInfo("Step: Given I am logged in");
        iNavigateToTheLoginPage();
        iLoginAsStudent();
    }

    @When("^I log out$")
    public void iLogOut() {
        SakaiLogger.logInfo("Step: When I log out");
        LoginPage login = api.getHomePage().logout();
        api.setLoginPage(login);
    }

    @Then("^I should see logged out$")
    public void iShouldSeeLoggedOut() {
        SakaiLogger.logInfo("Step: I should see logged out");
        api.getLoginPage().checkForLoggedOutBanner();
    }
}
