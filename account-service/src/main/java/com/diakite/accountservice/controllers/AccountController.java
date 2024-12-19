package com.diakite.accountservice.controllers;
import java.util.List;

import com.diakite.accountservice.dto.AccountDTO;
import com.diakite.accountservice.dto.InformationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.diakite.accountservice.entity.Account;
import com.diakite.accountservice.services.AccountService;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {


    @Autowired
    private AccountService accountService;

    @GetMapping("all")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public AccountDTO getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/details/{id}")
    public InformationDTO getAccountDetails(@PathVariable Long id) {
        return accountService.getAccountDetails(id);
    }

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {
        System.out.println("AccountController.getAccountById ");

        return accountService.createAccount(account);
    }



    
    
}
