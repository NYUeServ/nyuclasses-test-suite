package sakai.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sakai.utilities.DriverAPI;
import sakai.utilities.SakaiLogger;

public class MyProfileStep extends DriverAPI {

    private DriverAPI api;

    public MyProfileStep(DriverAPI api)
    {
        this.api = api;
    }

    @When("^I click on \"([^\"]*)\" tool$")
    public void iClickOnTool(String toolName)
    {
        SakaiLogger.logInfo("Step: I click on \"" + toolName + "\" tool");
        api.getHomePage().clickOnTool(toolName);
    }

    @And("^I should see my name \"([^\"]*)\" under My Profile tab$")
    public void iShouldSeeMyNameUnderMyProfileTab(String name){
        SakaiLogger.logInfo("Step: I should see my name \"" + name + "\" under My Profile tab");
        api.getHomePage().checkProfileName(name);
    }

    @And("^I should see \"([^\"]*)\" tab$")
    public void iShouldSeeTab(String tabName) {
        SakaiLogger.logInfo("Step: I should see \"" + tabName + "\" tab");
        api.getHomePage().checkProfileTabVisibility(tabName);
    }

    @When("^I click on \"([^\"]*)\" tab$")
    public void iClickOnTab(String tabName) {
        SakaiLogger.logInfo("Step: I click on \"" + tabName + "\" tab");
        api.getHomePage().clickOnProfileTab(tabName);
    }

    @And("^I should see button with name of \"([^\"]*)\"$")
    public void iShouldSeeButtonWithNameOf(String buttonName) {
        SakaiLogger.logInfo("Step: I should see button with name of \"" + buttonName + "\"");
        api.getHomePage().checkButtonVisibility(buttonName);
    }

    @Then("^I should see \"([^\"]*)\" as heading$")
    public void iShouldSeeAsHeading(String headingName) {
        SakaiLogger.logInfo("Step: I should see \"" + headingName + "\" as heading");
        api.getHomePage().checkHeadingVisibility(headingName);
    }
}
