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

public class MyProfile extends Tool{

    private final HomePage parent;
    private final WebDriver driver;

    private final By myProfileToolSelector = By.cssSelector("a[title=\"My Profile \"]");

    public MyProfile (HomePage parent, WebDriver driver)
    {
        super(parent, driver);
        this.parent = parent;
        this.driver = driver;
    }

    public void navigate()
    {
        SakaiLogger.logDebug("Finding My Profile tool element...");
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement myprofile = wait.until(ExpectedConditions.presenceOfElementLocated(myProfileToolSelector));
        SakaiLogger.logDebug("Clicking on My Profile tool...");
        myprofile.click();
    }

    public void checkProfileName(String name)
    {
        SakaiLogger.logDebug("Finding Web Elements on page...");
        PageWaiter.waitUntilPageReady();
        By myProfileNameSelector = By.id("profileHeadingName");
        WebElement nameElement = driver.findElement(myProfileNameSelector);
        assertEquals(name, nameElement.getText());
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
