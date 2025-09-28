package org.example.host;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import org.example.gmail.Color;
import org.example.user.MainDataBaseInfo;

import org.example.JavaAssistant;
import org.example.datastructure.InputVerification;

public class DataBaseManage {

    InputVerification inputVerification = new InputVerification();

    JavaAssistant javaAssistant = new JavaAssistant();
    Scanner scanner = new Scanner(System.in);
    MainDataBaseInfo mainDataBaseInfo = new MainDataBaseInfo();
    String mainFilePath = "";

    Connection con;
    File mainFile;
    int terminate = 0;
    public void insertRemove(Connection con) {

        this.con = con;

        do {
            System.out.println();
            System.out.println("Press 1"+ Color.BLUE +" to Insert a file in a DataBase"+Color.RESET);
            System.out.println("Press 2"+Color.BLUE+" to Remove a file from a DataBase"+Color.RESET);
            System.out.println("Press 3"+Color.BLUE+" to get information about DataBase"+Color.RESET);
            System.out.println("Press 4"+Color.BLUE+" to LogOut the host account"+Color.RESET);

            System.out.println();
            try {
                Thread.sleep(250);
            }catch (Exception e) {
                System.out.println(e);
            }

            System.out.print(Color.CYAN+"Enter your choice : "+Color.RESET);
            int choice = inputVerification.getInputInt();

            switch (choice) {
                case 1 : {
                    // for insert a file
                    insertFile();
                    terminate = 1;
                }
                break;
                case 2 : {
                    // for remove a file
                    removeFile();
                    terminate = 1;
                }
                break;
                case 3 : {
                    // to get information about DataBase
                    checkDataBase();
                    terminate = 1;
                }
                break;
                case 4 : {

                    // ==== logic for LogOut ==== //
                    System.out.println();
                    System.out.println(Color.GREEN+"Logout successful"+Color.RESET);
                    System.out.println();
                    try {
                        Thread.sleep(2000);
                    }catch (Exception e) {
                        System.out.println(e);
                    }
                    terminate = 1;
                    javaAssistant.hostLogOut();
                }
                break;
                default : {
                    System.out.println();
                    System.out.println(Color.YELLOW+"You enter invalid choice, please enter valid choice"+Color.RESET);
                    terminate = 0;
                }
                break;
            }
        }while (terminate != 1);
    }

    // ==== create method for insert file in DataBase ==== //

    public void insertFile() {

        try {
            do {

                System.out.print(Color.CYAN+"Enter File name : "+Color.RESET);
                String fileName = scanner.nextLine();
                System.out.println();
                System.out.println("make sure file already save in D drive and pdfStore folder");
                System.out.println("also make sure file is max size 10 kilobyte");
                try {
                    Thread.sleep(2000);
                }catch (Exception e) {
                    System.out.println("This File is to large");
                }

                String filePath = "D:\\pdfStore\\"+ fileName;
                //System.out.println(filePath);    --> this is only when you want to check
                this.mainFilePath = filePath;
                File file = new File(filePath);
                // if file Exist then call for store in database and loop should break

                if (file.exists()) {
                    this.mainFile = file;
                    tampInsertFile(fileName);
                    break;
                }
                // else File not Exist
                else {
                    System.out.println();
                    System.out.println(Color.RED+"This file is NOT Exist"+Color.RESET);
                    System.out.println(Color.YELLOW+"Please enter valid file name, which is full-fill above conditions"+Color.RESET);
                    System.out.println();
                }
            }while (true);
        }catch (Exception e) {
            System.out.println(e);
        }
    }
    public void tampInsertFile(String fileName) {
        String query = "insert into main_application(file_name, contant) values(?, ?)";
        try {
            FileInputStream fileInputStream = new FileInputStream(mainFile);
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, fileName);
            preparedStatement.setBlob(2, fileInputStream);
            int rowAffected = preparedStatement.executeUpdate();
            System.out.println(Color.PURPLE+"File insert in DataBase successfully"+Color.RESET);
            if (rowAffected > 0) {
                insertRemove(con);
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    }
    public void removeFile() {
        mainDataBaseInfo.getInfo(con,3);
        try {
            String query = "select file_name from main_application";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ArrayList<String> fileNameTampDb = new ArrayList<>();

            while (resultSet.next()) {
                String fileName = resultSet.getString(1);
                fileNameTampDb.add(fileName);
            }

            System.out.println(Color.YELLOW+"Enter File name which is you want to delete"+Color.RESET);
            System.out.print(Color.CYAN+"Enter file name : "+Color.RESET);
            String fileName = scanner.nextLine();

            if (fileNameTampDb.contains(fileName)) {
                String sub = "delete from main_application where file_name = ?";
                PreparedStatement preparedStatement = con.prepareStatement(sub);
                preparedStatement.setString(1, fileName);
                int rowAffected = preparedStatement.executeUpdate();
                if (rowAffected > 0) {
                    System.out.println();
                    System.out.println(Color.YELLOW+"File deleted successfully"+Color.RESET);
                    insertRemove(con);
                }
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    }
    public void checkDataBase() {
        mainDataBaseInfo.getInfo(con, 1);
    }
}
