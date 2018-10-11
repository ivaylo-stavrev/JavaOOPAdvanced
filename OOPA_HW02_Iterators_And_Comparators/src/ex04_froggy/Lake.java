package ex04_froggy;

import java.util.Iterator;
import java.util.List;

public class Lake<T> implements Iterable<T> {
    private List<T> numbers;

    public Lake(List<T> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Iterator<T> iterator() {
        return new Frog();
    }


    private final class Frog implements Iterator {
        private int frogPointer;
        private boolean isEven;

        public Frog() {
            this.frogPointer = 0;
            this.isEven = true;
        }

        @Override
        public boolean hasNext() {
            boolean hasNext = frogPointer < numbers.size();
            if (!hasNext && !this.isEven) {
                //frogPointer += 2;
                return false;
            } else if (!hasNext && this.isEven) {
                if (numbers.size() == 1) {
                    return false;
                }
                this.isEven = false;
                frogPointer = 1;
                return true;
            }
            return true;
        }

        @Override
        public T next() {
            T stepNumber = numbers.get(frogPointer);
            this.frogPointer += 2;
            return stepNumber;
        }
    }
}
