package com.bezkoder.springjwt.email;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public class SimpleTextEmail {
    public static void main(String[] args) {
        try {
            Email email = new SimpleEmail();

            // Configuration
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator(Constants.MY_EMAIL,
                    Constants.MY_PASSWORD));

            // Required for gmail
            email.setSSLOnConnect(true);

            // Sender
            email.setFrom(Constants.MY_EMAIL);

            // Email title
            email.setSubject("Test Email");
            // Email message.
            email.setMsg("This is a test mail ... :-)");

            // Receiver
            email.addTo(Constants.FRIEND_EMAIL);
            email.send();
            System.out.println("Sent!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
