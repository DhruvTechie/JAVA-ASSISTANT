package org.example;
import org.example.auth.AuthHandler;
import org.example.gmail.GMailSender;

import java.util.Random;
import java.util.Scanner;
public class TampGMail {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GMailSender gms = new GMailSender();
        Random random = new Random();

        System.out.print("Enter your gamil Id : ");
        String to = sc.nextLine();
        int randomNumber = 1000 + random.nextInt(9000);

        String from = "projectbootsem2@gmail.com";
        String subject = "varification of GMail";
        boolean send = gms.gmailSender(to, from, subject, randomNumber);

        if (send) {
            System.out.println();
            System.out.println("Mail sent succesfully");
            System.out.println();
            int count = 0;
            while (true) {

                System.out.print("Enter varification code : ");
                int otp = sc.nextInt();

                if (randomNumber == otp) {
                    System.out.println("Gmail varified succeully");
                    break;
                }if (count > 1) {
                    System.out.println("gmail varification unsuccesfully");
                    System.out.println("program should temineted");
                    break;
                }else {
                    System.out.println("Wrong OTP");
                    count++;
                    System.out.println("you have "+(3 - count) + " chance for enter valid otp");
                }
            }

        }else {
            System.out.println("Mail cant sent");
        }
    }
}
