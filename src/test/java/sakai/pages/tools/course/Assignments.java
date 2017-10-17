package sakai.pages.tools.course;

import org.openqa.selenium.WebDriver;
import sakai.pages.CoursePage;
import sakai.pages.tools.Tool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sakai.utilities.PageWaiter;
import sakai.utilities.SakaiLogger;
import org.openqa.selenium.support.ui.Select;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Assignments extends Tool{

    private final CoursePage parent;

    public Assignments (CoursePage parent, WebDriver driver)
    {
        super(driver);
        this.parent = parent;
    }

    public void navigate()
    {
        this.navigateToToolWithName("Assignments");
    }
    
    public void createNewAssignment(String assignmentTitle)
    {
        SakaiLogger.logDebug("Creating a new Assignment...");
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement title = driver.findElement(By.id("new_assignment_title"));
        title.sendKeys(assignmentTitle);
        LocalDate yesterday = LocalDate.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String openDateValue = yesterday.format(formatter);
        WebElement openDate = driver.findElement(By.id("opendate"));
        openDate.clear();
        openDate.sendKeys(openDateValue);
        SakaiLogger.logInfo(openDate.getAttribute("value"));
        if ( !driver.findElement(By.id("allowResToggle")).isSelected() )
        {
            driver.findElement(By.id("allowResToggle")).click();
        }
        wait = new WebDriverWait(driver,1);
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select#allowResubmitNumber")));
        Select numberOfResubmissions = new Select(driver.findElement(By.cssSelector("select#allowResubmitNumber")));
        numberOfResubmissions.selectByVisibleText("Unlimited");
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"cke_1_contents\"]/iframe")));
        WebElement assignmentInstructions = driver.findElement(By.xpath("/html/body"));
        assignmentInstructions.sendKeys("This is a test assignment for automated testing");
        driver.switchTo().defaultContent();
        if ( !driver.findElement(By.id("new_assignment_check_add_honor_pledge")).isSelected() )
        {
            driver.findElement(By.id("new_assignment_check_add_honor_pledge")).click();
        }
        driver.findElement(By.name("post")).click();
    }

    public void deleteAssignment(String assignmentTitle)
    {
        SakaiLogger.logDebug("Deleting Assignment...");
        PageWaiter.waitUntilPageReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        driver.findElement(By.xpath("//*[@id=\"col1\"]/div/div/form/div/table/tbody/tr[contains(., '"+ assignmentTitle +"')]/td/input")).click();
        driver.findElement(By.id("btnRemove")).click();
        By deleteSelector = By.id("delete");
        WebElement delete = wait.until(ExpectedConditions.presenceOfElementLocated(deleteSelector));
        delete.click();
    } 

    public void submitAssignment(String assignmentTitle) 
    {
        SakaiLogger.logDebug("Submitting the Assignment...");
        PageWaiter.waitUntilPageReady();
        WebDriverWait wait = new WebDriverWait(driver,10);
        driver.findElement(By.xpath("//*[@id=\"col1\"]/div/div/form/div/table/tbody/tr[contains(.,'"+ assignmentTitle +"')]/td[2]/h4/a")).click();
        PageWaiter.waitUntilPageReady();
        By assignmentTextSelector =  By.xpath("//*[@id=\"cke_1_contents\"]/iframe");
        WebElement assignmentTextArea = wait.until(ExpectedConditions.presenceOfElementLocated(assignmentTextSelector));
        driver.switchTo().frame(assignmentTextArea);
        WebElement assignmentText = driver.findElement(By.xpath("/html/body"));
        assignmentText.sendKeys("This is a test assignment submission for automated testing");
        driver.switchTo().defaultContent();
        if ( !driver.findElement(By.id("Assignment.view_submission_honor_pledge_yes")).isSelected() )
        {
            driver.findElement(By.id("Assignment.view_submission_honor_pledge_yes")).click();
        }
        driver.findElement(By.id("post")).click();
    }

}
