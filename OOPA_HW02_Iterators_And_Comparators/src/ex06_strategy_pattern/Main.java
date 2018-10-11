package ex06_strategy_pattern;

import ex06_strategy_pattern.comparators.ComparatorByAge;
import ex06_strategy_pattern.comparators.ComparatorByNameLength;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Set<Person> treeSetByName = new TreeSet<>(new ComparatorByNameLength());
        Set<Person> treeSetByAge = new TreeSet<>(new ComparatorByAge());
        int inputCount = Integer.parseInt(reader.readLine());
        String[] line;
        Person currentPerson;

        for (int i = 0; i < inputCount; i++) {
            line = reader.readLine().split("\\s+");
            currentPerson = new Person(line[0], Integer.parseInt(line[1]));
            treeSetByName.add(currentPerson);
            treeSetByAge.add(currentPerson);
        }

        treeSetByName.forEach(p -> System.out.println(p));
        treeSetByAge.forEach(p -> System.out.println(p));
    }
}
