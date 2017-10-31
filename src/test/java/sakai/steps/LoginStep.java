package sakai.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sakai.pages.HomePage;
import sakai.pages.LoginPage;
import sakai.utilities.UserFactory;
import sakai.utilities.api.Browser;
import sakai.utilities.SakaiLogger;
import sakai.utilities.api.Config;

public class LoginStep extends Browser {

    private Browser browser;
    private Config config;

    public LoginStep(Browser browser, Config config)
    {
        this.browser = browser;
        this.config = config;
    }

    @Given("^I navigate to the login page$")
    public void iNavigateToTheLoginPage()
    {
        SakaiLogger.logInfo("Step: I navigate to the login page");
        LoginPage login = new LoginPage(browser.getDriver(), config.getLoginPageUrl()).navigateToPage();
        browser.setLoginPage(login);

    }

    @When("^I login as student$")
    public void iLoginAsStudent()
    {
        SakaiLogger.logInfo("Step: I login as student");
        HomePage home = browser.getLoginPage().loginAsUser(UserFactory.getValidStudent());
        browser.setHomePage(home);
    }

    @When("^I login as instructor$")
    public void iLoginAsInstructor()
    {
        SakaiLogger.logInfo("Step: I login as instructor");
        HomePage home = browser.getLoginPage().loginAsUser(UserFactory.getValidInstructor());
        browser.setHomePage(home);
    }

    @When("^I login as teaching assistant$")
    public void iLoginAsTeachingAssistant()
    {
        SakaiLogger.logInfo("Step: I login as teaching assistant");
        HomePage home = browser.getLoginPage().loginAsUser(UserFactory.getValidTeachingAssistant());
        browser.setHomePage(home);
    }

    @Given("^I am logged in as \"([^\"]*)\"$")
    public void iAmLoggedIn(String user) {        
        SakaiLogger.logInfo("Step: Given I am logged in as \"" + user + "\"");
        iNavigateToTheLoginPage();
        if(user.equals("student"))
            iLoginAsStudent();
        else if(user.equals("instructor"))
            iLoginAsInstructor();
        else if(user.equals("teaching assistant"))
            iLoginAsTeachingAssistant();
    }

    @When("^I log out$")
    public void iLogOut() {
        SakaiLogger.logInfo("Step: When I log out from home page");
        browser.getHomePage().logout();
    }

    @Then("^I should see logged out$")
    public void iShouldSeeLoggedOut() {
        SakaiLogger.logInfo("Step: I should see logged out");
        browser.getLoginPage().checkForLoggedOutBanner();
    }
}
