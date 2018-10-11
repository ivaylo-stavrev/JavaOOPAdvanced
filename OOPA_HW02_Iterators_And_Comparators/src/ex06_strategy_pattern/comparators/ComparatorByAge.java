package ex06_strategy_pattern.comparators;

import ex06_strategy_pattern.Person;

import java.util.Comparator;

public class ComparatorByAge implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {
        return person1.getAge() - person2.getAge();
    }
}
