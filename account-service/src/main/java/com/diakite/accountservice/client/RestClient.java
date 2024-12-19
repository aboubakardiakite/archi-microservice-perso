package com.diakite.accountservice.client;

import com.diakite.accountservice.dto.CardDTO;
import com.diakite.accountservice.dto.LoanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class RestClient {

    @Autowired
    private RestTemplate restTemplate;

    private final String cardsServiceUrl = "http://localhost:9091/api/v1/cards/accounts/";
    private final String loanServiceUrl = "http://localhost:9092/api/v1/loans/accounts/";

    public List<CardDTO> getCard(Long accountId) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(cardsServiceUrl)
                    .path(String.valueOf(accountId))
                    .toUriString();

            CardDTO[] cardArray = restTemplate.getForObject(url, CardDTO[].class);
            Arrays.asList(cardArray).forEach(
                    card -> System.out.println("++++++++++++++++++++++++++" + card.getCardNumber())
            );
            return cardArray != null ? Arrays.asList(cardArray) : Collections.emptyList();
        } catch (Exception e) {
            // Log the exception and return an empty list
            System.err.println("Error while fetching cards: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<LoanDTO> getLoan(Long accountId) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(loanServiceUrl)
                    .path(String.valueOf(accountId))
                    .toUriString();

            LoanDTO[] loanArray = restTemplate.getForObject(url, LoanDTO[].class);
            return loanArray != null ? Arrays.asList(loanArray) : Collections.emptyList();
        } catch (Exception e) {
            // Log the exception and return an empty list
            System.err.println("Error while fetching loans: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
