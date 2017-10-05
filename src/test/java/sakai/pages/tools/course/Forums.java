package sakai.pages.tools.course;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.pages.CoursePage;
import sakai.pages.tools.Tool;
import sakai.utilities.SakaiLogger;

public class Forums extends Tool{
    private final CoursePage parent;
    private final WebDriver driver;

    private final By forumsToolSelector = By.cssSelector("a[title=\"Forums \"]");

    public Forums (CoursePage parent, WebDriver driver)
    {
        super(parent, driver);
        this.parent = parent;
        this.driver = driver;
    }

    public void navigate()
    {
        SakaiLogger.logDebug("Finding Forums tool element...");
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement forums = wait.until(ExpectedConditions.presenceOfElementLocated(forumsToolSelector));
        SakaiLogger.logDebug("Clicking on Forums tool...");
        forums.click();
    }
}
