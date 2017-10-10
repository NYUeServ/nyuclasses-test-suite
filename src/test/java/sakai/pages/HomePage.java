package sakai.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.pages.tools.home.MyOverview;
import sakai.pages.tools.home.MyProfile;
import sakai.utilities.PageWaiter;
import sakai.utilities.SakaiLogger;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class HomePage extends BasePage{

    public final MyOverview overview = new MyOverview(this, driver);
    public final MyProfile profile = new MyProfile(this, driver);

    private final By institutionHeaderSelector = By.className("Mrphs-headerLogo--institution");
    private final By newFeatureAcknowledgeSelector = By.id("popup-acknowledged-button");
    private final By profileSelector = By.className("Mrphs-userNav__submenuitem--username");
    private final By logoutButtonSelector = By.id("loginLink1");

    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    @Override
    public HomePage navigateToPage() {
        SakaiLogger.logDebug("Navigating to home page...");
        PageWaiter.waitUntilPageReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement header = wait.until(ExpectedConditions.presenceOfElementLocated(institutionHeaderSelector));
        header.click();
        return this;
    }

    public void checkForSakaiBanner()
    {
        SakaiLogger.logDebug("Finding Sakai Banner...");
        PageWaiter.waitUntilPageReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement header = wait.until(ExpectedConditions.presenceOfElementLocated(institutionHeaderSelector));
        assertTrue(header.isDisplayed());
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
