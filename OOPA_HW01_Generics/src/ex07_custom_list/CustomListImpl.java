package ex07_custom_list;

import java.util.ArrayList;
import java.util.List;

public class CustomListImpl<T extends Comparable<T>> implements CustomList<T> {
    private List<T> elements;

    public CustomListImpl() {
        this.elements = new ArrayList<>();
    }

    @Override
    public void add(T element) {
        this.elements.add(element);
    }

    @Override
    public T remove(int index) {
        return this.elements.remove(index);
    }

    @Override
    public boolean contains(T element) {
        if(this.elements.contains(element)) {
            return true;
        }
        return false;
    }

    @Override
    public void swap(int firstIndex, int secondIndex) {
        T buffer = this.elements.get(firstIndex);
        this.elements.set(firstIndex, this.elements.get(secondIndex));
        this.elements.set(secondIndex, buffer);
    }

    @Override
    public int countGreaterThan(T element) {
        int cnt = 0;
        for (T el : this.elements) {
            if (el.compareTo(element) > 0) {
                cnt++;
            }
        }
        return cnt;
    }

    @Override
    public T getMax() {
        T tMax = null;
        for (int i = 0; i < this.elements.size(); i++) {
            if(tMax == null) {
                tMax = this.elements.get(i);
            } else if(this.elements.get(i).compareTo(tMax) > 0) {
                tMax = this.elements.get(i);
            }
        }
        return tMax;
    }

    @Override
    public T getMin() {
        T tMin = null;
        for (int i = 0; i < this.elements.size(); i++) {
            if(tMin == null) {
                tMin = this.elements.get(i);
            } else if(this.elements.get(i).compareTo(tMin) < 0) {
                tMin = this.elements.get(i);
            }
        }
        return tMin;
    }

    public void print() {
        for (T element : elements) {
            System.out.println(element);
        }
    }
}
