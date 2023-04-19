package bll;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class AppServicesManager {
    public void sendEmail(String toEmail, String messageSubject, String yourTextMessage, String filePath, String fileName) throws MessagingException {
        String email = "kd.ticketingsystem@gmail.com";
        String password = "bvlweljfhujusmvc";

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        };

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, authenticator);

        // Create a message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(messageSubject);

        // Create a multipart message to include both text and attachment (if filePath is not empty)
        Multipart multipart = new MimeMultipart();

        // Add the text message
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(yourTextMessage);
        multipart.addBodyPart(messageBodyPart);

        // Add the attachment (if filePath is not empty)
        if (filePath != null && !filePath.isEmpty()) {
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(new File(filePath));
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);
        }

        // Set the content of the message to the multipart message
        message.setContent(multipart);

        // Send the message
        Transport.send(message);
    }

}