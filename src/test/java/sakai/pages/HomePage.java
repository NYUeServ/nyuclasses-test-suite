package sakai.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sakai.utilities.JSWaiter;

import static org.junit.Assert.assertTrue;

public class HomePage extends BasePage{

    private final By bannerSelector = By.className("Mrphs-topHeader");
    private final By newFeatureAcknowledgeSelector = By.id("popup-acknowledged-button");
    private final By profileSelector = By.className("Mrphs-userNav__submenuitem--username");
    private final By logoutButtonSelector = By.id("loginLink1");

    public HomePage(WebDriver driver) { super(driver); }

    public void checkForSakaiBanner()
    {
        JSWaiter.waitUntilJQueryReady();
        WebElement banner = driver.findElement(bannerSelector);
        assertTrue(banner.isDisplayed());
    }

    public void closeNewFeaturePopUp()
    {
        JSWaiter.waitUntilJQueryReady();
        WebElement acknowledge = driver.findElement(newFeatureAcknowledgeSelector);
        acknowledge.click();
    }

    public LoginPage logout()
    {
        JSWaiter.waitUntilJQueryReady();
        WebElement profile = driver.findElement(profileSelector);
        WebElement logout = driver.findElement(logoutButtonSelector);

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
