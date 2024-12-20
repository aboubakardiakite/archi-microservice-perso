package com.diakite.cardservice.service;

import com.diakite.cardservice.client.RestClient;
import com.diakite.cardservice.dto.AccountDTO;
import com.diakite.cardservice.entity.Card;
import com.diakite.cardservice.repository.CardRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CardServiceImpl implements CardService {

    private static final Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private RestClient restClient;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new RuntimeException("Card not found"));
    }

    public List<Card> getCardsByAccountId(Long accountId) {
        return cardRepository.findByAccountId(accountId);
    }

    public Card saveCard(Card card) {
        AccountDTO account = restClient.getAccount(card.getAccountId());

        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        return cardRepository.save(card);
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }

    @Override
    public void deleteCardByAccountId(Long accountId) {
        logger.info("Deleting all cards for account: {}", accountId);
        this.cardRepository.deleteCardByAccountId(accountId);
    }
}

