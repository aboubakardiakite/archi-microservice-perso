package com.diakite.loanservice.services;

import com.diakite.loanservice.entity.Loan;

import java.util.List;

public interface LoanService {

    public List<Loan> getAllLoans();

    public Loan getLoanById(Long id);

    public List<Loan> getLoansByAccountId(Long accountId);

    public Loan saveLoan(Loan loan);

    public void deleteLoan(Long id);

    void deleteLoanByAccountId(Long accountId);

    void updateAccount(Long accountId);

}
