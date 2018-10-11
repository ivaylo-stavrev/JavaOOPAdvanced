package ex01_logger.core;

import ex01_logger.factories.AppenderFactory;
import ex01_logger.factories.LayoutFactory;
import ex01_logger.interfaces.Appender;
import ex01_logger.interfaces.Logger;
import ex01_logger.io.io_interfaces.Reader;
import ex01_logger.io.io_interfaces.Writer;
import ex01_logger.models.enums.ReportLevel;
import ex01_logger.models.loggers.MessageLogger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Engine implements Runnable {
    private final String TERMINATE_PROGRAM = "END";
    private final String DEFAULT_LOGGER_METHOD_PREFIX = "log";

    private Writer writer;
    private Reader reader;
    private Logger logger;

    public Engine(Writer writer, Reader reader) {
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public void run() {
        int numOfAppenders = Integer.parseInt(reader.readLine());
        String[] appenderArgs;
        Appender[] appenders = new Appender[numOfAppenders];
        for (int i = 0; i < numOfAppenders; i++) {
            appenderArgs = reader.readLine().split("\\s+");
            appenders[i] = AppenderFactory.create(appenderArgs[0], LayoutFactory.create(appenderArgs[1]));

            if (appenderArgs.length > 2) {
                appenders[i].setReportLevel(ReportLevel.valueOf(appenderArgs[2]));
                // appenders[i].setReportLevel(Enum.valueOf(ReportLevel.class, appenderArgs[2]));
            }
        }
        this.logger = new MessageLogger(appenders);

        String line;
        while (true) {
            line = this.reader.readLine();
            if (TERMINATE_PROGRAM.equalsIgnoreCase(line)) {
                break;
            }
            String[] messageArgs = line.split("\\|");

            String level = messageArgs[0];
            String dateTime = messageArgs[1];
            String message = messageArgs[2];

            String loggerMethodName = DEFAULT_LOGGER_METHOD_PREFIX + level.charAt(0) + level.substring(1).toLowerCase();
            //System.out.println(loggerMethodName);

            try {
                Method loggerMethod = this.logger.getClass().getDeclaredMethod(loggerMethodName, String.class, String.class);
                loggerMethod.invoke(this.logger, dateTime, message);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        this.logger.getInfo(this.writer);
    }
}
