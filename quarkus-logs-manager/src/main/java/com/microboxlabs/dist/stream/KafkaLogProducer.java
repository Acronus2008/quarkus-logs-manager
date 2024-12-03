package com.microboxlabs.dist.stream;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

@ApplicationScoped
public class KafkaLogProducer {
    private final Logger logger = LoggerFactory.getLogger(KafkaLogProducer.class);
    private KafkaProducer<String, String> producer;

    private final String bootstrapServers;
    private final String topic;

    public KafkaLogProducer(@ConfigProperty(name = "quarkus.kafka.bootstrap-servers") String bootstrapServers, @ConfigProperty(name = "kafka.producer.topic") String topic) {
        this.bootstrapServers = bootstrapServers;
        this.topic = topic;
    }


    @PostConstruct
    public void init() {
        producer = new KafkaProducer<>(configureProducer());
    }

    @PreDestroy
    public void close() {
        if (producer != null) {
            producer.close();
        }
    }

    public void sendLog(String key, String message) {
        try {
            logger.info("Sending message {} to {}", message, topic);
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, message);
            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    logger.error("Error sending message to Kafka: {}", exception.getMessage(), exception);
                } else {
                    logger.info("Message sent to topic {}, partition {}, offset {}",
                            metadata.topic(), metadata.partition(), metadata.offset());
                }
            });
        } catch (Exception e) {
            logger.error("Failed to send log to Kafka: {}", e.getMessage(), e);
        }
    }

    private Properties configureProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 3);
        return props;
    }
}
