package ex02_generic_box_of_integer;

public class Box<T> {
    private T value;

    public Box() {
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value.getClass().getTypeName() + ": " + this.getValue();
    }
}
