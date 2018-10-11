package ex02_blob.factories;

import ex02_blob.interfaces.Behavior;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BehaviorFactory {
    public static final String BEHAVIORS_CLASS_PATH = "ex02_blob.models.behaviors.";

    public BehaviorFactory() {
    }

    public static Behavior create(String behaviorType) {
        try {
            Class<?> behaviorClass = Class.forName(BEHAVIORS_CLASS_PATH + behaviorType);
            Constructor<?> behaviorCtor = behaviorClass.getDeclaredConstructor();
            return (Behavior) behaviorCtor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
