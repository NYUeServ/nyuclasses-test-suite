package sakai.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sakai.utilities.JSWaiter;
import sakai.utilities.SakaiLogger;
import sakai.utilities.User;
import sakai.utilities.UserFactory;

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
        SakaiLogger.logInfo("Finding Web Elements on page...");
        JSWaiter.waitUntilJQueryReady();
        WebElement username = driver.findElement(usernameFieldSelector);
        WebElement password = driver.findElement(passwordFieldSelector);
        WebElement login = driver.findElement(loginButtonSelector);

        User student = UserFactory.getValidStudent();
        SakaiLogger.logInfo("Logging into Student account with username: " + student.getUsername() + ", password: " + student.getPassword());
        username.sendKeys(student.getUsername());
        password.sendKeys(student.getPassword());
        login.click();

        SakaiLogger.logInfo("Login request submitted");
        JSWaiter.waitUntilJQueryReady();
        assertEquals("NYU Classes : My Workspace : Overview", driver.getTitle());
        return new HomePage(driver);
    }

    public HomePage loginAsInstructor()
    {
        SakaiLogger.logInfo("Finding Web Elements on page...");
        JSWaiter.waitUntilJQueryReady();
        WebElement username = driver.findElement(usernameFieldSelector);
        WebElement password = driver.findElement(passwordFieldSelector);
        WebElement login = driver.findElement(loginButtonSelector);

        User instructor = UserFactory.getValidInstructor();
        SakaiLogger.logInfo("Logging into Instructor account with username: " + instructor.getUsername() + ", password: " + instructor.getPassword());
        username.sendKeys(instructor.getUsername());
        password.sendKeys(instructor.getPassword());
        login.click();

        SakaiLogger.logInfo("Login request submitted");
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
