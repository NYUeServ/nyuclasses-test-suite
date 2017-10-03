package sakai.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.utilities.PageWaiter;
import sakai.utilities.SakaiLogger;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class HomePage extends BasePage{

    private final By bannerSelector = By.className("Mrphs-topHeader");
    private final By newFeatureAcknowledgeSelector = By.id("popup-acknowledged-button");
    private final By profileSelector = By.className("Mrphs-userNav__submenuitem--username");
    private final By logoutButtonSelector = By.id("loginLink1");
    private final By myProfileNameSelector = By.id("profileHeadingName");

    public HomePage(WebDriver driver) { super(driver); }

    public void checkForSakaiBanner()
    {
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement banner = wait.until(ExpectedConditions.presenceOfElementLocated(bannerSelector));
        assertTrue(banner.isDisplayed());
    }

    public void checkForOverviewWidget(String widgetName)
    {
        WebDriverWait wait = new WebDriverWait(driver,10);
        By widgetTitleSelector = By.xpath("//div/nav/h2[. = '" + widgetName + "']");
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(widgetTitleSelector));
        assertEquals(widgetName, element.getAttribute("innerText"));
    }

    public void clickOnTool(String toolName)
    {
        WebDriverWait wait = new WebDriverWait(driver,10);
        By toolNameSelector = By.cssSelector("a[title=\"" + toolName + "\"]");
        WebElement tool = wait.until(ExpectedConditions.presenceOfElementLocated(toolNameSelector));
        tool.click();
    }

    public void checkProfileName(String name)
    {
        WebElement nameElement = driver.findElement(myProfileNameSelector);
        assertEquals(name, nameElement.getText());
    }

    public void checkTabVisibility(String tabName)
    {
        
    }

    public void closeNewFeaturePopUp()
    {
        PageWaiter.waitUntilPageReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement profile = wait.until(ExpectedConditions.presenceOfElementLocated(profileSelector));

        //Waiting for profile to see if it is enabled
        //We can assume that if profile is not enabled, the new feature popup exists
        //If profile is enabled, it is safe to assume that there is no popup
        if(!profile.isEnabled())
        {
            WebElement acknowledge = wait.until(ExpectedConditions.presenceOfElementLocated(newFeatureAcknowledgeSelector));
            acknowledge.click();
        }

    }

    public LoginPage logout()
    {
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement profile = wait.until(ExpectedConditions.presenceOfElementLocated(profileSelector));
        WebElement logout = wait.until(ExpectedConditions.presenceOfElementLocated(logoutButtonSelector));

        //If profile icon is not clickable, check for what's new popup and close it
        if(!profile.isEnabled())
        {
            closeNewFeaturePopUp();
        }
        profile.click();
        logout.click();

        return new LoginPage(driver);
    }

}
