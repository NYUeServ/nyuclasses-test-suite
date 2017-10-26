package sakai.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sakai.pages.HomePage;
import sakai.pages.CoursePage;
import sakai.utilities.BrowserAPI;
import sakai.utilities.SakaiLogger;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class AssignmentStep extends BrowserAPI {

    private BrowserAPI browser;

    public AssignmentStep(BrowserAPI browser)
    {
        this.browser = browser;
    }

    @And("^I navigate to \"([^\"]*)\" course site$")
    public void iNavigateToCourseSite(String courseTitle)
    {
        SakaiLogger.logInfo("Step: I navigate to the "+ courseTitle +" tab");
        CoursePage coursePage = new CoursePage(browser.getDriver());
        browser.setCoursePage(coursePage).navigateToPage(courseTitle);

    }

    @And("^I navigate to the \"([^\"]*)\" tab$")
    public void iNavigateToTheTab(String tabName)
    {
        SakaiLogger.logInfo("Step: I navigate to the \""+ tabName +"\" tab");
        browser.getCoursePage().navigateToTab(tabName);
    }

    @And("^I create a new assignment \"([^\"]*)\" with open date \"([^\"]*)\"$")
    public void iCreateANewAssignment(String assignmentTitle, String openDate)
    {
        SakaiLogger.logInfo("Step: I create a new assignment \""+ assignmentTitle +"\"");
        browser.getCoursePage().assignments.createNewAssignment(assignmentTitle +  " " + browser.getPlatform(),openDate);
    }

    @Then("^I should see \"([^\"]*)\"$")
    public void iShouldSee(String needle)
    {
        SakaiLogger.logInfo("Step: I should see \""+ needle + "\"");
        List<WebElement> list = browser.getDriver().findElements(By.xpath("//*[contains(text(),'" + needle + "')]"));
        assertTrue("Text not found!", list.size() > 0);
    }

    @When("^I submit my assignment \"([^\"]*)\"$")
    public void iSubmitMyAssignment(String assignmentTitle)
    {
        SakaiLogger.logInfo("Step: I submit my assignment \"" + assignmentTitle + "\"");
        browser.getCoursePage().assignments.submitInlineAssignment(assignmentTitle +  " " + browser.getPlatform());
    }

    @When("^I remove the assignment \"([^\"]*)\"$")
    public void iRemoveTheAssignment(String assignmentTitle)
    {
        SakaiLogger.logInfo("Step: I remove assignment \""+ assignmentTitle + "\"");
        browser.getCoursePage().assignments.deleteAssignment(assignmentTitle +  " " + browser.getPlatform());
    }
}