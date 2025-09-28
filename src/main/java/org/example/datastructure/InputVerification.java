package org.example.datastructure;

import org.example.gmail.Color;

import java.util.Scanner;

public class InputVerification {
    Scanner scanner = new Scanner(System.in);
    public int getInputInt() {
        while (true) {
            try {
                return scanner.nextInt();
            }catch (Exception e) {
                System.out.print(Color.YELLOW+"Invalid Input, please enter valid input : "+Color.RESET);
                scanner.nextLine();
            }
        }
    }
}