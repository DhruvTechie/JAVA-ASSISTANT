package org.example.host;

import org.example.gmail.Color;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class HostAuthHandler {

    boolean hostLogInFlag = false;
    Scanner sc = new Scanner(System.in);
    DataBaseManage dataBaseManage = new DataBaseManage();
    Connection con;
    // create method for host logIn

    public void hostLogIn(Connection con) {

        this.con = con;
        System.out.print(Color.CYAN+"Enter Host Username : "+Color.RESET);
        String usname = sc.nextLine();
        System.out.print(Color.CYAN+"Enter password : "+Color.RESET);
        String pass = sc.nextLine();

        String query = "select * from hostauth";

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            String username = "";
            String password = "";
            while (rs.next()) {
                username = rs.getString(1);
                password = rs.getString(2);
            }

            if (usname.equals(username) && password.equals(pass)) {
                hostLogInFlag = true;
                dataBaseManage.insertRemove(con);
            }else {
                System.out.println();
                System.out.println(Color.RED+"Invalid username and password"+Color.RESET);
                System.out.println();
                try {
                    Thread.sleep(1000);
                }catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
