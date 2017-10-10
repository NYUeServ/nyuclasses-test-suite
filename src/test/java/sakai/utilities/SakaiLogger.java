package sakai.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SakaiLogger {

    private final static Logger log = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    public static void logInfo(String info)
    {
        log.info(info);
    }

    public static void logErr(String err)
    {
        log.error(err);
    }

    public static void logDebug(String debug)
    {
        log.debug(debug);
    }
}
