package com.diakite.accountservice.controllers;
import java.util.List;

import com.diakite.accountservice.services.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.diakite.accountservice.entity.Account;
import com.diakite.accountservice.services.AccountService;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {


    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }



    
    
}
