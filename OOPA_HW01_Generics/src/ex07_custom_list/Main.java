package ex07_custom_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CustomList myList = new CustomListImpl<>();

        String line = reader.readLine();
        while (true) {
            if ("END".equals(line)) {
                break;
            }
            String[] lineSplitted = line.split("\\s+");
            String arg1 = null;
            String arg2 = null;
            String cmd = lineSplitted[0];
            if(lineSplitted.length > 1) {
                arg1 = lineSplitted[1];
            }

            if(lineSplitted.length > 2) {
                arg2 = lineSplitted[2];
            }

            switch (cmd) {
                case "Add":
                    myList.add(arg1);
                    break;
                case "Remove":
                    myList.remove(Integer.parseInt(arg1));
                    break;
                case "Contains":
                    System.out.println(myList.contains(arg1));
                    break;
                case "Swap":
                    myList.swap(Integer.parseInt(arg1), Integer.parseInt(arg2));
                    break;
                case "Greater":
                    System.out.println(myList.countGreaterThan(arg1));
                    break;
                case "Max":
                    System.out.println(myList.getMax());
                    break;
                case "Min":
                    System.out.println(myList.getMin());
                    break;
                case "Print":
                    myList.print();
                    break;
            }

            line = reader.readLine();
        }
    }
}
