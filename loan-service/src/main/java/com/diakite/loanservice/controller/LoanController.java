package com.diakite.loanservice.controller;

import com.diakite.loanservice.entity.Loan;
import com.diakite.loanservice.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {


    @Autowired
    private LoanService loanService;

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<List<Loan>> getLoansByAccountId(@PathVariable Long accountId) {
        return ResponseEntity.ok(loanService.getLoansByAccountId(accountId));
    }

    @PostMapping("/create")
    public Loan createLoan(@RequestBody Loan loan) {
        return loanService.saveLoan(loan);
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
    }


}
