package org.example.auth;

import org.example.JavaAssistant;
import org.example.gmail.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SignOutHandler {
    public void signIn(Connection connection) {

        String username = LogInHandler.signInUserName;
        System.out.println(username);
        String query = "delete from auth where username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            int rowaffected = preparedStatement.executeUpdate();
            if (rowaffected > 0) {
                System.out.println();
                System.out.println(Color.PURPLE+"SignOut succesfully "+Color.RESET);
            }
            JavaAssistant javaAssistant = new JavaAssistant();
            javaAssistant.hostLogOut();
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
