package demo;

public class Main {
    public static void main(String[] args) {
        Vegetable vegetable = new Vegetable("green");

        Animal animal = new Animal();

        //animal.dog.bark();

        // This could be used if the inner class Food is declared PUBLIC
        //Animal.Food animalFood = animal.getFood();
        //
        //animalFood.setCalories(100);
        //System.out.println(animalFood.getCalories());
        //
        //animal.food.setCalories(200);
        //System.out.println(animal.food.getCalories());


        // If the class is declared PRIVATE then public methods should be used to extract the information needed.
        animal.getTimeToEat();
    }
}
