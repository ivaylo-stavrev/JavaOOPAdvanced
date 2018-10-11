package ex02_collection;

import java.util.Iterator;

public class ListyIterator<T> implements Iterable {
    private T[] elements; //= new ArrayList<>();
    private int index;

    Iterator listyIter = iterator();


    public ListyIterator(T... args) {
        this.elements = args;
    }

    public boolean move() {
        if (this.hasNext()) {
            this.index++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterator iterator() {
        return new ListyIteratorIterator();
    }

    public boolean hasNext() {
        return this.index < this.elements.length - 1;
    }

    public void print() {
        if (this.elements == null) {
            throw new IllegalStateException("Invalid Operation!");
        } else {
            System.out.println(this.elements[this.index]);
        }
    }

    public void printAll() {
        StringBuilder sb = new StringBuilder();
        Iterator iterator = this.iterator();
        //System.out.println(String.join(" ", elements));
        sb.append(this.elements[0]).append(" ");
        while (iterator.hasNext()) {
            sb.append(iterator.next()).append(" ");
        }
        if (this.elements == null) {
            throw new IllegalStateException("Invalid Operation!");
        } else {
            System.out.println(sb.toString());
        }
    }

    private final class ListyIteratorIterator implements Iterator<T>{
        private int counter = 0;

        public int getCounter() {
            return counter;
        }

        public void setCounter(int counter) {
            this.counter = counter;
        }

        @Override
        public boolean hasNext() {
            return this.counter < elements.length - 1;
        }

        @Override
        public T next() {
            if (this.hasNext()) {
                this.counter++;
            }
            return elements[counter];
        }
    }
}
