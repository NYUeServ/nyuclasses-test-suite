package sakai.pages;

import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertEquals;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage navigateToLogin()
    {
        driver.navigate().to("https://newclasses.nyu.edu");
        assertEquals("NYU Login", driver.getTitle());
        return new LoginPage(driver);
    }


}
