package sakai.utilities;

import org.apache.log4j.Logger;

public class SakaiLogger {

    private static Logger log;

    public static void logInfo(String info)
    {
        if(log == null)
        {
            log = org.apache.log4j.Logger.getRootLogger();
        }
        log.info(info + "\n");
    }

    public static void logErr(String err)
    {
        if(log == null)
        {
            log = org.apache.log4j.Logger.getRootLogger();
        }
        log.error(err + "\n");
    }
}
