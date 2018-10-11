package ex02_collection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ListyIterator<String> myList = null;

        ListyIterator myListyIter = new ListyIterator();
        Iterator iter = myListyIter.iterator();

        //ListyIterator.ListyIteratorIterator lIter = myListyIter.iterator();


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
                        if (myList == null) {
                            System.out.println("Invalid Operation!");
                        } else {
                            myList.print();
                        }
                    break;
                case "PrintAll":
                    if (myList == null) {
                        System.out.println("Invalid Operation!");
                    } else {
                        myList.printAll();
                    }
                    break;
            }

            line = reader.readLine();
        }
    }
}
