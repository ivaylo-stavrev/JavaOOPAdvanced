package ex05_generic_count_method_string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int numOfInputs = Integer.parseInt(reader.readLine());
        List<Box<String>> boxes = new ArrayList<>();
        Box<String> box;

        for (int i = 0; i < numOfInputs; i++) {
            String input = reader.readLine();
            box = new Box<>();
            box.setValue(input);
            boxes.add(box);
        }

        String checkValue = reader.readLine();
        box = new Box<>();
        box.setValue(checkValue);
        System.out.println(compareElements(boxes, box));

//        String[] lineSplited = reader.readLine().split("\\s+");
//        int index1 = Integer.parseInt(lineSplited[0]);
//        int index2 = Integer.parseInt(lineSplited[1]);
//        swapIndeces(index1, index2, boxes);
//
//        for (Box<String> box : boxes) {
//            System.out.println(box.toString());
//        }
    }

//    public static <T> void swapIndeces(int index1, int index2, List<Box<T>> boxesList) {
//        Box<T> buffer = boxesList.get(index1);
//        boxesList.set(index1, boxesList.get(index2));
//        boxesList.set(index2, buffer);
//    }

    public static <T extends Comparable<T>> int compareElements(List<Box<T>> elements, Box<T> element) {
        int cnt = 0;
        for (Box<T> el : elements) {
            if (el.getValue().compareTo(element.getValue()) > 0) {
                cnt++;
            }
        }
        return cnt;
    }
}
