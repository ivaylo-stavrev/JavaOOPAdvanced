package ex10_tuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] lineSplitted = reader.readLine().split("\\s+");
        String firstName = lineSplitted[0];
        String secondName = lineSplitted[1];
        String address = lineSplitted[2];
        Tuple<String, String> tuple1 = new Tuple<>(String.format("%s %s", firstName, secondName), address);
        System.out.println(tuple1.toString());

        lineSplitted = reader.readLine().split("\\s+");
        String name = lineSplitted[0];
        int litersOfBeer = Integer.parseInt(lineSplitted[1]);
        Tuple<String, Integer> tuple2 = new Tuple<>(name, litersOfBeer);
        System.out.println(tuple2.toString());

        lineSplitted = reader.readLine().split("\\s+");
        int intNumber = Integer.parseInt(lineSplitted[0]);
        double doubleNumber = Double.parseDouble(lineSplitted[1]);
        Tuple<Integer, Double> tuple3 = new Tuple<>(intNumber, doubleNumber);
        System.out.println(tuple3.toString());
    }
}
