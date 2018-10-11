package ex01_listyiterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ListyIterator<String> myList = null;

        //String input = reader.readLine();
        String line = reader.readLine();
        while (true) {
            if ("END".equals(line)) {
                break;
            }
            String[] lineSplitted = line.split("\\s+", 2);

            String cmd = lineSplitted[0];
            String[] items = lineSplitted.length == 1 ? null : lineSplitted[1].split("\\s+");
            //String items = lineSplitted[1];

            switch (cmd) {
                case "Create":
                    myList = items == null ? null : new ListyIterator<String>(Arrays.stream(items).toArray(String[]::new));
                    break;
                case "Move":
                    System.out.println(myList.move());
                    break;
                case "HasNext":
                    System.out.println(myList.hasNext());
                    break;
                case "Print":
                    //try {
                        if (myList == null) {
                            System.out.println("Invalid Operation!");
                        } else {
                            myList.print();
                        }
                    //} catch (IllegalStateException ise) {
                    //    System.out.println(ise.getMessage());
                    //}
                    break;
            }

            line = reader.readLine();
        }
    }
}
