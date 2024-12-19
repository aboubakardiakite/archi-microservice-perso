package com.diakite.cardservice.client;


import com.diakite.cardservice.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {

    @Autowired
    private RestTemplate restTemplate;

    private String accountServiceUrl = "http://localhost:9090/api/v1/accounts/";

    public AccountDTO getAccount(Long accountId) {
        return restTemplate.getForObject(accountServiceUrl + accountId, AccountDTO.class);
    }

}
