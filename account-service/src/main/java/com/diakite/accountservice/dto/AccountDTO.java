package com.diakite.accountservice.dto;


import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long id;

    private String name;

    private String email;

    private Integer solde;

    private Integer nbCard;

    private Integer nbLoan;

    public Integer getNbCard() {
        return nbCard;
    }

    public Integer getNbLoan() {
        return nbLoan;
    }

    public void setNbCard(Integer nbCard) {
        this.nbCard = nbCard;
    }

    public void setNbLoan(Integer nbLoan) {
        this.nbLoan = nbLoan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSolde() {
        return solde;
    }

    public void setSolde(Integer solde) {
        this.solde = solde;
    }


}
