package sakai.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sakai.pages.BasePage;
import sakai.pages.HomePage;
import sakai.pages.LoginPage;
import sakai.utilities.Util;

public class LoginStep {

    private BasePage page;
    private LoginPage login;
    private HomePage home;


    @Given("^I navigate to the login page$")
    public void iNavigateToTheLoginPage() {
        page = new BasePage(Util.getDriver());
        login = page.navigateToLogin();
    }

    @When("^I login as student$")
    public void iLoginAsStudent() {
        home = login.loginAsStudent();
    }

    @And("^I close new feature popup if needed$")
    public void iCloseWhatSNewPopupIfNeeded() { home.closeNewFeaturePopUp();}

    @Then("^I should see Sakai logo$")
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
    public void iShouldSeeLoggedOut() {
        login.checkForLoggedOutBanner();
    }
}
