package sakai.steps;

import cucumber.api.java.en.Then;
import sakai.utilities.DriverAPI;
import sakai.utilities.SakaiLogger;

public class MyOverviewStep extends DriverAPI {

    private DriverAPI api;

    public MyOverviewStep(DriverAPI api)
    {
        this.api = api;
    }

    @Then("^I should see \"([^\"]*)\" on home page$")
    public void iShouldSee(String frameName) {
        SakaiLogger.logInfo("Step: I should see \"" + frameName + "\" on home page");
        api.getHomePage().overview.checkForOverviewFrames(frameName);
    }

}
