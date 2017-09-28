package sakai.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(usernameFieldSelector));
        WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(passwordFieldSelector));
        WebElement login = wait.until(ExpectedConditions.presenceOfElementLocated(loginButtonSelector));

        username.sendKeys(System.getenv("sakai_student_username"));
        password.sendKeys(System.getenv("sakai_student_password"));
        login.click();

        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleContains("NYU Classes : My Workspace : Overview"));
        return new HomePage(driver);
    }

    public HomePage loginAsInstructor()
    {
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(usernameFieldSelector));
        WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(passwordFieldSelector));
        WebElement login = wait.until(ExpectedConditions.presenceOfElementLocated(loginButtonSelector));

        username.sendKeys(System.getenv("sakai_instructor_username"));
        password.sendKeys(System.getenv("sakai_instructor_password"));
        login.click();

        assertEquals("NYU Classes : My Workspace : Overview", driver.getTitle());
        return new HomePage(driver);
    }

    public void checkForLoggedOutBanner()
    {
        WebDriverWait wait = new WebDriverWait(driver,10);
        assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(logoutBannerSelector)).isDisplayed());
    }
}
