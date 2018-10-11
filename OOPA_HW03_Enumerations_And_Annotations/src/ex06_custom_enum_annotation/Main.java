package ex06_custom_enum_annotation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String inputType = reader.readLine();

        if ("Suit".equals(inputType)) {
            Class<CardSuit> suitTypeClass = CardSuit.class;
            printCustomAnnotationStats(suitTypeClass);
        } else if ("Rank".equals(inputType)){
            Class<CardRank> rankTypeClass = CardRank.class;
            printCustomAnnotationStats(rankTypeClass);
        }

    }

    private static void printCustomAnnotationStats(Class<?> customTypeClass) {
        if (customTypeClass.isAnnotationPresent(CustomAnnotation.class)) {
            CustomAnnotation annotation = customTypeClass.getAnnotation(CustomAnnotation.class);
            System.out.println(String.format("Type = %s, Description = %s",
                    annotation.type(), annotation.descrioption()));
        }
    }
}