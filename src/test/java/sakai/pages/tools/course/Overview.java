package sakai.pages.tools.course;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.pages.CoursePage;
import sakai.pages.tools.Tool;
import sakai.utilities.SakaiLogger;

public class Overview extends Tool{

    private final CoursePage parent;
    private final WebDriver driver;

    private final By overviewToolSelector = By.cssSelector("a[title=\"Overview \"]");

    public Overview (CoursePage parent, WebDriver driver)
    {
        super(parent, driver);
        this.parent = parent;
        this.driver = driver;
    }

    public void navigate()
    {
        SakaiLogger.logDebug("Finding Overview tool element...");
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement overview = wait.until(ExpectedConditions.presenceOfElementLocated(overviewToolSelector));
        SakaiLogger.logDebug("Clicking on Overview tool...");
        overview.click();
    }
}
