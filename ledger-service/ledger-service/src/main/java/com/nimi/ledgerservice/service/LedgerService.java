package com.nimi.ledgerservice.service;

import com.nimi.ledgerservice.domain.Ledger;
import com.nimi.ledgerservice.domain.Transection;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LedgerService {

    Ledger saveLedger(Ledger ledger);
    List<Ledger> findAllLedgers();
    Transection addDeposit(Ledger ledger, Transection transection);
    Transection addWithdrawal(Ledger ledger, Transection transection);

}
