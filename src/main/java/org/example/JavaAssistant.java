package org.example;

import java.util.Scanner;
import org.example.auth.*;
import org.example.datastructure.InputVerification;
import org.example.connection.ConnectionDataBase;
import org.example.gmail.Color;
import org.example.user.ShowData;


public class JavaAssistant {

    public static void main(String[] args) {
        System.out.println();
        System.out.println();
        //System.out.print("     ");
        String design = "WELCOME TO THE JAVA ASSISTANT";
        for (int i = 0; i < design.length(); i++) {
            try {
                Thread.sleep(250);
                System.out.print(Color.PURPLE+ Color.BOLD+ design.charAt(i) + Color.RESET);
            }catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println();


        // create object for TampJavaAssistant //

        TampJavaAssistant tampJavaAssistant = new TampJavaAssistant();
        tampJavaAssistant.handler();
    }
    public void hostLogOut() {
        TampJavaAssistant tampJavaAssistant = new TampJavaAssistant();
        tampJavaAssistant.handler();
    }
}
class TampJavaAssistant {

    InputVerification inputVerification = new InputVerification();
    ConnectionDataBase connectionDataBase = new ConnectionDataBase();
    boolean flag = true;
    void handler() {
        while (flag) {
            System.out.println();
            System.out.println("Press 1 "+Color.BLUE+"If you are Host"+Color.RESET);
            System.out.println("Press 2 "+ Color.BLUE+ "If you are  User"+Color.RESET);
            System.out.println("Press 3 "+Color.BLUE +  "If you want to close the program"+Color.RESET);

            System.out.println();
            try {
                Thread.sleep(750);
            }catch (Exception e) {
                System.out.println(e);
            }
            System.out.print(Color.GREEN+"Enter your choice : "+Color.RESET);
            int choice = inputVerification.getInputInt();

            switch (choice) {
                case 1 : {

                    // call login method for host login
                    connectionDataBase.connection(choice);
                    flag = false;
                }
                break;
                case 2 : {
                    // call user authentication
                    connectionDataBase.connection(choice);
                    flag = false;
                    int breakLoop = 0;
                    // userDefine is call by this method after database connected
                }
                break;
                case 3 : {
                    System.out.println();
                    System.out.println("program will close");
                    try {
                        Thread.sleep(2000);
                    }catch (Exception e) {
                        System.out.println(e);
                    }
                    flag = false;
                }
                break;
                default : {
                    System.out.println();
                    System.out.println(Color.RED+"Invalid choice, please enter valid choice"+Color.RESET);
                    System.out.println();
                    ShowData showData = new ShowData();
                }
                break;
            }
        }
    }
}
