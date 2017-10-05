package sakai.pages.tools.course;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.pages.CoursePage;
import sakai.pages.tools.Tool;
import sakai.utilities.SakaiLogger;

public class Calendar extends Tool{

    private final CoursePage parent;
    private final WebDriver driver;

    private final By calendarToolSelector = By.cssSelector("a[title=\"Calendar \"]");

    public Calendar (CoursePage parent, WebDriver driver)
    {
        super(parent, driver);
        this.parent = parent;
        this.driver = driver;
    }

    public void navigate()
    {
        SakaiLogger.logDebug("Finding Calendar tool element...");
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement calendar = wait.until(ExpectedConditions.presenceOfElementLocated(calendarToolSelector));
        SakaiLogger.logDebug("Clicking on Calendar tool...");
        calendar.click();
    }
}
