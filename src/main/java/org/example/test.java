package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;

public class test {
    Connection con;
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e);
        }

        // JDBC Strings

        String url = "jdbc:mysql://localhost:3306/authentication";
        String user = "root";
        String pass = "";

        // Connection

        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            if (con != null) {
                //System.out.println("connection successfully");
            }
            Statement statement = con.createStatement();
            statement.executeUpdate("SET GLOBAL max_allowed_packet = 67108864;");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
