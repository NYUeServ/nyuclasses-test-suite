package sakai.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sakai.pages.HomePage;
import sakai.utilities.DriverAPI;

public class MyProfileStep extends DriverAPI {

    private DriverAPI api;

    public MyProfileStep(DriverAPI api)
    {
        this.api = api;
    }

    @When("^I click on \"([^\"]*)\" tool$")
    public void iClickOnTool(String toolName)
    {
        api.getHomePage().clickOnTool(toolName);
    }

    @And("^I should see my name \"([^\"]*)\" under My Profile tab$")
    public void iShouldSeeMyNameUnderMyProfileTab(String name){
        api.getHomePage().checkProfileName(name);
    }

    @And("^I should see \"([^\"]*)\" tab$")
    public void iShouldSeeTab(String arg0) {

    }

    @When("^I click on \"([^\"]*)\" tab$")
    public void iClickOnTab(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I should see \"([^\"]*)\" under Pictures tab$")
    public void iShouldSeeUnderPicturesTab(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I should see button with name of \"([^\"]*)\"$")
    public void iShouldSeeButtonWithNameOf(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I should see \"([^\"]*)\" under Connections tab$")
    public void iShouldSeeUnderConnectionsTab(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I should see \"([^\"]*)\" under Search tab$")
    public void iShouldSeeUnderSearchTab(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I should see \"([^\"]*)\" under Privacy tab$")
    public void iShouldSeeUnderPrivacyTab(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I should see \"([^\"]*)\" under Preferences tab$")
    public void iShouldSeeUnderPreferencesTab(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
