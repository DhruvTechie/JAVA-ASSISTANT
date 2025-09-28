package org.example.auth;
import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

import org.example.JavaAssistant;
import org.example.datastructure.InputVerification;
import org.example.gmail.Color;
import org.example.host.HostAuthHandler;

public class AuthHandler {

    InputVerification inputVerification = new InputVerification();
    HostAuthHandler hostAuthHandler = new HostAuthHandler();

    public Connection connection;
    public HashMap<String, String> TampStore = new HashMap<>();

    Scanner sc = new Scanner(System.in);

    public void databaseConnection(Connection con) {

        this.connection = con;
        userDefine(connection);
    }

    public void userDefine(Connection con) {

        while (true) {

            System.out.println();
            System.out.println("Press 1 :"+ Color.BLUE+" If you have account and Log In "+Color.RESET);
            System.out.println("Press 2 :"+Color.BLUE+" If you want to create a new account"+Color.RESET);
            System.out.println("Press 3 :"+Color.BLUE+" FOR EXIT"+Color.RESET);
            System.out.println();
            System.out.print("Enter choice : ");
            int choice = inputVerification.getInputInt();

            if (choice == 1) {

                // ==== code for Log In ==== //

                LogInHandler lih = new LogInHandler();
                lih.logIn(connection);
                return;

            }else if (choice == 2) {

                // ==== code for sign in ==== //

                SignInHandler sih = new SignInHandler();
                sih.signIn(connection);
                return;

            }else if (choice == 3) {
                JavaAssistant javaAssistant = new JavaAssistant();
                javaAssistant.hostLogOut();
                return;
            }else {
                System.out.println(Color.RED+"Invalid choice, plese enter valid choice"+Color.RESET);
            }
        }
    }
    public void hostAuth() {
        hostAuthHandler.hostLogIn(connection);
    }
}
