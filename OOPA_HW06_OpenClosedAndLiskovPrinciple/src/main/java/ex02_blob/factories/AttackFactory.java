package ex02_blob.factories;

import ex02_blob.interfaces.Attack;
import ex02_blob.interfaces.Behavior;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AttackFactory {
    public static final String ATTACKS_CLASS_PATH = "ex02_blob.models.attacks.";

    public AttackFactory() {
    }

    public static Attack create(String attackType) {
        try {
            Class<?> attackClass = Class.forName(ATTACKS_CLASS_PATH + attackType);
            Constructor<?> attackCtor = attackClass.getDeclaredConstructor();
            return (Attack) attackCtor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
