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
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(usernameFieldSelector));
        WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(passwordFieldSelector));
        WebElement login = wait.until(ExpectedConditions.presenceOfElementLocated(loginButtonSelector));

        User student = UserFactory.getValidStudent();
        SakaiLogger.logInfo("Logging into Student account with username: " + student.getUsername() + ", password: " + student.getPassword());
        username.sendKeys(student.getUsername());
        password.sendKeys(student.getPassword());
        login.click();

        SakaiLogger.logInfo("Login request submitted");
        PageWaiter.waitUntilPageReady();
        assertEquals("NYU Classes : My Workspace : Overview", driver.getTitle());
        return new HomePage(driver);
    }

    public HomePage loginAsInstructor()
    {
        SakaiLogger.logInfo("Finding Web Elements on page...");
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(usernameFieldSelector));
        WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(passwordFieldSelector));
        WebElement login = wait.until(ExpectedConditions.presenceOfElementLocated(loginButtonSelector));

        User instructor = UserFactory.getValidInstructor();
        SakaiLogger.logInfo("Logging into Instructor account with username: " + instructor.getUsername() + ", password: " + instructor.getPassword());
        username.sendKeys(instructor.getUsername());
        password.sendKeys(instructor.getPassword());
        login.click();

        SakaiLogger.logInfo("Login request submitted");
        PageWaiter.waitUntilPageReady();
        assertEquals("NYU Classes : My Workspace : Overview", driver.getTitle());
        return new HomePage(driver);
    }

    public void checkForLoggedOutBanner()
    {
        PageWaiter.waitUntilJQueryReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement logoutBanner = wait.until(ExpectedConditions.presenceOfElementLocated(logoutBannerSelector));
        assertTrue(logoutBanner.isDisplayed());
    }
}
