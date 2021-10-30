package com.nimi.ledgerservice.repository;

import com.nimi.ledgerservice.domain.Transection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransectionRepository extends JpaRepository<Transection,Long> {
}
