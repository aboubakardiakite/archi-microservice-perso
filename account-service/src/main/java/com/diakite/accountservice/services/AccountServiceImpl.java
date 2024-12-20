
package com.diakite.accountservice.services;

import java.util.List;
import java.util.Objects;

import com.diakite.accountservice.client.RestClient;
import com.diakite.accountservice.dto.AccountDTO;
import com.diakite.accountservice.dto.CardDTO;
import com.diakite.accountservice.dto.InformationDTO;
import com.diakite.accountservice.dto.LoanDTO;
import com.diakite.accountservice.kafka.AccountKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diakite.accountservice.entity.Account;
import com.diakite.accountservice.repository.AccountRepository;
import com.diakite.accountservice.utils.DTOMapper;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RestClient restClient;

    @Autowired
    private AccountKafkaProducer accountKafkaProducer;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public InformationDTO getAccountDetails(Long Id) {

        Account account = accountRepository.findById(Id).orElse(null);
        AccountDTO accountDTO = DTOMapper.mapToDTO(account);
        List<CardDTO> cards = restClient.getCard(Id);
        List<LoanDTO> loans = restClient.getLoan(Id);
        InformationDTO informationDTO = DTOMapper.mapToInformationDTO(accountDTO, cards, loans);

        return informationDTO;
    }

    @Override
    public AccountDTO getAccountById(Long Id) {
        return DTOMapper.mapToDTO(Objects.requireNonNull(accountRepository.findById(Id).orElse(null)));
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountKafkaProducer.sendAccountDeleteEvent(accountId);
        accountRepository.deleteById(Long.valueOf(accountId));
    }

    @Override
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }
}