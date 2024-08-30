package koh.service.mail.handler;

import koh.service.mail.kafka.KafkaProducerWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

@Slf4j
public abstract class AbstractHandler implements MessageHandler {
    final KafkaProducerWorker bus;
    AbstractHandler(KafkaProducerWorker bus) {
        this.bus = bus;
    }
}
