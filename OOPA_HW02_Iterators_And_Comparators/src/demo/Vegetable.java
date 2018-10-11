package demo;

public class Vegetable implements Colorable {
    private String color;
    private Dog dog;

    public Vegetable(String color) {
        this.color = color;
        this.dog = new Dog("black");
    }



    public Dog getDog() {
        return dog;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }
}
