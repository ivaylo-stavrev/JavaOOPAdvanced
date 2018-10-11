package ex03_generic_swap_method_strings;

import java.util.ArrayList;
import java.util.List;

public class Box<T> {
    private T value;
//    private List<T> listOfAnyType = new ArrayList<>();

    public Box() {
    }

//    public List<T> getListOfAnyType() {
//        return this.listOfAnyType;
//    }
//
//    public void setListOfAnyType(List<T> listOfAnyType) {
//        this.listOfAnyType = listOfAnyType;
//    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

//    public void addElement(T elem) {
//        this.listOfAnyType.add(elem);
//    }

//    public void swapIndeces(int index1, int index2) {
//        T buffer = this.listOfAnyType.get(index1);
//        this.listOfAnyType.set(index1, this.listOfAnyType.get(index2));
//        this.listOfAnyType.set(index2, buffer);
//    }

    @Override
    public String toString() {
        return this.value.getClass().getTypeName() + ": " + this.getValue();
    }

//    public void boxPrint() {
//        for (int i = 0; i < this.listOfAnyType.size(); i++) {
//            System.out.println(String.format("%s: %s", this.listOfAnyType.get(i).getClass().getTypeName(), this.listOfAnyType.get(i)));
//        }
//    }
}
