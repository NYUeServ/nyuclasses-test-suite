package sakai.pages.tools.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sakai.pages.HomePage;
import sakai.pages.tools.Tool;
import sakai.utilities.PageWaiter;
import sakai.utilities.SakaiLogger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyProfile extends Tool{

    private final HomePage parent;
    private final WebDriver driver;

    public MyProfile (HomePage parent, WebDriver driver)
    {
        super(driver);
        this.parent = parent;
        this.driver = driver;
    }

    public void navigate()
    {
        this.navigateToToolWithName("My Profile");
    }

    public void checkProfileName()
    {
        SakaiLogger.logDebug("Finding Web Elements on page...");
        PageWaiter.waitUntilPageReady();
        By myProfileNameSelector = By.id("profileHeadingName");
        WebElement nameElement = driver.findElement(myProfileNameSelector);
        assertTrue(nameElement.isDisplayed());
    }

    public void checkTabVisibility(String tabName)
    {
        SakaiLogger.logDebug("Finding Web Elements on page...");
        PageWaiter.waitUntilPageReady();
        By tabsSelector = By.xpath("//*[@id=\"col1\"]/div/ul/li/span/a[contains(.,'" + tabName + "')]");
        WebElement tab = driver.findElement(tabsSelector);
        assertEquals(tabName, tab.getAttribute("innerText"));
    }

    public void clickOnTab(String tabName)
    {
        SakaiLogger.logDebug("Finding Web Elements on page...");
        By tabsSelector = By.xpath("//*[@id=\"col1\"]/div/ul/li/span/a[contains(.,'" + tabName + "')]");
        WebElement tab = driver.findElement(tabsSelector);
        SakaiLogger.logDebug("Clicking on " + tabName + " tab...");
        tab.click();
    }

    public void checkHeadingVisibility(String headingName)
    {
        this.checkH3HeadingVisibility(headingName);
    }

}
