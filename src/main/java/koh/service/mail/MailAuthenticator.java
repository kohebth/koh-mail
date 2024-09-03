package koh.service.mail;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import lombok.Getter;

import java.util.Properties;

@Getter
class MailAuthenticator extends Authenticator {
    private final String username;
    private final String password;

    MailAuthenticator(Properties properties) {
        this.username = properties.getProperty("mail.smtp.user");
        this.password = properties.getProperty("mail.smtp.password");
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
