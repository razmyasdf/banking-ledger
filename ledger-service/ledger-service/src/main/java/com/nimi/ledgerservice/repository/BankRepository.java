package com.nimi.ledgerservice.repository;

import com.nimi.ledgerservice.domain.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank,Long> {

    @Override
    @Query("select b from Bank b")
    List<Bank> findAll();
}
