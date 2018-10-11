package ex06_strategy_pattern.comparators;

import ex06_strategy_pattern.Person;

import java.util.Comparator;

public class ComparatorByNameLength implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {
        if (person1.getName().length() - person2.getName().length() == 0) {
            return person1.getName().compareToIgnoreCase(person2.getName());
        }
        return person1.getName().length() - person2.getName().length();
    }
}
