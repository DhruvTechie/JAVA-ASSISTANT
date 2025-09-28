package org.example.user;

import org.example.JavaAssistant;
import org.example.gmail.Color;
import org.example.host.DataBaseManage;
import org.example.host.HostAuthHandler;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.example.datastructure.Stack;

public class MainDataBaseInfo {

    Connection con;
    int stackSize;

    //DataBaseManage dataBaseManage = new DataBaseManage();
    HashMap<Integer, String> tampFileName = new HashMap<>();
    Stack stack;
    int hostUser;
    public void getInfo(Connection connection, int hostUser) {

        this.hostUser = hostUser;
        this.con = connection;
        try {
            String query = "select * from main_application";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                count++;
            }
            stackSize = count;

            if (stackSize > 0) {
                Stack stack1 = new Stack(stackSize);
                stack = stack1;
                getDataBaseInfo();
            }else {
                System.out.println();
                System.out.println(Color.YELLOW+"here table is EMPTY"+Color.RESET);
                // host call
                System.out.println(Color.RED+"Here data base is Empty so that program will close, please RESTART the program"+Color.RESET);
                try {
                    Thread.sleep(2000);
                }catch (Exception e) {
                    System.out.println(e);
                }
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    }
    public void getDataBaseInfo() {
        try {
            String query = "select file_name from main_application";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String fileName = resultSet.getString(1);
                stack.push(fileName);
            }
            //stack.display();

            for (int i = stackSize; i > 0; i--) {
                tampFileName.put(i, stack.pop());
            }
            //System.out.println(tampFileName);
            // to print all file base on there order
            printFileName(hostUser);

        }catch (Exception e) {
            System.out.println(e);
        }
    }
    public void printFileName(int choice) {
        System.out.println();
        for (int i = 1; i <= stackSize; i++) {
            System.out.println(Color.YELLOW+"Index : "+Color.CYAN + i +Color.PURPLE+"   --> File Name : "+Color.CYAN +tampFileName.get(i)+Color.RESET);
            try {
                Thread.sleep(250);
            }catch (Exception e) {
                System.out.println(e);
            }
        }
        if (choice == 1) {

            ShowData showData = new ShowData();
            showData.mainApplication(con, tampFileName, stackSize, choice);
        }
        else if (choice == 2) {
            // call here method which show user interface
            ShowData showData = new ShowData();
            showData.mainApplication(con, tampFileName, stackSize, choice);
        }
        else if (choice == 3) {

        }
    }
}
