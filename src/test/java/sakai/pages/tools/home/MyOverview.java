package sakai.pages.tools.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.pages.HomePage;
import sakai.pages.tools.Tool;
import sakai.utilities.PageWaiter;
import sakai.utilities.SakaiLogger;

import static org.junit.Assert.assertTrue;

public class MyOverview extends Tool{

    private final HomePage parent;
    private final WebDriver driver;

    public MyOverview (HomePage parent, WebDriver driver)
    {
        super(driver);
        this.parent = parent;
        this.driver = driver;
    }

    public void navigate()
    {
        this.navigateToToolWithName("Overview");
    }

    public void checkForContent()
    {
        SakaiLogger.logDebug("Finding content information page...");
        PageWaiter.waitUntilPageReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        By contentSelector = By.id("content");
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(contentSelector));
        assertTrue(element.isDisplayed());
    }

}
