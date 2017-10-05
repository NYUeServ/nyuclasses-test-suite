package sakai.pages.tools.course;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.pages.CoursePage;
import sakai.pages.tools.Tool;
import sakai.utilities.SakaiLogger;

public class Messages extends Tool{

    private final CoursePage parent;
    private final WebDriver driver;

    private final By messagesToolSelector = By.cssSelector("a[title=\"Messages \"]");

    public Messages (CoursePage parent, WebDriver driver)
    {
        super(parent, driver);
        this.parent = parent;
        this.driver = driver;
    }

    public void navigate()
    {
        SakaiLogger.logDebug("Finding Messages tool element...");
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement messages = wait.until(ExpectedConditions.presenceOfElementLocated(messagesToolSelector));
        SakaiLogger.logDebug("Clicking on Messages tool...");
        messages.click();
    }
}
