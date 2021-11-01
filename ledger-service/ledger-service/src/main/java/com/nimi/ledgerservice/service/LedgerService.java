package com.nimi.ledgerservice.service;

import com.nimi.ledgerservice.domain.Ledger;
import com.nimi.ledgerservice.domain.Transection;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LedgerService {

    Ledger saveLedger(Ledger ledger);
    List<Ledger> findAllLedgers();
    Transection addDeposit(Long ledgerId, Transection transection);
    Transection addWithdrawal(Long ledgerId, Transection transection);
    List<Ledger> findByCustomerId(Long customerId);
    List<Transection> findTransectionForLedger(Long ledgerId);

}
