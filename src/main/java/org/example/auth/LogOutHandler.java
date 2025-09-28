package org.example.auth;

import org.example.JavaAssistant;
import org.example.connection.ConnectionDataBase;
import org.example.datastructure.InputVerification;
import org.example.gmail.Color;
import org.example.user.MainDataBaseInfo;

import java.sql.Connection;

public class LogOutHandler {
    ConnectionDataBase connectionDataBase = new ConnectionDataBase();
    Connection con;
    InputVerification inputVerification = new InputVerification();
    MainDataBaseInfo mainDataBaseInfo = new MainDataBaseInfo();
    AuthHandler authHandler = new AuthHandler();
    public void logOut(Connection con) {
        this.con = con;
        System.out.println();
        System.out.println("Press 1"+ Color.BLUE +" to Explore the JAVA"+Color.RESET);
        System.out.println("Press 2"+Color.BLUE+" to LogOut this User account "+Color.RESET);
        System.out.println("Press 3"+Color.BLUE+" to SignOut this User account"+Color.RESET);
        System.out.println();
        System.out.print(Color.CYAN+"Enter your choice : "+Color.RESET);
        int choice = inputVerification.getInputInt();
        switch (choice) {
            case 1 : {
                // this is for explore the java
                mainDataBaseInfo.getInfo(con, 2);
            }break;
            case 2 : {
                // this is for log out this user account
                //authHandler.databaseConnection(con);
                JavaAssistant javaAssistant = new JavaAssistant();
                javaAssistant.hostLogOut();
            }break;
            case 3 : {
                // this is for user signOut

                SignOutHandler signOutHandler = new SignOutHandler();
                signOutHandler.signIn(con);
            }break;
            default : {
                System.out.println(Color.RED+"Invalid choice"+Color.RESET);
                System.out.println(Color.CYAN+"Please enter valid choice"+Color.RESET);
            }
        }
    }
}
