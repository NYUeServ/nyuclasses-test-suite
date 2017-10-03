package sakai.utilities;

import org.openqa.selenium.WebDriver;

public class Util {

    private static String platform;
    private static WebDriver driver;

    public static void setPlatform(String platform){
        Util.platform = platform;
    }

    public static String getPlatform()
    {
        return Util.platform;
    }

    public static void setDriver(WebDriver driver)
    {
        Util.driver = driver;
    }

    public static WebDriver getDriver()
    {
        return Util.driver;
    }

}
