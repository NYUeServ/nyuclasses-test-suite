package sakai.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sakai.pages.BasePage;
import sakai.pages.HomePage;
import sakai.pages.LoginPage;
import sakai.utilities.DriverAPI;

public class LoginStep extends DriverAPI{

    private DriverAPI api;

    public LoginStep(DriverAPI api)
    {
        this.api = api;
    }

    @Given("^I navigate to the login page$")
    public void iNavigateToTheLoginPage()
    {
        BasePage page = new BasePage(api.getDriver());
        LoginPage login = api.setBasePage(page).navigateToLogin();
        api.setLoginPage(login);

    }

    @When("^I login as student$")
    public void iLoginAsStudent()
    {
        HomePage home = api.getLoginPage().loginAsStudent();
        api.setHomePage(home);

    }

    @And("^I close new feature popup if needed$")
    public void iCloseWhatSNewPopupIfNeeded()
    {
        api.getHomePage().closeNewFeaturePopUp();
    }

    @Then("^I should see Sakai logo$")
    public void iShouldSeeNYUClassesLogo() {
        api.getHomePage().checkForSakaiBanner();
    }

    @Given("^I am logged in$")
    public void iAmLoggedIn() {
        iNavigateToTheLoginPage();
        iLoginAsStudent();
    }

    @When("^I log out$")
    public void iLogOut() {
        LoginPage login = api.getHomePage().logout();
        api.setLoginPage(login);
    }

    @Then("^I should see logged out$")
    public void iShouldSeeLoggedOut() {
        api.getLoginPage().checkForLoggedOutBanner();
    }
}
