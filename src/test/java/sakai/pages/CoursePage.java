package sakai.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

    public final By courseSandboxSelector = By.xpath("//*[@id='topnav']/li[2]/a[@title='Tech Team Sandbox']");

    public CoursePage(WebDriver driver) {
        super(driver);
        PageWaiter.waitUntilPageReady();
    }

    @Override
    public CoursePage navigateToPage() {
        SakaiLogger.logDebug("Navigating to course sandbox page...");
        PageWaiter.waitUntilPageReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement course = wait.until(ExpectedConditions.presenceOfElementLocated(courseSandboxSelector));
        course.click();
        return this;
    }
}
