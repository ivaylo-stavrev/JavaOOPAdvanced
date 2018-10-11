package ex05_comparing_objects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Person> people = new ArrayList<>();

        String line = reader.readLine();
        while (true) {
            if ("END".equals(line)) {
                break;
            }
            String[] lineSplitted = line.split("\\s+");

            String name = lineSplitted[0];
            String town = lineSplitted[2];
            int age = Integer.parseInt(lineSplitted[1]);

            people.add(new Person(name, age, town));

            line = reader.readLine();
        }
        int number = Integer.parseInt(reader.readLine());
        int equalsCount = 0;
        int notEqualsCount = 0;
        int allPeopleCount = people.size();

        if (number >= allPeopleCount || number < 0) {
            System.out.println("No matches");
        } else {
            equalsCount = checkForEquals(people, number);
            notEqualsCount = allPeopleCount - equalsCount;
            if (equalsCount == 0) {
                System.out.println("No matches");
            } else {
                System.out.println(String.format("%d %d %d", equalsCount, notEqualsCount, allPeopleCount));
            }
        }
    }

    private static int checkForEquals(List<Person> people, int number) {
        Person personReference = people.get(number);
        //people.remove(number);
        int counter = 0;
        for (Person p : people) {
            if (personReference.compareTo(p) == 0) {
                counter++;
            }
        }
        return counter;
    }
}
