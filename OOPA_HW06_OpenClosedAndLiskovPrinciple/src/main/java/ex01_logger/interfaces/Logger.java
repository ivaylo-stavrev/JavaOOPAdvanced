package ex01_logger.interfaces;

import ex01_logger.io.io_interfaces.Writer;

public interface Logger {
    void logInfo(String dateTime, String message);

    void logWarning(String dateTime, String message);

    void logError(String dateTime, String message);

    void logCritical(String dateTime, String message);

    void logFatal(String dateTime, String message);

    void getInfo(Writer writer);
}
