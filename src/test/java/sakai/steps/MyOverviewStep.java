package sakai.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import sakai.pages.BasePage;
import sakai.pages.HomePage;
import sakai.pages.LoginPage;
import sakai.utilities.DriverAPI;

public class MyOverviewStep extends DriverAPI {

    private DriverAPI api;

    public MyOverviewStep(DriverAPI api)
    {
        this.api = api;
    }

    @Then("^I should see \"([^\"]*)\"$")
    public void iShouldSee(String frameName) {
        switch(frameName)
        {
            case "NYU Classes Info":

        }
    }
}
