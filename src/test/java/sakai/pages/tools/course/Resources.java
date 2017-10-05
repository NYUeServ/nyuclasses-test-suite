package sakai.pages.tools.course;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.pages.CoursePage;
import sakai.pages.tools.Tool;
import sakai.utilities.SakaiLogger;

public class Resources extends Tool{

    private final CoursePage parent;
    private final WebDriver driver;

    private final By resourcesToolSelector = By.cssSelector("a[title=\"Resources \"]");

    public Resources (CoursePage parent, WebDriver driver)
    {
        super(parent, driver);
        this.parent = parent;
        this.driver = driver;
    }

    public void navigate()
    {
        SakaiLogger.logDebug("Finding Resources tool element...");
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement resources = wait.until(ExpectedConditions.presenceOfElementLocated(resourcesToolSelector));
        SakaiLogger.logDebug("Clicking on Resources tool...");
        resources.click();
    }
}
