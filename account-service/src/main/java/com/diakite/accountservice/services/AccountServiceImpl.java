
package com.diakite.accountservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diakite.accountservice.entity.Account;
import com.diakite.accountservice.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> getAccount(Long Id) {
        return accountRepository.findById(Id);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(Long.valueOf(accountId));
    }

    @Override
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }
}