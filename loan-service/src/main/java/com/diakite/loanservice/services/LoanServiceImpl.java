package com.diakite.loanservice.services;

import com.diakite.loanservice.client.RestClient;
import com.diakite.loanservice.dto.AccountDTO;
import com.diakite.loanservice.entity.Loan;
import com.diakite.loanservice.kafka.LoanKafkaProducer;
import com.diakite.loanservice.repository.LoanRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    private static final Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private RestClient restClient;

    @Autowired
    private LoanKafkaProducer loanKafkaProducer;


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
    @Transactional
    public Loan saveLoan(Loan loan) {

        AccountDTO account = restClient.getAccount(loan.getAccountId());

        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        Loan savedLoan = loanRepository.save(loan);

        this.updateAccount(loan.getAccountId());

        return savedLoan;

    }

    public void deleteLoan(Long id) {
        Loan loan = this.getLoanById(id);

        if(loan == null) {
            throw new RuntimeException("Loan not found");
        }

        loanRepository.deleteById(id);

        this.updateAccount(loan.getAccountId());
    }

    @Override
    public void deleteLoanByAccountId(Long accountId) {
        logger.info("Deleting all cards for account: {}", accountId);
        this.loanRepository.deleteLoanByAccountId(accountId);
    }

    @Override
    public void updateAccount(Long accountId) {

        Integer loanCount = this.getLoansByAccountId(accountId).size();

        loanKafkaProducer.sendLoanUpdateEvent(accountId, loanCount);
    }
}

