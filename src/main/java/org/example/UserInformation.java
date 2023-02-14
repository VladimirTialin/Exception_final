package org.example;

import java.util.Scanner;

public class UserInformation {
    public static String stringFromUser() {
        Scanner scn = new Scanner(System.in);
        return scn.nextLine();
    }
}