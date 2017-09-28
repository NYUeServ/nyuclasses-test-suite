package sakai.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sakai.utilities.JSWaiter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginPage extends BasePage {

    private final By usernameFieldSelector = By.name("j_username");
    private final By passwordFieldSelector = By.name("j_password");
    private final By loginButtonSelector = By.name("_eventId_proceed");
    private final By logoutBannerSelector = By.className("logout");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public HomePage loginAsStudent()
    {

        JSWaiter.waitUntilJQueryReady();
        WebElement username = driver.findElement(usernameFieldSelector);
        WebElement password = driver.findElement(passwordFieldSelector);
        WebElement login = driver.findElement(loginButtonSelector);

        username.sendKeys(System.getenv("sakai_student_username"));
        password.sendKeys(System.getenv("sakai_student_password"));
        login.click();

        JSWaiter.waitUntilJQueryReady();
        assertEquals("NYU Classes : My Workspace : Overview", driver.getTitle());
        return new HomePage(driver);
    }

    public HomePage loginAsInstructor()
    {
        JSWaiter.waitUntilJQueryReady();
        WebElement username = driver.findElement(usernameFieldSelector);
        WebElement password = driver.findElement(passwordFieldSelector);
        WebElement login = driver.findElement(loginButtonSelector);

        username.sendKeys(System.getenv("sakai_instructor_username"));
        password.sendKeys(System.getenv("sakai_instructor_password"));
        login.click();

        JSWaiter.waitUntilJQueryReady();
        assertEquals("NYU Classes : My Workspace : Overview", driver.getTitle());
        return new HomePage(driver);
    }

    public void checkForLoggedOutBanner()
    {
        JSWaiter.waitUntilJQueryReady();
        WebElement logoutBanner = driver.findElement(logoutBannerSelector);
        assertTrue(logoutBanner.isDisplayed());
    }
}
