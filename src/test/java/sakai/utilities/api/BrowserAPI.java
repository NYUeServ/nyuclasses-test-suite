package sakai.utilities.api;

import org.openqa.selenium.WebDriver;
import sakai.pages.BasePage;
import sakai.pages.HomePage;
import sakai.pages.LoginPage;
import sakai.pages.CoursePage;

/**
 * This is the class used by cucumber picocontainer
 * An instance of this class will be passed to all step definitions so they are using the same page resources
 *
 * The setters of this class will also return a copy of what is set, for the sake of easier chaining commands
 *
 * This class manages the current active page objects throughout the test suite
 */
public class BrowserAPI {

    private String platform;
    private WebDriver driver;
    private LoginPage login;
    private HomePage home;
    private CoursePage coursePage;

    public void setPlatform(String platform){
        this.platform = platform;
    }

    public String getPlatform()
    {
        return this.platform;
    }

    public void setDriver(WebDriver driver)
    {
        this.driver = driver;
    }

    public WebDriver getDriver()
    {
        return this.driver;
    }

    public LoginPage setLoginPage(LoginPage login)
    {
        this.login = login;
        return login;
    }

    public LoginPage getLoginPage()
    {
        return this.login;
    }

    public HomePage setHomePage(HomePage home)
    {
        this.home = home;
        return home;
    }

    public HomePage getHomePage()
    {
        return this.home;
    }

    public CoursePage setCoursePage(CoursePage coursePage)
    {
        this.coursePage = coursePage;
        return coursePage;
    }
    public CoursePage getCoursePage()
    {
        return this.coursePage;
    }
}
