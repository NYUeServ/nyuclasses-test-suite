package sakai.pages.tools.course;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.pages.CoursePage;
import sakai.pages.tools.Tool;
import sakai.utilities.SakaiLogger;

public class Help extends Tool{

    private final CoursePage parent;
    private final WebDriver driver;

    private final By helpToolSelector = By.cssSelector("a[title=\"Help \"]");

    public Help (CoursePage parent, WebDriver driver)
    {
        super(parent, driver);
        this.parent = parent;
        this.driver = driver;
    }

    public void navigate()
    {
        SakaiLogger.logDebug("Finding Help tool element...");
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement help = wait.until(ExpectedConditions.presenceOfElementLocated(helpToolSelector));
        SakaiLogger.logDebug("Clicking on Help tool...");
        help.click();
    }
}
