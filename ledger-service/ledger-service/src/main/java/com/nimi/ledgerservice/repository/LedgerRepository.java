package com.nimi.ledgerservice.repository;

import com.nimi.ledgerservice.domain.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface LedgerRepository extends JpaRepository<Ledger,Long> {

    @Override
    @Query("SELECT l from Ledger l")
    List<Ledger> findAll();

    @Query("select l from Ledger l where l.customerId=?1")
    List<Ledger> findLedgerByCustomerId(Long customerId);


}
