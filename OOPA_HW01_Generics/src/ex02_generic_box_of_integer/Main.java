package ex02_generic_box_of_integer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Box box = new Box();
        Box box1 = new Box();


        int numOfInputs = Integer.parseInt(reader.readLine());

        for (int i = 0; i < numOfInputs; i++) {
            box.setValue(Integer.parseInt(reader.readLine()));
            //box1.setValue(i);
            System.out.println(String.format("%s", box.toString()));
            //System.out.println(box1.toString());
        }
    }
}
