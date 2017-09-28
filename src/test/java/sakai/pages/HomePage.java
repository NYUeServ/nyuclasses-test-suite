package sakai.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;

public class HomePage extends BasePage{

    private final By bannerSelector = By.className("Mrphs-topHeader");
    private final By profileSelector = By.className("Mrphs-userNav__submenuitem--username");
    private final By logoutButtonSelector = By.id("loginLink1");

    public HomePage(WebDriver driver) { super(driver); }

    public void checkForSakaiBanner()
    {
        WebDriverWait wait = new WebDriverWait(driver,10);
        assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(bannerSelector)).isDisplayed());
    }

    public LoginPage logout()
    {
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement profile = wait.until(ExpectedConditions.presenceOfElementLocated(profileSelector));
        WebElement logout = wait.until(ExpectedConditions.presenceOfElementLocated(logoutButtonSelector));

        profile.click();
        logout.click();

        return new LoginPage(driver);
    }

}
