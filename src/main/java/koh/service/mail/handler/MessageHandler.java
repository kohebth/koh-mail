package koh.service.mail.handler;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;

public interface MessageHandler {
    void handle(ConsumerRecord<String, String> rawMessage)
            throws IOException;
}
