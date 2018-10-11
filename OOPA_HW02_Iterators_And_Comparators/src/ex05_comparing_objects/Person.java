package ex05_comparing_objects;

public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private String town;

    public Person(String name, int age, String town) {
        this.name = name;
        this.age = age;
        this.town = town;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public int compareTo(Person person) {
        if (this.getName().compareTo(person.getName()) > 0) {
            return 1;
        } else if (this.getName().compareTo(person.getName()) == 0) {
            if (this.getAge() - person.getAge() > 0) {
                return 1;
            } else if (this.getAge() - person.getAge() == 0) {
                return this.getTown().compareTo(person.getTown());
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}
