package ex01_listyiterator;

import java.util.*;
import java.util.stream.Collectors;

public class ListyIterator<T> {
    private T[] elements; //= new ArrayList<>();
    private int index;

//    public ListyIterator(List<T> elements) {
//        this.elements = elements;
//    }

    public ListyIterator(T... args) {
        this.elements = args;
    }

    public boolean move() {
        //this.index = (this.hasNext()) ? this.index++ : this.index;
        if (this.hasNext()) {
            this.index++;
            return true;
        } else {
            return false;
        }
    }

    public boolean hasNext() {
        return this.index < this.elements.length - 1;
    }

    public void print() {
        //if(this.elements.length == 0) {
        if (this.elements == null) {
            throw new IllegalStateException("Invalid Operation!");
        } else {
            System.out.println(this.elements[this.index]);
        }
    }
}
