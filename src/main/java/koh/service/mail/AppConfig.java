package koh.service.mail;

import java.util.Properties;

public class AppConfig {
    public static final String MAIL_SMTP_HOST = System.getenv("MAIL_SMTP_HOST");
    public static final String MAIL_SMTP_PORT = System.getenv("MAIL_SMTP_PORT");
    public static final String MAIL_SMTP_SSL_TRUST = System.getenv("MAIL_SMTP_SSL_TRUST");
    public static final String MAIL_SMTP_STARTTLS_ENABLE = System.getenv("MAIL_SMTP_STARTTLS_ENABLE");
    public static final String MAIL_SMTP_AUTH = System.getenv("MAIL_SMTP_AUTH");
    public static final String MAIL_SMTP_USER = System.getenv("MAIL_SMTP_USER");
    private static final String MAIL_SMTP_PASSWORD = System.getenv("MAIL_SMTP_PASSWORD");

    public static final String KAFKA_HOST = System.getenv("KAFKA_HOST");
    public static final String KAFKA_PORT = System.getenv("KAFKA_PORT");
    public static final String KAFKA_GROUP = System.getenv("KAFKA_GROUP");


    static final Properties MAIL_PROPERTIES = new Properties();

    static {
        MAIL_PROPERTIES.setProperty("mail.smtp.host", MAIL_SMTP_HOST);
        MAIL_PROPERTIES.setProperty("mail.smtp.port", MAIL_SMTP_PORT);
        MAIL_PROPERTIES.setProperty("mail.smtp.user", MAIL_SMTP_USER);
        MAIL_PROPERTIES.setProperty("mail.smtp.password", MAIL_SMTP_PASSWORD);
        MAIL_PROPERTIES.setProperty("mail.smtp.starttls.enable", MAIL_SMTP_STARTTLS_ENABLE);
        MAIL_PROPERTIES.setProperty("mail.smtp.ssl.trust", MAIL_SMTP_SSL_TRUST);
        MAIL_PROPERTIES.setProperty("mail.smtp.auth", MAIL_SMTP_AUTH);
    }

    private AppConfig() {}
}
