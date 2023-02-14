package org.example;

import java.util.Scanner;

public class OnlyCorrect {
    public static int correctInt(int minValue, int maxValue) {
        int resultValue;
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                String value = scanner.nextLine();
                resultValue = Integer.parseInt(value);
                if (resultValue < minValue || resultValue > maxValue) {
                    throw new RuntimeException();
                } else {
                    return resultValue;
                }
            } catch (Exception e) {
                System.out.printf("Неверное значение! Введите цифру от %d до %d: ", minValue, maxValue);
            }
        }
    }
}