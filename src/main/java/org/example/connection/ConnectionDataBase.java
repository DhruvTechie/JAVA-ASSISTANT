package org.example.connection;

import org.example.auth.AuthHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import org.example.auth.*;
import org.example.host.HostAuthHandler;

public class ConnectionDataBase {

    Connection con;
    int hostUser = 0;
    AuthHandler authHandler = new AuthHandler();
    HostAuthHandler hostAuthHandler = new HostAuthHandler();
    public void connection(int hostUser) {

        this.hostUser = hostUser;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (Exception e) {
            System.out.println(e);
        }

        // JDBC Strings

        String url = "jdbc:mysql://localhost:3306/authentication";
        String user = "root";
        String pass = "142005";
        // use your database password

        // Connection

        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            if (con != null) {
                //System.out.println("connection successfully");
            }
            this.con = con;
        }catch (Exception e) {
            System.out.println(e);
        }

        switch (hostUser) {
            case 1 : {
                hostAuthHandler.hostLogIn(con);
            }
            break;
            case 2 : {
                authHandler.databaseConnection(con);
            }
            break;
        }
    }

}
