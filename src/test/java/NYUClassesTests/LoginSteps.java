package NYUClassesTests;

import Base.BaseUtil;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginSteps extends BaseUtil{

    private BaseUtil base;

    public LoginSteps(BaseUtil base)
    {
        this.base = base;
    }

    @Given("^I navigate to the login page$")
    public void iNavigateToTheLoginPage() throws Throwable {
        base.driver.get("https://newclasses.nyu.edu");
    }

    @And("^I enter ([^\"]*) and ([^\"]*)$")
    public void iEnterNetidAndPassword(String netid, String password) throws Throwable {
        WebDriverWait wait = new WebDriverWait(base.driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("j_username"))).sendKeys(netid);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("j_password"))).sendKeys(password);
    }

    @And("^I press \"([^\"]*)\"$")
    public void iPress(String elementname) throws Throwable {
        base.driver.findElement(By.name(elementname)).click();
    }

    @When("^I log out$")
    public void iLogOut() throws Throwable {
        base.driver.findElement(By.className("Mrphs-userNav__submenuitem--username")).click();
        base.driver.findElement(By.id("loginLink1")).click();
    }

    @Then("^I should see NYU Classes logo$")
    public void iShouldSeeNYUClassesLogo() throws Throwable {
        WebDriverWait wait = new WebDriverWait(base.driver,10);
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.className("Mrphs-topHeader"))).isDisplayed());
    }

    @Then("^I should see logged out$")
    public void iShouldSeeLoggedOut() throws Throwable {
        WebDriverWait wait = new WebDriverWait(base.driver,10);
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.className("logout"))).isDisplayed());
    }
}
