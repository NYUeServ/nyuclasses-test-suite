package sakai.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sakai.utilities.api.Browser;
import sakai.utilities.SakaiLogger;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertTrue;

public class DefaultStep extends Browser {

    private Browser browser;

    public DefaultStep(Browser browser)
    {
        this.browser = browser;
    }

    @And("^I close new feature popup if needed$")
    public void iCloseWhatSNewPopupIfNeeded()
    {
        SakaiLogger.logInfo("Step: I close new feature popup if needed");
        browser.getHomePage().closeNewFeaturePopUp();
    }

    @Then("^I should see Sakai logo$")
    public void iShouldSeeNYUClassesLogo() {
        SakaiLogger.logInfo("Step: I should see Sakai logo");
        browser.getHomePage().checkForSakaiBanner();
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

    @Then("^I should see \"([^\"]*)\" on the page$")
    public void iShouldSee(String needle)
    {
        SakaiLogger.logInfo("Step: I should see \""+ needle + "\"");
        List<WebElement> list = browser.getDriver().findElements(By.xpath("//*[contains(text(),'" + needle + "')]"));
        assertTrue("Text not found!", list.size() > 0);
    }
}
