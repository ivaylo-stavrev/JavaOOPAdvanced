package ex11_threeuple;

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
        String town = lineSplitted[3];
        Threeuple<String, String, String> threeuple1 = new Threeuple<>(String.format("%s %s", firstName, secondName), address, town);
        System.out.println(threeuple1.toString());

        lineSplitted = reader.readLine().split("\\s+");
        String name = lineSplitted[0];
        boolean isDrunk = "drunk".equals(lineSplitted[2]);
        int litersOfBeer = Integer.parseInt(lineSplitted[1]);
        Threeuple<String, Integer, Boolean> threeuple2 = new Threeuple<>(name, litersOfBeer, isDrunk);
        System.out.println(threeuple2.toString());

        lineSplitted = reader.readLine().split("\\s+");
        name = lineSplitted[0];
        double doubleNumber = Double.parseDouble(lineSplitted[1]);
        String bankName = lineSplitted[2];
        Threeuple<String, Double, String> threeuple3 = new Threeuple<>(name, doubleNumber, bankName);
        System.out.println(threeuple3.toString());
    }
}
