package sakai.pages.tools.course;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.pages.CoursePage;
import sakai.pages.tools.Tool;
import sakai.utilities.SakaiLogger;

public class NYULibraries extends Tool{

    private final CoursePage parent;
    private final WebDriver driver;

    private final By nyulibrariesToolSelector = By.cssSelector("a[title=\"NYU Libraries \"]");

    public NYULibraries (CoursePage parent, WebDriver driver)
    {
        super(parent, driver);
        this.parent = parent;
        this.driver = driver;
    }

    public void navigate()
    {
        SakaiLogger.logDebug("Finding NYU Libraries tool element...");
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement nyulibraries = wait.until(ExpectedConditions.presenceOfElementLocated(nyulibrariesToolSelector));
        SakaiLogger.logDebug("Clicking on NYU Libraries tool...");
        nyulibraries.click();
    }
}
