package sakai.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.utilities.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginPage extends BasePage {

    private String loginURL;
    private final By usernameFieldSelector = By.name("j_username");
    private final By passwordFieldSelector = By.name("j_password");
    private final By loginButtonSelector = By.name("_eventId_proceed");
    private final By logoutBannerSelector = By.className("logout");

    public LoginPage(WebDriver driver, String loginURL) {
        super(driver);
        this.loginURL = loginURL;
    }

    @Override
    public LoginPage navigateToPage()
    {
        driver.navigate().to(loginURL);
        PageWaiter.waitUntilPageReady();
        assertEquals("NYU Login", driver.getTitle());
        return this;
    }


    public HomePage loginAsUser(User user)
    {
        SakaiLogger.logDebug("Finding Web Elements on page...");
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(usernameFieldSelector));
        WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(passwordFieldSelector));
        WebElement login = wait.until(ExpectedConditions.presenceOfElementLocated(loginButtonSelector));

        SakaiLogger.logDebug("Logging into " + user.getRole() + " account with username: " + user.getUsername() + ", password: " + user.getPassword());
        username.sendKeys(user.getUsername());
        password.sendKeys(user.getPassword());
        login.click();
        SakaiLogger.logDebug("Login request submitted");

        PageWaiter.waitUntilPageReady();
        assertEquals("NYU Classes : My Workspace : Overview", driver.getTitle());

        return new HomePage(driver);
    }

    public void checkForLoggedOutBanner()
    {
        SakaiLogger.logDebug("Finding Web Elements on page...");
        PageWaiter.waitUntilJQueryReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement logoutBanner = wait.until(ExpectedConditions.presenceOfElementLocated(logoutBannerSelector));
        assertTrue(logoutBanner.isDisplayed());
    }
}
