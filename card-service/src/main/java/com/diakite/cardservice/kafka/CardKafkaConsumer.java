package com.diakite.cardservice.kafka;

import com.diakite.cardservice.service.CardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CardKafkaConsumer {


    private static final Logger logger = LoggerFactory.getLogger(CardKafkaConsumer.class);

    @Autowired
    private CardService cardService;

    @KafkaListener(topics = "${spring.kafka.topic.account:account-events}", groupId = "${spring.kafka.consumer.group-id:card-group}")
    public void consumeAccountDeleteEvent(String event) {


        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(event);
            if("ACCOUNT_DELETED".equals(jsonNode.get("event").asText())) {
                logger.info("Received account delete event: {}", jsonNode);
                Long accountId = jsonNode.get("accountId").asLong();
                logger.info("Deleting all cards for account: {}", accountId);
                cardService.deleteCardByAccountId(accountId);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        logger.info("Consumed account delete event: {}", event);

    }


}
