package sakai.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import sakai.pages.tools.course.*;
import sakai.utilities.PageWaiter;
import sakai.utilities.SakaiLogger;

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
        //ToDo: Remove hardcoded course site title
        By courseSelector = By.xpath("//*[@id=\"topnav\"]/li/a[@title='Cucumber Test Site']");
        WebElement course = wait.until(ExpectedConditions.presenceOfElementLocated(courseSelector));
        course.click();
        return this;
    }

    public CoursePage navigateToPage(String courseTitle) {
        SakaiLogger.logDebug("Navigating to course page...");
        PageWaiter.waitUntilPageReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        By courseSelector = By.xpath("//*[@id=\"topnav\"]/li/a[@title='" + courseTitle + "']");
        WebElement course = wait.until(ExpectedConditions.presenceOfElementLocated(courseSelector));
        course.click();
        return this;
    }

}
