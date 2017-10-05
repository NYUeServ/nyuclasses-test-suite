package sakai.pages.tools.course;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.pages.CoursePage;
import sakai.pages.tools.Tool;
import sakai.utilities.SakaiLogger;

public class SiteInformation extends Tool{

    private final CoursePage parent;
    private final WebDriver driver;

    private final By siteinformationToolSelector = By.cssSelector("a[title=\"Site Information \"]");

    public SiteInformation (CoursePage parent, WebDriver driver)
    {
        super(parent, driver);
        this.parent = parent;
        this.driver = driver;
    }

    public void navigate()
    {
        SakaiLogger.logDebug("Finding Site Information tool element...");
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement siteinformation = wait.until(ExpectedConditions.presenceOfElementLocated(siteinformationToolSelector));
        SakaiLogger.logDebug("Clicking on Site Information tool...");
        siteinformation.click();
    }
}
