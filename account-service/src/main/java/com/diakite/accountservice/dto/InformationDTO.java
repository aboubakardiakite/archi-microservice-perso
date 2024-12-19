package com.diakite.accountservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InformationDTO {

    private AccountDTO account;
    private List<CardDTO> card;
    private List<LoanDTO> loan;

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public List<CardDTO> getCard() {
        return card;
    }

    public void setCard(List<CardDTO> card) {
        this.card = card;
    }

    public List<LoanDTO> getLoan() {
        return loan;
    }

    public void setLoan(List<LoanDTO> loan) {
        this.loan = loan;
    }



}
