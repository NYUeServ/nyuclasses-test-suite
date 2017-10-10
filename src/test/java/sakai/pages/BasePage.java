package sakai.pages;

import org.openqa.selenium.WebDriver;
import sakai.utilities.PageWaiter;
import static org.junit.Assert.assertEquals;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public abstract BasePage navigateToPage();

}
