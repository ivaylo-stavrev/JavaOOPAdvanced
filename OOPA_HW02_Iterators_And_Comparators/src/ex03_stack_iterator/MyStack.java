package ex03_stack_iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyStack<T> implements Iterable<T> {
    private static final int INITIAL_CAPACITY = 16;
    private static final int EMPTY_STACK = 0;

    private T[] stack;
    private int stackPointer;


    @SuppressWarnings("unchecked")
    public MyStack() {
        this.stack = (T[]) new Object[INITIAL_CAPACITY];
    }

    public void push(T element) {
        if (this.stackPointer == this.stack.length) {
            stackLengthExtend();
        }
        this.stack[stackPointer++] = element;

    }

    private Iterator<T> iterator = this.iterator();

    public T pop() {
        if(this.stackPointer == EMPTY_STACK) {
            throw new NoSuchElementException("No element");
        }
        T item = this.stack[--stackPointer];
        this.stack[stackPointer] = null;
        return item;
    }

    private void stackLengthExtend() {
        this.stack = Arrays.copyOf(this.stack, this.stack.length * 2);
    }

//    public void printAll() {
//        if (this.stack == null) {
//            throw new IllegalStateException("Invalid Operation!");
//        } else {
//            if (iterator.hasNext()) {
//                System.out.println(iterator.next());
//            }
//        }
//    }

    @Override
    public Iterator<T> iterator() {
        return new MyStackIterator();
    }

    private class MyStackIterator implements Iterator<T> {
        private int counter;

        public MyStackIterator() {
            setCounter(stackPointer - 1);
        }

        public int getCounter() {
            return this.counter;
        }

        public void setCounter(int counter) {
            this.counter = counter;
        }

        @Override
        public boolean hasNext() {
            return counter >= 0;
        }

        @Override
        public T next() {
            return stack[counter--];
        }
    }
}
