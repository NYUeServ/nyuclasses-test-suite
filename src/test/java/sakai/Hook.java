package sakai;

import containers.BaseUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Hook extends BaseUtil{

    private BaseUtil base;

    public Hook(BaseUtil base){
        this.base = base;
    }

    @Before
    public void initializeEnvironment()
    {
        System.out.println("=========== INITIALIZE TEST ===========");
        FirefoxDriverManager.getInstance().setup();
        base.driver = new FirefoxDriver();
        base.driver.manage().deleteAllCookies();

        System.out.println("Initializing testing environment");
        System.out.println("Using driver: Firefox");
        System.out.println("Opening the browser");
    }

    @After
    public void tearDownEnvironment(Scenario scenario)
    {
        if(scenario.isFailed())
        {
            //TODO: Take screenshot
            System.out.println(scenario.getName());
        }

        System.out.println("Cleaning the environment");
        base.driver.manage().deleteAllCookies();
        base.driver.quit();
        System.out.println("=========== TEST FINISHED ===========");
    }

}
