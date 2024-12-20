package com.diakite.cardservice.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CardKafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(CardKafkaProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.card:card-events}")
    private String topic;

    public CardKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCardUpdateEvent(Long accountId, Integer nbCards) {
        String event = String.format("{\"event\":\"CARD_UPDATE\",\"accountId\": %s, \"nbCards\": %s}", accountId, nbCards);
        logger.info("Sending card delete event: {}", event);
        kafkaTemplate.send(topic, event);
    }

}
