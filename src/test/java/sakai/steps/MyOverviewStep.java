package sakai.steps;

import cucumber.api.java.en.Then;
import sakai.utilities.api.Browser;
import sakai.utilities.SakaiLogger;

public class MyOverviewStep extends Browser {

    private Browser browser;

    public MyOverviewStep(Browser browser)
    {
        this.browser = browser;
    }

    @Then("^I should see various content on home page$")
    public void iShouldSee() {
        SakaiLogger.logInfo("Step: I should see various content on home page");
        browser.getHomePage().overview.checkForContent();
    }
}
