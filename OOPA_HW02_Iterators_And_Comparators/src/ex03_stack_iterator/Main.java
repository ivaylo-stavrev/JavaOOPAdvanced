package ex03_stack_iterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        MyStack<Integer> myStack = new MyStack<>();

        //String input = reader.readLine();
        String line = reader.readLine();
        while (true) {
            if ("END".equals(line)) {
                break;
            }
            String[] lineSplitted = line.split("\\s+", 2);

            String cmd = lineSplitted[0];
            String[] items = lineSplitted.length == 1 ? null : lineSplitted[1].split(",\\s+");
            //Integer[] intItems = Arrays.stream(items).map(Integer::getInteger).toArray();
            int[] intItems = null;
            if (items != null) {
                intItems = Arrays.stream(items).mapToInt(Integer::parseInt).toArray();
            }
            //intItems = Arrays.stream(items).mapToInt(Integer::valueOf).collect(Collectors.toList());

            switch (cmd) {
                case "Push":
                    for (int item : intItems) {
                        myStack.push(item);
                    }
                    break;
                case "Pop":
                    try {
                        myStack.pop();
                    } catch (NoSuchElementException nsee) {
                        System.out.println(nsee.getMessage());
                    }
                    break;
            }
            line = reader.readLine();
        }
        if (myStack == null) {
            System.out.println("No elements");
        } else {
            printAll(myStack);
            printAll(myStack);
        }
    }

    private static void printAll(MyStack<Integer> myStack) {
        for (Integer item : myStack) {
            System.out.println(item);
        }
    }
}
