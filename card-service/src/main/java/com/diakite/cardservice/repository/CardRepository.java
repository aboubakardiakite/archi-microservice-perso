package com.diakite.cardservice.repository;

import com.diakite.cardservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card>findByAccountId(Long accountId);

    void deleteCardByAccountId(Long accountId);



}
