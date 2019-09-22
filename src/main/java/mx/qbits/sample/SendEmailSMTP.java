package mx.qbits.sample;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmailSMTP {
    public static void main(String[] args) {
        // change accordingly
        String to = "somebody@gmail.com";

        // change accordingly
        String from = "no-reply@kebblar.io";

        // or IP address
        String host = "smtp.gmail.com";

        // mail id
        final String username = "no-reply@xyz.io";

        // correct password for gmail id
        final String password = "secret";

        System.out.println("TLSEmail Start");
        // Get the session object

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // SSL Port
        properties.put("mail.smtp.port", "465");

        // enable authentication
        properties.put("mail.smtp.auth", "true");

        // SSL Factory
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // creating Session instance referenced to
        // Authenticator object to pass in
        // Session.getInstance argument
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {

            // override the getPasswordAuthentication
            // method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // compose the message
        try {
            // javax.mail.internet.MimeMessage class is mostly
            // used for abstraction.
            MimeMessage message = new MimeMessage(session);

            // header field of the header.
            message.setFrom(new InternetAddress(from));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("subject");
            message.setText("Hello, java & gmail are sending a test email");

            // Send message
            Transport.send(message);
            System.out.println("Your mail it has been sent..");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
