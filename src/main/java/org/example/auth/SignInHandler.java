package org.example.auth;

import org.example.gmail.Color;
import org.example.gmail.GMailSender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.example.datastructure.InputVerification;
import org.example.user.MainDataBaseInfo;
import org.example.user.ShowData;

public class SignInHandler {

    Connection con;
    InputVerification inputVerification = new InputVerification();
    ShowData showData = new ShowData();
    MainDataBaseInfo mainDataBaseInfo = new MainDataBaseInfo();

    Scanner sc = new Scanner(System.in);
    boolean flagGmail = false;
    boolean flagUserName = false;
    boolean flagPassWord = false;
    String mainGmail;
    String mainUserName;
    String mainPassWord;

    public void signIn(Connection con) {

        this.con = con;

        GMailSender gms = new GMailSender();
        Random random = new Random();

        System.out.print(Color.BLUE+"Enter your gamil Id : "+Color.RESET);
        String to = sc.nextLine();
        int randomNumber = 1000 + random.nextInt(9000);
        String[] TampGmail = {};

        // checking Gmail has already acount or not

        try {
            Statement st = con.createStatement();
            String quere1 = "select count(*) from auth";
            String quere2 = "select gmail from auth";
            int arraySize = 0;
            ResultSet rs1 = st.executeQuery(quere1);
            while (rs1.next()) {
                arraySize++;
            }
            ResultSet rs2 = st.executeQuery(quere2);
            String[] TampStoreGmail = new String[arraySize];
            int count = 0;
            while (rs2.next()) {
                String tamp = rs2.getString(1);
                TampStoreGmail[count] = tamp;
            }
            TampGmail = TampStoreGmail;
        }catch (Exception e) {
            System.out.println(e);
        }

        int ab = 0;
        for (String gamil : TampGmail) {
            if (gamil == null) {
                continue;
            }
            if (gamil.equals(to)) {
                System.out.println();
                System.out.println(Color.RED+"can not use same gmail for two different account"+Color.RESET);
                System.out.println();
                ab = 1;
                signIn(con);
            }
        }

        boolean send = false;
        if (ab == 0) {
            String from = "projectbootsem2@gmail.com";
            String subject = "verification of GMail";
            send = gms.gmailSender(to, from, subject, randomNumber);
        }

        if (send) {
            System.out.println();
            System.out.println(Color.GREEN+"Mail sent succesfully"+Color.RESET);
            System.out.println();
            int count = 0;
            while (true) {

                System.out.print(Color.BLUE+"Enter varification code : "+Color.RESET);
                int otp = inputVerification.getInputInt();
                //sc.next();

                if (randomNumber == otp) {
                    System.out.println(Color.GREEN+"Gmail varified succeully"+Color.RESET);
                    mainGmail = to;
                    flagGmail = true;
                    break;
                }if (count > 1) {
                    System.out.println(Color.RED+"gmail varification unsuccesfully"+Color.RESET);
                    System.out.println(Color.YELLOW+"program will start from begin"+Color.RESET);
                    signIn(con);
                    break;
                }else {
                    System.out.println(Color.RED+"Wrong OTP"+Color.RESET);
                    count++;
                    System.out.println("you have "+(3 - count) + " chance for enter valid otp");
                }
            }

            checkUserName();
            checkPassWord();

            if (flagGmail && flagPassWord && flagPassWord) {
                try {
                    Random random1 = new Random();
                    int key = random1.nextInt(10);
                    String enPass = "";
                    for (int i = mainPassWord.length()-1; i >= 0;i--) {
                        enPass = enPass + mainPassWord.charAt(i);
                    }
                    enPass = key + enPass + key;
                    mainPassWord = enPass;
                    String query = "INSERT INTO auth (gmail, username, password) VALUES (?, ?, ?)";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, mainGmail);
                    pst.setString(2, mainUserName);
                    pst.setString(3, mainPassWord);
                    int rowsAffected = pst.executeUpdate();

                    // ==== open this account --> signIn Successfully ==== //

                    mainDataBaseInfo.getInfo(con, 2);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        }else {
            System.out.println(Color.RED+"Mail cant sent"+Color.RESET);
        }
    }

    public void checkUserName() {
        ArrayList<String> tampUserName = new ArrayList<>();

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT username FROM auth")) {

            while (rs.next()) {
                tampUserName.add(rs.getString("username"));
            }
            //System.out.println(tampUserName);
        } catch (Exception e) {
            System.out.println("An error occurred while fetching usernames: " + e.getMessage());
            return;
        }

        //System.out.println(tampUserName);
        while (true) {
            System.out.print(Color.CYAN+"Enter user name: "+Color.RESET);
            String username = sc.next();
            sc.nextLine();

            boolean check = tampUserName.contains(username);

            if (check) {
                System.out.println(Color.YELLOW+"this username is already Exist"+Color.RESET);
                continue;
            }
            else if ((isUserNameValid(username))) {
                //System.out.println("This username is Invalid");
                continue;
            }
            else {
                mainUserName = username;
                flagUserName = true;
                break;
            }
        }
    }


    public void checkPassWord() {

        System.out.print(Color.CYAN+"Enter Password : "+Color.RESET);
        String password = sc.nextLine();
        System.out.print(Color.CYAN+"Enter confirm Password : "+Color.RESET);
        String cpassword = sc.nextLine();

        if (!(password.equals(cpassword))) {
            System.out.println();
            System.out.println("conform Password miss match");
            System.out.println("please reEnter password and conform password");
            System.out.println();
            checkPassWord();
        }else {
            mainPassWord = password;
            flagPassWord = true;
        }
    }

    public boolean isUserNameValid(String un){
        boolean flag = false;
        int count = 1;
        for (int i = 0; i < un.length(); i++) {
            char c=un.charAt(i);
            if((c>='a' && c<='z' )||(Character.isDigit(c)) || (c=='_')){
                count++;
            }
            else{
                System.out.println(Color.RED+"This user name is Invalid, please enter valid username"+Color.RESET);
                // time hoy to sopln ma condition add karvi
                flag = false;
                return flag;
            }
        }
        if (count == un.length()) {
            flag = true;
        }
        return flag;
    }
}
