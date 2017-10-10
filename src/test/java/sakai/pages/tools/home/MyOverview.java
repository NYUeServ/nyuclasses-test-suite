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

import static org.junit.Assert.assertEquals;

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

    public void checkForOverviewFrames(String widgetName)
    {
        SakaiLogger.logDebug("Finding Web Elements on page...");
        PageWaiter.waitUntilPageReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        By widgetTitleSelector = By.xpath("//div/nav/h2[. = '" + widgetName + "']");
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(widgetTitleSelector));
        assertEquals(widgetName, element.getAttribute("innerText"));
    }

}
