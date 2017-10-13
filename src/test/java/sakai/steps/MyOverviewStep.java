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

    @When("^I click on \"([^\"]*)\" tool$")
    public void iClickOnTool(String toolName)
    {
        SakaiLogger.logInfo("Step: I click on \"" + toolName + "\" tool");

        // IMPORTANT DIFFERENCE:
        // My Overview stands for the tool in HomePage
        // Course Overview stands for the tool in CoursePage
        switch(toolName)
        {
            case "My Overview":
                browser.getHomePage().overview.navigate();
                break;
            case "My Profile":
                browser.getHomePage().profile.navigate();
                break;
            case "Assignments":
                browser.getCoursePage().assignments.navigate();
                break;
            default:
                SakaiLogger.logErr("Tool of name " + toolName + " not found or implemented");
                break;
        }
    }

}
