package sakai.pages.tools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.pages.BasePage;
import sakai.pages.LoginPage;
import sakai.utilities.PageWaiter;
import sakai.utilities.SakaiLogger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class Tool {

    private final BasePage parent;
    private final WebDriver driver;

    public Tool (BasePage parent, WebDriver driver)
    {
        this.parent = parent;
        this.driver = driver;
    }

    public abstract void navigate();

    public void checkButtonVisibility(String buttonName)
    {
        SakaiLogger.logDebug("Finding Button Elements on page...");
        PageWaiter.waitUntilPageReady();
        By buttonSelector = By.xpath("//input[@value='" + buttonName + "']");
        WebElement button = driver.findElement(buttonSelector);
        assertEquals(buttonName, button.getAttribute("value"));
    }

    public void checkH3HeadingVisibility(String headingName)
    {
        SakaiLogger.logDebug("Finding <h3> Elements on page...");
        PageWaiter.waitUntilPageReady();
        By headingSelector = By.xpath("//h3[contains(.,'" + headingName + "')]");
        WebElement heading = driver.findElement(headingSelector);
        assertTrue(heading.getText().contains(headingName));
    }

}
