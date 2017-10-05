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
        log.info("[Sakai Test] - INFO: " + info + "\n");
    }

    public static void logErr(String err)
    {
        if(log == null)
        {
            log = org.apache.log4j.Logger.getRootLogger();
        }
        log.error("[Sakai Test] - ERROR: " + err + "\n");
    }

    public static void logDebug(String debug)
    {
        if(log == null)
        {
            log = org.apache.log4j.Logger.getRootLogger();
        }
        log.debug("[Sakai Test] - DEBUG: " + debug + "\n");
    }
}
