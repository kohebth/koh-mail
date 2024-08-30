package koh.service.mail.handler;

import koh.service.mail.MailSender;
import koh.service.mail.kafka.KafkaJson;
import koh.service.mail.kafka.KafkaProducerWorker;
import koh.service.mail.message.MailMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;

@Slf4j
public class CredentialMailHandler extends AbstractHandler {
    MailSender mailSender;

    public CredentialMailHandler(KafkaProducerWorker bus, MailSender sender) {
        super(bus);
    }

    @Override
    public void handle(ConsumerRecord<String, String> rawMessage)
            throws IOException {
        MailMessage mailMessage = KafkaJson.fromJson(rawMessage.value(), MailMessage.class);

        StringBuilder messageText = new StringBuilder();
        messageText.append("Hello,\n\n");
        messageText.append("You have requested to view a credential to access to your resource.\n");
        messageText.append("Click on the link below to continue.\n");
        messageText.append(mailMessage.getContent()).append("\n");
        messageText.append("\nThank you for using our platform!\n");
        messageText.append("\nSincerely\n").append("Kohebeth");
        try {
            mailSender.sendEmail(mailMessage.getEmail(), "Credential Access", messageText.toString());
        } catch (Exception e) {
            log.error("Unable to send an email", e);
        }
    }
}
