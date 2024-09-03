package koh.service.mail;

import koh.service.mail.handler.ConfirmationMailHandler;
import koh.service.mail.handler.CredentialMailHandler;
import koh.service.mail.handler.RegistrationMailHandler;
import koh.service.mail.kafka.KafkaConsumerWorker;
import koh.service.mail.kafka.KafkaProducerWorker;
import koh.service.manager.vps.kafka.KafkaConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import static koh.service.mail.kafka.KafkaReqTopic.*;

public class App {
    final KafkaConfig kafkaConfig;
    final KafkaConsumer<String, String> consumer;
    final KafkaProducer<String, String> producer;
    final KafkaConsumerWorker consumerWorker;
    final KafkaProducerWorker producerWorker;
    final MailSender mailSender;

    App()
            throws Exception {
        this.kafkaConfig = new KafkaConfig(AppConfig.KAFKA_HOST, AppConfig.KAFKA_PORT, AppConfig.KAFKA_GROUP);
        this.consumer = new KafkaConsumer<>(this.kafkaConfig.getConsumerProperties());
        this.producer = new KafkaProducer<>(this.kafkaConfig.getProducerProperties());
        this.consumerWorker = new KafkaConsumerWorker(this.consumer);
        this.producerWorker = new KafkaProducerWorker(this.producer);
        this.mailSender = new MailSender(AppConfig.MAIL_SMTP_USER, AppConfig.MAIL_PROPERTIES);
    }

    public static void main(String[] args)
            throws Exception {
      new App().start();
    }

    void start() {
        consumerWorker.addHandler(
                TOPIC_MAIL_CONFIRMATION_REQUEST,
                new ConfirmationMailHandler(producerWorker, mailSender)
        );
        consumerWorker.addHandler(
                TOPIC_MAIL_REGISTRATION_REQUEST,
                new RegistrationMailHandler(producerWorker, mailSender)
        );
        consumerWorker.addHandler(TOPIC_MAIL_CREDENTIAL_REQUEST, new CredentialMailHandler(producerWorker, mailSender));
        consumerWorker.exec();
    }
}
