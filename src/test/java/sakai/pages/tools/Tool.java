package sakai.pages.tools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.pages.BasePage;
import sakai.utilities.PageWaiter;
import sakai.utilities.SakaiLogger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class Tool {

    protected final WebDriver driver;

    public Tool (WebDriver driver)
    {
        this.driver = driver;
    }

    public abstract void navigate();

    public void navigateToToolWithName(String toolName)
    {
        SakaiLogger.logDebug("Finding " + toolName + " tool element...");
        WebDriverWait wait = new WebDriverWait(driver,10);
        By toolSelector = By.cssSelector("a[title=\"" + toolName + " \"]");
        WebElement tool = wait.until(ExpectedConditions.presenceOfElementLocated(toolSelector));
        SakaiLogger.logDebug("Clicking on " + toolName + " tool...");
        tool.click();
    }

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
