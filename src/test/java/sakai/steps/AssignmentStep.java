package sakai.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import sakai.pages.CoursePage;
import sakai.utilities.api.Browser;
import sakai.utilities.SakaiLogger;

public class AssignmentStep extends Browser {

    private Browser browser;

    public AssignmentStep(Browser browser)
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