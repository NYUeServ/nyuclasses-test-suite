package sakai.pages.tools.course;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.pages.CoursePage;
import sakai.pages.tools.Tool;
import sakai.utilities.SakaiLogger;

public class NewGradebook extends Tool{

    private final CoursePage parent;
    private final WebDriver driver;

    private final By newgradebookToolSelector = By.cssSelector("a[title=\"New Gradebook \"]");

    public NewGradebook (CoursePage parent, WebDriver driver)
    {
        super(parent, driver);
        this.parent = parent;
        this.driver = driver;
    }

    public void navigate()
    {
        SakaiLogger.logDebug("Finding New Gradebook tool element...");
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement newgradebook = wait.until(ExpectedConditions.presenceOfElementLocated(newgradebookToolSelector));
        SakaiLogger.logDebug("Clicking on New Gradebook tool...");
        newgradebook.click();
    }
}
