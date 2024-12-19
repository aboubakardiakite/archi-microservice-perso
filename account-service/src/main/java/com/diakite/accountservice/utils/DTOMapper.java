package com.diakite.accountservice.utils;

import com.diakite.accountservice.dto.AccountDTO;
import com.diakite.accountservice.dto.CardDTO;
import com.diakite.accountservice.dto.InformationDTO;
import com.diakite.accountservice.dto.LoanDTO;
import com.diakite.accountservice.entity.Account;

import java.util.List;

public class DTOMapper {

    public static AccountDTO mapToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setName(account.getName());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setSolde(account.getSolde());
        return accountDTO;
    }


    public static InformationDTO mapToInformationDTO(AccountDTO account, List<CardDTO> card, List<LoanDTO> loan) {
        InformationDTO informationDTO = new InformationDTO();
        informationDTO.setAccount(account);
        informationDTO.setCard(card);
        informationDTO.setLoan(loan);
        return informationDTO;
    }

}
