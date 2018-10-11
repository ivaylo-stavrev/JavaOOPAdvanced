package demo;

public class Dog extends Animal implements Colorable {
    private String color;

    public Dog(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    public void bark(){
        System.out.println("BAU BAU");
    }

    public void print(){

    }
}
