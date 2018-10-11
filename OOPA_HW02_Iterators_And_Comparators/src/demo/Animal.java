package demo;

public class Animal implements Colorable, Eatable {
    private int age;
    private String color;
    //private int animTimeToEat;

    //Dog dog = new Dog("grey");
    Food food = getFood();


    @Override
    public int getTimeToEat() {
        return this.food.getTimeToEat();
    }

    @Override
    public void setTimeToEat(int timeToEat) {
        this.food.setTimeToEat(timeToEat);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    public Food getFood(){
        return new Food(50);
    }

    private class Food implements Eatable {
        private int calories;
        private int timeToEat;

        public Food(int calories) {
            this.calories = calories;
        }

        public int getCalories() {
            return calories;
        }

        public void setCalories(int calories) {
            this.calories = calories;
        }

        @Override
        public int getTimeToEat() {
            return this.timeToEat;
        }

        @Override
        public void setTimeToEat(int timeToEat) {
            this.timeToEat = timeToEat;
        }
    }
}
