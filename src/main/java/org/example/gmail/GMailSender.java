package org.example.gmail;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class GMailSender {

    public boolean gmailSender(String to, String from, String subject, int randoomNumber) {
        boolean flag = false;

        // logic
        // smtp properties

        Properties properties = new Properties();

        properties.put("mail.smtp.auth",true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");

        String username = "projectbootsem2";
        String password = "jumb dqlj yuok eeqt";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);

            String msg = "your OTP is "+randoomNumber+" PLESE do not shere your OTP whith anyone";

            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(to)});
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(msg);

            Transport.send(message);

            flag = true;

        }catch (Exception e) {
            e.getStackTrace();
        }
        return flag;
    }
}
