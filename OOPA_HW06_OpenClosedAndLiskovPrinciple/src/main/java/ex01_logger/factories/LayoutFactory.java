package ex01_logger.factories;

import ex01_logger.interfaces.Layout;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class LayoutFactory {
    public static final String LAYOUTS_CLASS_PATH = "ex01_logger.models.layouts.";

    public LayoutFactory() {
    }

    public static Layout create(String layoutType) {
        Class<?> layoutClass = null;
        try {
            layoutClass = Class.forName(LAYOUTS_CLASS_PATH + layoutType);
            Constructor<?> layoutCtor = layoutClass.getDeclaredConstructor();
            return (Layout) layoutCtor.newInstance();
        } catch (ClassNotFoundException |
                NoSuchMethodException |
                InvocationTargetException |
                IllegalAccessException |
                InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
