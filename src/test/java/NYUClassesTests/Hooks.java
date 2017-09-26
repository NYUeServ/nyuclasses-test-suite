package NYUClassesTests;

import Base.BaseUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Hooks extends BaseUtil{

    private BaseUtil base;

    public Hooks(BaseUtil base){
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
            //Take screenshot
            System.out.println(scenario.getName());
        }
        //System.out.println("Closing the browser");

        //driver.close();

        System.out.println("Cleaning the environment");
        System.out.println("=========== TEST FINISHED ===========");
    }

}
