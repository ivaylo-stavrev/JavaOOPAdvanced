package ex08_custom_list_sorter;

public interface CustomList <T extends Comparable<T>> {

    void add(T element);

    T remove(int index);

    boolean contains(T element);

    void swap(int firstIndex, int secondIndex);

    int countGreaterThan(T element);

    T getMax();

    T getMin();

    void print();

    int size();

    int compareElements(int firstIndex, int secondIndex);
}
