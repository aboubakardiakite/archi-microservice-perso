package com.diakite.accountservice.services;
import java.util.List;

import com.diakite.accountservice.dto.AccountDTO;
import com.diakite.accountservice.dto.InformationDTO;
import com.diakite.accountservice.entity.Account;


public interface AccountService {

    Account createAccount(Account account);
    InformationDTO getAccountDetails(Long Id);
    AccountDTO getAccountById(Long Id);
    List<Account> getAllAccounts();
    void deleteAccount(Long Id);
    Account updateAccount(Account account);
}