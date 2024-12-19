package com.diakite.accountservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {

    private Long id;

    private Double amount;
    private String type;
    private Long accountId;
}
