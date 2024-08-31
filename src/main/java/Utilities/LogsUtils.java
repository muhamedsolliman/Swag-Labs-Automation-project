package Utilities;

import org.apache.logging.log4j.LogManager;

public class LogsUtils {
    public static String LOGS_PATH ="test-outputs/Logs";

    public static void TRACE(String Message){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString()).trace(Message);
    }
    public static void debug(String Message){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString()).debug(Message);
    }
    public static void info(String Message){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString()).info(Message);
    }
    public static void warn(String Message){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString()).warn(Message);
    }
    public static void error(String Message){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString()).error(Message);
    }
    public static void fatal(String Message){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString()).fatal(Message);
    }
}
