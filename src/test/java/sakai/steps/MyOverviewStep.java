package sakai.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sakai.utilities.api.BrowserAPI;
import sakai.utilities.SakaiLogger;

public class MyOverviewStep extends BrowserAPI {

    private BrowserAPI browser;

    public MyOverviewStep(BrowserAPI browser)
    {
        this.browser = browser;
    }

    @Then("^I should see various content on home page$")
    public void iShouldSee() {
        SakaiLogger.logInfo("Step: I should see various content on home page");
        browser.getHomePage().overview.checkForContent();
    }
}
