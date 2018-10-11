package ex02_blob.factories;

import ex02_blob.interfaces.Attack;
import ex02_blob.interfaces.Behavior;
import ex02_blob.models.Blob;
import ex02_blob.observers.Subject;

public class BlobFactory {

    public BlobFactory() {
    }

    public static Blob create(String name, int health, int damage, Behavior behavior, Attack attack, Subject subject) {
        return new Blob(name, health, damage, behavior, attack, subject);
    }
}
