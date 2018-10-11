package lab01_jar_of_T;

import java.util.ArrayDeque;

public class Jar<T> {
    private ArrayDeque store = new ArrayDeque();

    public void add(T element) {
        this.store.push(element);
    }

    public T remove() {
        return (T)this.store.pop();
    }
}
