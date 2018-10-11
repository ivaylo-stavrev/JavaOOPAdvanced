package ex01_logger.factories;

import ex01_logger.interfaces.Appender;
import ex01_logger.interfaces.Layout;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AppenderFactory {
    public static final String APPENDERS_CLASS_PATH = "ex01_logger.models.appenders.";

    public AppenderFactory() {
    }

    public static Appender create(String appenderType, Layout layout) {
        try {
            Class<?> appenderClass = Class.forName(APPENDERS_CLASS_PATH + appenderType);
            Constructor<?> appenderCtor = appenderClass.getDeclaredConstructor(Layout.class);
            return (Appender) appenderCtor.newInstance(layout);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
