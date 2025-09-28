package org.example.user;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Locale;

import org.example.JavaAssistant;
import org.example.auth.LogOutHandler;
import org.example.datastructure.Queue;
import org.example.gmail.Color;
import org.example.host.DataBaseManage;
import org.example.user.MainDataBaseInfo;
import org.example.datastructure.InputVerification;

import static java.nio.file.Files.*;

public class ShowData {

    Connection con;
    Queue queue = new Queue(7);
    MainDataBaseInfo mainDataBaseInfo = new MainDataBaseInfo();
    InputVerification inputVerification = new InputVerification();
    HashMap<Integer, String> tampFileNameStor= new HashMap<>();
    LogOutHandler logOutHandler = new LogOutHandler();
    int stackSize;
    String mainFileName = "";
    String filePath;
    int hostUser;
    public void mainApplication(Connection con, HashMap tampFileNameStor, int stackSize, int choice) {

        this.hostUser = choice;
        this.tampFileNameStor = tampFileNameStor;
        this.stackSize = stackSize;
        this.con = con;
        getMainInfo();

    }
    public void getMainInfo() {
        int breakLoop = 0;

        do {
            if (hostUser == 1) {

                System.out.println("Press "+(stackSize + 1)+" to you LogOut host account");
                System.out.println();
                System.out.println(Color.YELLOW+"This host account LogOUt successfully"+Color.RESET);

            }
            if (hostUser == 2) {

                System.out.println("Press "+(stackSize + 1)+" to  back or signout or LogOut");
            }
            System.out.println();
            System.out.print(Color.CYAN+"Enter the index number you want to search : "+Color.RESET);
            int choice = inputVerification.getInputInt();
            //queue.enqueue(choice);

            if (choice > 0 && choice <= stackSize) {
                String filename = tampFileNameStor.get(choice);
                mainFileName = filename;
                getPath(1);
                System.out.println("file:///D:/tampStore/"+mainFileName);
                try {
                    Thread.sleep(10000);
                }catch (Exception e) {
                    System.out.println(e);
                }
                getPath(2);
                if (hostUser == 1) {
                    breakLoop = 1;
                    DataBaseManage dataBaseManage = new DataBaseManage();
                    dataBaseManage.insertRemove(con);
                }else if (hostUser == 2) {
                    breakLoop = 1;
                    MainDataBaseInfo mainDataBaseInfo1 = new MainDataBaseInfo();
                    mainDataBaseInfo1.getInfo(con, 2);
                }
                breakLoop = 1;
            }else if (choice == (stackSize + 1) && hostUser == 1) {
                // for host
                JavaAssistant javaAssistant = new JavaAssistant();
                javaAssistant.hostLogOut();
                breakLoop = 1;
            }else if (choice == (stackSize + 1) && hostUser == 2) {
                // foe user
                logOutHandler.logOut(con);
                breakLoop = 1;
            }
            else {
                System.out.println();
                System.out.println(Color.YELLOW+"this integer choice is not in limit"+Color.RESET);
                System.out.println(Color.YELLOW+"Please enter valid choice"+Color.RESET);
                System.out.println();
            }
        }while (breakLoop == 0);
    }

    public void getPath(int choice) {
        String path = "";
        if (choice == 1) {
            String query = "select contant from main_application where file_name = ?";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, mainFileName);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    byte[] b = resultSet.getBytes("contant");
                    this.filePath = "D://tampStore/"+mainFileName;
                    path = filePath;
                    File file = new File(path);
                    //System.out.println(file.exists());
                    file.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(b);
                    System.out.println();
                }
            }catch (Exception e) {
                System.out.println(e);
            }
            if (choice == 2) {
                File file = new File(filePath);
                file.delete();
            }
        }
    }
}
