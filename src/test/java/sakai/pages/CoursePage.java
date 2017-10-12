package sakai.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.pages.tools.course.*;
import sakai.utilities.PageWaiter;
import sakai.utilities.SakaiLogger;
import org.openqa.selenium.support.ui.Select;

public class CoursePage extends BasePage {

    public final Announcements announcements = new Announcements(this, driver);
    public final Assignments assignments = new Assignments(this, driver);
    public final Calendar calendar = new Calendar(this, driver);
    public final Forums forums = new Forums(this, driver);
    public final Help help = new Help(this, driver);
    public final Lessons lessons = new Lessons(this, driver);
    public final Messages messages = new Messages(this, driver);
    public final NewGradebook newGradebook = new NewGradebook(this, driver);
    public final NYULibraries nyuLibraries = new NYULibraries(this, driver);
    public final Overview overview = new Overview(this, driver);
    public final Resources resources = new Resources(this, driver);
    public final Settings settings = new Settings(this, driver);
    public final SiteInformation siteInformation = new SiteInformation(this, driver);
    public final Statistics statistics = new Statistics(this, driver);
    public final Syllabus syllabus = new Syllabus(this, driver);
    public final TestsAndQuizzes testsAndQuizzes = new TestsAndQuizzes(this, driver);

    public CoursePage(WebDriver driver) {
        super(driver);
        PageWaiter.waitUntilPageReady();
    }

    public CoursePage navigateToPage(String courseTitle) {
        SakaiLogger.logDebug("Navigating to course page...");
        PageWaiter.waitUntilPageReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        driver.findElement(By.xpath("//*[@id=\"topnav\"]/li/a[@title='"+ courseTitle +"']")).click();
        return this;
    }

    public  CoursePage createNewAssignment(String assignmentTitle){
        SakaiLogger.logDebug("Creating a new Assignment...");
        PageWaiter.waitUntilPageReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement title = driver.findElement(By.id("new_assignment_title"));
        title.sendKeys(assignmentTitle);
        if ( !driver.findElement(By.id("allowResToggle")).isSelected() )
        {
            driver.findElement(By.id("allowResToggle")).click();
        }
        wait = new WebDriverWait(driver,1);
        Select numberOfResubmissions = new Select(driver.findElement(By.id("allowResubmitNumber")));
        numberOfResubmissions.selectByVisibleText("Unlimited");
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"cke_1_contents\"]/iframe")));
        WebElement assignmentInstructions = driver.findElement(By.xpath("/html/body"));
        assignmentInstructions.sendKeys("This is a test assignment for automated testing");
        driver.switchTo().defaultContent();
        if ( !driver.findElement(By.id("new_assignment_check_add_honor_pledge")).isSelected() )
        {
            driver.findElement(By.id("new_assignment_check_add_honor_pledge")).click();
        }
        driver.findElement(By.id("newAssignmentForm")).click();
        return this;
    }

    public CoursePage deleteAssignment(String assignmentTitle)
    {
        SakaiLogger.logDebug("Deleting Assignment...");
        PageWaiter.waitUntilPageReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        driver.findElement(By.xpath("//*[@id=\"col1\"]/div/div/form/div/table/tbody/tr[contains(., '"+ assignmentTitle +"')]/td/input")).click();
        driver.findElement(By.id("btnRemove")).click();
        By deleteSelector = By.id("delete");
        WebElement delete = wait.until(ExpectedConditions.presenceOfElementLocated(deleteSelector));
        delete.click();
        return this;
    } 

    public CoursePage submitAssignment(String assignmentTitle) 
    {
        SakaiLogger.logDebug("Submitting the Assignment...");
        PageWaiter.waitUntilPageReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        driver.findElement(By.xpath("//*[@id=\"col1\"]/div/div/form/div/table/tbody/tr[contains(.,'"+ assignmentTitle +"'')]/td[2]/h4/a")).click();
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"cke_1_contents\"]/iframe")));
        WebElement assignmentText = driver.findElement(By.xpath("/html/body"));
        assignmentText.sendKeys("This is a test assignment submission for automated testing");
        driver.switchTo().defaultContent();
        if ( !driver.findElement(By.id("Assignment.view_submission_honor_pledge_yes")).isSelected() )
        {
            driver.findElement(By.id("Assignment.view_submission_honor_pledge_yes")).click();
        }
        driver.findElement(By.id("post")).click();
        return this;
    }

    public CoursePage navigateToTab(String tabName)
    {
        SakaiLogger.logDebug("Finding tab on page...");
        PageWaiter.waitUntilPageReady();
        By tabsSelector = By.xpath("//*[@id=\"col1\"]/div/div/ul/li/span/a[contains(.,'" + tabName + "')]");
        WebElement tab = driver.findElement(tabsSelector);
        tab.click();  
        return this;
    }

    @Override
    public CoursePage navigateToPage() {
        SakaiLogger.logDebug("Navigating to course page...");
        PageWaiter.waitUntilPageReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        By courseSelector = By.xpath("//*[@id=\"topnav\"]/li/a[@title='Cucumber Test Site']");
        WebElement course = wait.until(ExpectedConditions.presenceOfElementLocated(courseSelector));
        course.click();
        return this;
    }
}
