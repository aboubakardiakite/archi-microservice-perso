package com.diakite.cardservice.controller;



import com.diakite.cardservice.entity.Card;
import com.diakite.cardservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public Card getCardById(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    @GetMapping("/accounts/{accountId}")
    public List<Card> getCardsByAccountId(@PathVariable Long accountId) {
        return cardService.getCardsByAccountId(accountId);
    }

    @PostMapping("/create")
    public Card createCard(@RequestBody Card card) {
        return cardService.saveCard(card);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }

}
