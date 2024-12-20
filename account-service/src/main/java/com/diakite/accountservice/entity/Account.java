package com.diakite.accountservice.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private Integer solde;

    private Integer nbCard;

    private Integer nbLoan;

    public Integer getNbCard() {
        return nbCard;
    }

    public void setNbCard(Integer nbCard) {
        this.nbCard = nbCard;
    }

    public Integer getNbLoan() {
        return nbLoan;
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
