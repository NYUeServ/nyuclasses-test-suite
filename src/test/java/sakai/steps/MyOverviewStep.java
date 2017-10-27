package sakai.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sakai.utilities.BrowserAPI;
import sakai.utilities.SakaiLogger;

public class MyOverviewStep extends BrowserAPI {

    private BrowserAPI browser;

    public MyOverviewStep(BrowserAPI browser)
    {
        this.browser = browser;
    }

    @Then("^I should see \"([^\"]*)\" on home page$")
    public void iShouldSee(String frameName) {
        SakaiLogger.logInfo("Step: I should see \"" + frameName + "\" on home page");
        browser.getHomePage().overview.checkForOverviewFrames(frameName);
    }
}
