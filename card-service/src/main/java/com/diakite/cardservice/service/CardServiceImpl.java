package com.diakite.cardservice.service;

import com.diakite.cardservice.client.RestClient;
import com.diakite.cardservice.dto.AccountDTO;
import com.diakite.cardservice.entity.Card;
import com.diakite.cardservice.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

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
}

