package koh.service.mail;

import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class MailSender {

    String sender;
    Properties authenticationProperties;
    MailAuthenticator authenticator;

    MailSender(String sender, Properties properties) {
        this.sender = sender;
        this.authenticationProperties = properties;
        this.authenticator = new MailAuthenticator(properties);
    }

    public void sendEmail(String toAddress, String subject, String messageText)
            throws Exception {
        Session session = Session.getInstance(authenticationProperties, authenticator);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(this.sender));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
        message.setSubject(subject);
        message.setText(messageText);

        Transport.send(message);
        log.info("Email has been sent: {}", message);
    }
}
