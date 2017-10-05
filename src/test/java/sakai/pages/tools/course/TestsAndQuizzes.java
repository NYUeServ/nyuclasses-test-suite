package sakai.pages.tools.course;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.pages.CoursePage;
import sakai.pages.tools.Tool;
import sakai.utilities.SakaiLogger;

public class TestsAndQuizzes extends Tool{

    private final CoursePage parent;
    private final WebDriver driver;

    private final By testandquizzesToolSelector = By.cssSelector("a[title=\"Tests & Quizzes \"]");

    public TestsAndQuizzes (CoursePage parent, WebDriver driver)
    {
        super(parent, driver);
        this.parent = parent;
        this.driver = driver;
    }

    public void navigate()
    {
        SakaiLogger.logDebug("Finding Tests & Quizzes tool element...");
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement testandquizzes = wait.until(ExpectedConditions.presenceOfElementLocated(testandquizzesToolSelector));
        SakaiLogger.logDebug("Clicking on Tests & Quizzes tool...");
        testandquizzes.click();
    }
}
