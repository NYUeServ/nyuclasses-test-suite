package sakai.utilities;

public class Configuration {

    private static String platform;

    public static void setPlatform(String platform){
        Configuration.platform = platform;
    }

    public static String getPlatform()
    {
        return Configuration.platform;
    }
}
