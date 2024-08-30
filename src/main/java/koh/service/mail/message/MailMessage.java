package koh.service.mail.message;

import lombok.Getter;

import java.util.List;

@Getter
public class MailMessage {
    String email;
    String content;
    String url;
}
