package sakai.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sakai.utilities.BrowserAPI;
import sakai.utilities.SakaiLogger;

public class MyProfileStep extends BrowserAPI {

    private BrowserAPI browser;

    public MyProfileStep(BrowserAPI browser)
    {
        this.browser = browser;
    }

    @And("^I should see my name \"([^\"]*)\" under My Profile tab$")
    public void iShouldSeeMyNameUnderMyProfileTab(String name){
        SakaiLogger.logInfo("Step: I should see my name \"" + name + "\" under My Profile tab");
        browser.getHomePage().profile.checkProfileName(name);
    }

    @And("^I should see \"([^\"]*)\" tab$")
    public void iShouldSeeTab(String tabName) {
        SakaiLogger.logInfo("Step: I should see \"" + tabName + "\" tab");
        browser.getHomePage().profile.checkTabVisibility(tabName);
    }

    @When("^I click on \"([^\"]*)\" tab$")
    public void iClickOnTab(String tabName) {
        SakaiLogger.logInfo("Step: I click on \"" + tabName + "\" tab");
        browser.getHomePage().profile.clickOnTab(tabName);
    }

    @And("^I should see button with name of \"([^\"]*)\"$")
    public void iShouldSeeButtonWithNameOf(String buttonName) {
        SakaiLogger.logInfo("Step: I should see button with name of \"" + buttonName + "\"");
        browser.getHomePage().profile.checkButtonVisibility(buttonName);
    }

    @Then("^I should see \"([^\"]*)\" as heading$")
    public void iShouldSeeAsHeading(String headingName) {
        SakaiLogger.logInfo("Step: I should see \"" + headingName + "\" as heading");
        browser.getHomePage().profile.checkHeadingVisibility(headingName);
    }
}
