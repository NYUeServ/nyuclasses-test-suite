package sakai.pages.tools.course;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.pages.CoursePage;
import sakai.pages.tools.Tool;
import sakai.utilities.SakaiLogger;

public class Lessons extends Tool{

    private final CoursePage parent;
    private final WebDriver driver;

    private final By lessonsToolSelector = By.cssSelector("a[title=\"Lessons \"]");

    public Lessons (CoursePage parent, WebDriver driver)
    {
        super(parent, driver);
        this.parent = parent;
        this.driver = driver;
    }

    public void navigate()
    {
        SakaiLogger.logDebug("Finding Lessons tool element...");
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement lessons = wait.until(ExpectedConditions.presenceOfElementLocated(lessonsToolSelector));
        SakaiLogger.logDebug("Clicking on Lessons tool...");
        lessons.click();
    }
}
