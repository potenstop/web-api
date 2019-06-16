package top.potens.framework.log;


import org.slf4j.Logger;
import org.springframework.boot.logging.LogLevel;
import top.potens.framework.util.CurrentLineUtil;

/**
 * Created by wenshao on 2019/6/15.
 */
public class AppUtil {
    private static final Logger appLog = new LogWrapper("AppLogger");

    public static void info(String message, Object... objects) {
        appLog.info(LogLoaderFactory.newAppLog(LogLevel.INFO, CurrentLineUtil.getStackTrace(), message).toString(), objects);
    }

    public static void info(String message, Throwable throwable, Object... objects) {
        appLog.info(LogLoaderFactory.newAppLog(LogLevel.INFO, CurrentLineUtil.getStackTrace(), message, throwable).toString(), objects);
    }

    public static void debug(String message, Object... objects) {
        appLog.debug(LogLoaderFactory.newAppLog(LogLevel.DEBUG, CurrentLineUtil.getStackTrace(), message).toString(), objects);
    }

    public static void debug(String message, Throwable throwable, Object... objects) {
        appLog.debug(LogLoaderFactory.newAppLog(LogLevel.DEBUG, CurrentLineUtil.getStackTrace(), message, throwable).toString(), objects);
    }

    public static void error(String message, Object... objects) {
        appLog.debug(LogLoaderFactory.newAppLog(LogLevel.ERROR, CurrentLineUtil.getStackTrace(), message).toString(), objects);
    }

    public static void error(String message, Throwable throwable, Object... objects) {
        appLog.error(LogLoaderFactory.newAppLog(LogLevel.ERROR, CurrentLineUtil.getStackTrace(), message, throwable).toString(), objects);
    }
    public static void warn(String message, Object... objects) {
        appLog.warn(LogLoaderFactory.newAppLog(LogLevel.WARN, CurrentLineUtil.getStackTrace(), message).toString(), objects);
    }

    public static void warn(String message, Throwable throwable, Object... objects) {
        appLog.warn(LogLoaderFactory.newAppLog(LogLevel.WARN, CurrentLineUtil.getStackTrace(), message, throwable).toString(), objects);
    }
}
