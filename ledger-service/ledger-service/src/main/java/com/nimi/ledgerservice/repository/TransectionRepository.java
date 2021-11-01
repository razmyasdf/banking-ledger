package com.nimi.ledgerservice.repository;

import com.nimi.ledgerservice.domain.Transection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransectionRepository extends JpaRepository<Transection,Long> {
    @Query("select t from Transection t where t.ledger.id=?1")
    List<Transection> findByLedger(Long ledgerId);
}
