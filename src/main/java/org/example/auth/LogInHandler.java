package org.example.auth;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import org.example.datastructure.InputVerification;
import org.example.gmail.Color;
import org.example.user.MainDataBaseInfo;
import org.example.user.ShowData;
public class LogInHandler {

    InputVerification inputVerification = new InputVerification();
    AuthHandler authHandler = new AuthHandler();
    ShowData showData = new ShowData();
    Connection con;
    Scanner sc = new Scanner(System.in);
    HashMap<String, String> tampStore = new HashMap<>();
    MainDataBaseInfo mainDataBaseInfo = new MainDataBaseInfo();
    public static String signInUserName = "";

    public void logIn(Connection con) {
        this.con = con;
        String query = "select * from auth";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String username = rs.getString(2);
                String password = rs.getString(3);

                tampStore.put(username, password);
            }
        }catch (Exception e) {
            System.out.println(e);
        }

        int count = 0;
        System.out.print(Color.YELLOW+"Enter your username : "+Color.RESET);
        String username = sc.nextLine();

        //System.out.println(tampStore);
        boolean flag = tampStore.containsKey(username);
        if (flag) {
            String password = tampStore.get(username);
            String tampPassword = "";
            String tampPasswors1 = "";
            for (int i = 1; i < password.length() - 1; i++) {
                tampPassword = tampPassword + password.charAt(i);
            }
            for (int i = tampPassword.length() - 1; i >= 0; i--) {
                tampPasswors1 = tampPasswors1 + tampPassword.charAt(i);
            }
            password = tampPasswors1;
            int ab = 0;
            while (ab == 0) {
                System.out.print(Color.CYAN+"Enter your password : "+Color.RESET);
                String pass = sc.nextLine();
                if (pass.equals(password)) {
                    System.out.println();
                    System.out.println(Color.GREEN+Color.BOLD+"LogIn successfully"+Color.RESET);
                    System.out.println();
                    signInUserName = username;
                    mainDataBaseInfo.getInfo(con, 2);
                    ab = 1;
                }
                else {
                    System.out.println(Color.RED+Color.BOLD+"incorrect password, please try again"+Color.RESET);
                    count++;
                }
                if (count >= 3) {
                    System.out.println(Color.RED+Color.BOLD+"you try many times, reenter username and password"+Color.RESET);
                    logIn(con);
                }
            }
        }else {
            System.out.println();
            System.out.println(Color.RED+Color.BOLD+"This username is not Exist, try again"+Color.RESET);
            System.out.println("Press 1 :"+Color.BLUE+" If you want to create new account"+Color.RESET);
            System.out.println("Press 2 :"+Color.BLUE+" If you want re-try for LogIn"+Color.RESET);
            System.out.println();
            System.out.print(Color.CYAN+"Enter your choice : "+Color.RESET);
            int choice = inputVerification.getInputInt();
            if (choice == 2) {
                logIn(con);
            }else if (choice == 1) {
                //AuthHandler authHandler1 = new AuthHandler();
                SignInHandler signInHandler = new SignInHandler();
                signInHandler.signIn(con);
            }
        }
    }
}
