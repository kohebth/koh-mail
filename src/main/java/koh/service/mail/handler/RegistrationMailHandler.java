package koh.service.mail.handler;

import koh.service.mail.MailSender;
import koh.service.mail.kafka.KafkaJson;
import koh.service.mail.kafka.KafkaProducerWorker;
import koh.service.mail.message.MailMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;

@Slf4j
public class RegistrationMailHandler extends AbstractHandler {
    MailSender mailSender;

    public RegistrationMailHandler(KafkaProducerWorker bus, MailSender sender) {
        super(bus);
    }

    @Override
    public void handle(ConsumerRecord<String, String> rawMessage)
            throws IOException {
        MailMessage mailMessage = KafkaJson.fromJson(rawMessage.value(), MailMessage.class);

        StringBuilder messageText = new StringBuilder();
        messageText.append("Hello,\n\n");
        messageText.append("You are successfully registered!\n");
        messageText.append("To complete the registration progress, click on the link below.\n\n");
        messageText.append(mailMessage.getContent()).append("\n");
        messageText.append("\nThank you for using our platform!\n");
        messageText.append("\nSincerely\n").append("Kohebeth");
        try {
            mailSender.sendEmail(mailMessage.getEmail(), "Register Confirmation", messageText.toString());
        } catch (Exception e) {
            log.error("Unable to send an email", e);
        }
    }
}
