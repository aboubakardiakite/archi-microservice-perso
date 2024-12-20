
package com.diakite.accountservice.services;

import java.util.List;
import java.util.Objects;

import com.diakite.accountservice.client.RestClient;
import com.diakite.accountservice.dto.AccountDTO;
import com.diakite.accountservice.dto.CardDTO;
import com.diakite.accountservice.dto.InformationDTO;
import com.diakite.accountservice.dto.LoanDTO;
import com.diakite.accountservice.kafka.AccountKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diakite.accountservice.entity.Account;
import com.diakite.accountservice.repository.AccountRepository;
import com.diakite.accountservice.utils.DTOMapper;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

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

    @Override
    public void updateAccountNbCard(Long accountId, Integer nbCard) {
        logger.info("Updating account nbCard: {}", accountId);
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account != null) {
            account.setNbCard(nbCard);
            accountRepository.save(account);
        }else {
            logger.error("Account not found");
        }


    }

    @Override
    public void updateAccountNbLoan(Long accountId, Integer nbLoan) {
        logger.info("Updating account nbLoan: {}", accountId);
        Account account = accountRepository.findById(accountId).
                orElseThrow(() -> new RuntimeException("Account not found"));
        if(account != null) {
            account.setNbLoan(nbLoan);
            accountRepository.save(account);

        }else {
            logger.error("Account not found");
        }

    }


}