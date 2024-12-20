package com.diakite.accountservice.kafka.consumers;


import com.diakite.accountservice.services.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AccountKafkaLoanConsumer {

    private static final Logger logger = LoggerFactory.getLogger(AccountKafkaLoanConsumer.class);

    @Autowired
    private AccountService accountService;

    @KafkaListener(topics = "${spring.kafka.topic.loan:loan-events-update}", groupId = "${spring.kafka.consumer.group-id:my-group}")
    public void consumeLoanEvent(String event) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(event);
            if("CARD_UPDATE".equals(jsonNode.get("event").asText())) {
                logger.info("Received card update event: {}", jsonNode);
                Long accountId = jsonNode.get("accountId").asLong();
                Integer nbLoans = jsonNode.get("nbLoans").asInt();
                logger.info("Updating card count for account: {}", accountId);
                accountService.updateAccountNbLoan(accountId, nbLoans);
            }
        }catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
