package ex02_blob.observers;

public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
