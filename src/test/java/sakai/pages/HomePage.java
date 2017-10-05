package sakai.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.utilities.PageWaiter;
import sakai.utilities.SakaiLogger;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class HomePage extends BasePage{

    private final By bannerSelector = By.className("Mrphs-topHeader");
    private final By newFeatureAcknowledgeSelector = By.id("popup-acknowledged-button");
    private final By profileSelector = By.className("Mrphs-userNav__submenuitem--username");
    private final By logoutButtonSelector = By.id("loginLink1");
    private final By myProfileNameSelector = By.id("profileHeadingName");

    public HomePage(WebDriver driver) { super(driver); }

    public void checkForSakaiBanner()
    {
        SakaiLogger.logDebug("Finding Web Elements on page...");
        PageWaiter.waitUntilPageReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement banner = wait.until(ExpectedConditions.presenceOfElementLocated(bannerSelector));
        assertTrue(banner.isDisplayed());
    }

    public void checkForOverviewWidget(String widgetName)
    {
        SakaiLogger.logDebug("Finding Web Elements on page...");
        PageWaiter.waitUntilPageReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        By widgetTitleSelector = By.xpath("//div/nav/h2[. = '" + widgetName + "']");
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(widgetTitleSelector));
        assertEquals(widgetName, element.getAttribute("innerText"));
    }

    public void clickOnTool(String toolName)
    {
        SakaiLogger.logDebug("Finding Web Elements on page...");
        WebDriverWait wait = new WebDriverWait(driver,10);
        By toolNameSelector = By.cssSelector("a[title=\"" + toolName + "\"]");
        WebElement tool = wait.until(ExpectedConditions.presenceOfElementLocated(toolNameSelector));
        SakaiLogger.logDebug("Clicking on " + toolName + " tool...");
        tool.click();
    }

    public void checkProfileName(String name)
    {
        SakaiLogger.logDebug("Finding Web Elements on page...");
        PageWaiter.waitUntilPageReady();
        WebElement nameElement = driver.findElement(myProfileNameSelector);
        assertEquals(name, nameElement.getText());
    }

    public void checkProfileTabVisibility(String tabName)
    {
        SakaiLogger.logDebug("Finding Web Elements on page...");
        PageWaiter.waitUntilPageReady();
        By tabsSelector = By.xpath("//*[@id=\"col1\"]/div/ul/li/span/a[contains(.,'" + tabName + "')]");
        WebElement tab = driver.findElement(tabsSelector);
        assertEquals(tabName, tab.getAttribute("innerText"));
    }

    public void clickOnProfileTab(String tabName)
    {
        SakaiLogger.logDebug("Finding Web Elements on page...");
        By tabsSelector = By.xpath("//*[@id=\"col1\"]/div/ul/li/span/a[contains(.,'" + tabName + "')]");
        WebElement tab = driver.findElement(tabsSelector);
        SakaiLogger.logDebug("Clicking on " + tabName + " tab...");
        tab.click();
    }

    public void checkButtonVisibility(String buttonName)
    {
        SakaiLogger.logDebug("Finding Web Elements on page...");
        PageWaiter.waitUntilPageReady();
        By buttonSelector = By.xpath("//input[@value='" + buttonName + "']");
        WebElement button = driver.findElement(buttonSelector);
        assertEquals(buttonName, button.getAttribute("value"));
    }

    public void checkHeadingVisibility(String headingName)
    {
        SakaiLogger.logDebug("Finding Web Elements on page...");
        PageWaiter.waitUntilPageReady();
        By headingSelector = By.xpath("//h3[contains(.,'" + headingName + "')]");
        WebElement heading = driver.findElement(headingSelector);
        assertTrue(heading.getText().contains(headingName));
    }

    public void closeNewFeaturePopUp()
    {
        SakaiLogger.logDebug("Finding Web Elements on page...");
        PageWaiter.waitUntilPageReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement profile = wait.until(ExpectedConditions.presenceOfElementLocated(profileSelector));

        //Waiting for profile to see if it is enabled
        //We can assume that if profile is not enabled, the new feature popup exists
        //If profile is enabled, it is safe to assume that there is no popup
        if(!profile.isEnabled())
        {
            WebElement acknowledge = wait.until(ExpectedConditions.presenceOfElementLocated(newFeatureAcknowledgeSelector));
            SakaiLogger.logDebug("Clicking on Acknowledge...");
            acknowledge.click();
        }

    }

    public LoginPage logout()
    {
        SakaiLogger.logDebug("Finding Web Elements on page...");
        PageWaiter.waitUntilPageReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement profile = wait.until(ExpectedConditions.presenceOfElementLocated(profileSelector));
        WebElement logout = wait.until(ExpectedConditions.presenceOfElementLocated(logoutButtonSelector));

        //If profile icon is not clickable, check for what's new popup and close it
        if(!profile.isEnabled())
        {
            closeNewFeaturePopUp();
        }
        SakaiLogger.logDebug("Clicking on Client Profile...");
        profile.click();
        SakaiLogger.logDebug("Clicking on Log Out...");
        logout.click();

        return new LoginPage(driver);
    }

}
