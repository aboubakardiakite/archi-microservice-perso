package com.diakite.loanservice.services;

import com.diakite.loanservice.client.RestClient;
import com.diakite.loanservice.dto.AccountDTO;
import com.diakite.loanservice.entity.Loan;
import com.diakite.loanservice.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private RestClient restClient;


    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    public List<Loan> getLoansByAccountId(Long accountId) {
        return loanRepository.findByAccountId(accountId);
    }

    @Override
    public Loan saveLoan(Loan loan) {

        AccountDTO account = restClient.getAccount(loan.getAccountId());

        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        return loanRepository.save(loan);
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }
}

