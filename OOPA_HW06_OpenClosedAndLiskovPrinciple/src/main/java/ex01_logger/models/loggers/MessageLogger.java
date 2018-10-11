package ex01_logger.models.loggers;

import ex01_logger.interfaces.Appender;
import ex01_logger.interfaces.Logger;
import ex01_logger.io.io_interfaces.Writer;
import ex01_logger.models.enums.ReportLevel;

import java.util.Arrays;

public class MessageLogger implements Logger {
    private Appender[] appenders;
    //private ReportLevel reportLevel;

    public MessageLogger(Appender... appenders) {
        this.setAppenders(appenders);
    }

    public void setAppenders(Appender[] appenders) {
        if (appenders == null) {
            throw new IllegalStateException("Appenders cannot be null.");
        }
        this.appenders = appenders;
    }

    @Override
    public void logInfo(String dateTime, String message) {
        this.appendMessage(dateTime, message, ReportLevel.INFO);
    }

    @Override
    public void logWarning(String dateTime, String message) {
        this.appendMessage(dateTime, message, ReportLevel.WARNING);
    }

    @Override
    public void logError(String dateTime, String message) {
        this.appendMessage(dateTime, message, ReportLevel.ERROR);
    }

    @Override
    public void logCritical(String dateTime, String message) {
        this.appendMessage(dateTime, message, ReportLevel.CRITICAL);
    }

    @Override
    public void logFatal(String dateTime, String message) {
        this.appendMessage(dateTime, message, ReportLevel.FATAL);
    }

    @Override
    public void getInfo(Writer writer) {
        writer.writeLine("Logger info");
        Arrays.stream(this.appenders).forEach(appender -> writer.writeLine(appender.toString()));
    }

    private void appendMessage(String dateTime, String message, ReportLevel reportLevel) {
        if (this.appenders.length > 0) {
            Arrays.stream(this.appenders).forEach(appender -> appender.append(reportLevel, dateTime, message));
        }
    }
}
