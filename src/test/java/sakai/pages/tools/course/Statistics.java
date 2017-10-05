package sakai.pages.tools.course;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.pages.CoursePage;
import sakai.pages.tools.Tool;
import sakai.utilities.SakaiLogger;

public class Statistics extends Tool{

    private final CoursePage parent;
    private final WebDriver driver;

    private final By statisticsToolSelector = By.cssSelector("a[title=\"Statistics \"]");

    public Statistics (CoursePage parent, WebDriver driver)
    {
        super(parent, driver);
        this.parent = parent;
        this.driver = driver;
    }

    public void navigate()
    {
        SakaiLogger.logDebug("Finding Statistics tool element...");
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement statistics = wait.until(ExpectedConditions.presenceOfElementLocated(statisticsToolSelector));
        SakaiLogger.logDebug("Clicking on Statistics tool...");
        statistics.click();
    }
}
