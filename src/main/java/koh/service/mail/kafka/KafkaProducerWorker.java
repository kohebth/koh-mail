package koh.service.mail.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;

public class KafkaProducerWorker {
    final Producer<String, String> producer;

    public KafkaProducerWorker(Producer<String, String> producer) {
        this.producer = producer;
    }

    void send(ProducerRecord<String, String> message) {
        this.producer.send(message);
    }

    public void respond(KafkaRespTopic topic, String key, Object value)
            throws IOException {
        send(new ProducerRecord<>(topic.name(), key, KafkaJson.toJson(value)));
    }

    public void request(KafkaReqTopic topic, String key, Object value)
            throws IOException {
        send(new ProducerRecord<>(topic.name(), key, KafkaJson.toJson(value)));
    }
}
