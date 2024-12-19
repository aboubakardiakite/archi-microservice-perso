package com.diakite.accountservice.services;
import java.util.List;
import java.util.Optional;

import com.diakite.accountservice.entity.Account;


public interface AccountService {

    Account createAccount(Account account);
    Optional<Account> getAccount(Long Id);
    List<Account> getAllAccounts();
    void deleteAccount(Long Id);
    Account updateAccount(Account account);
}