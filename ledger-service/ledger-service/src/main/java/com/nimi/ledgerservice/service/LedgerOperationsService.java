package com.nimi.ledgerservice.service;

import com.nimi.ledgerservice.domain.Ledger;
import com.nimi.ledgerservice.domain.Transection;
import com.nimi.ledgerservice.domain.TransectionType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

public interface LedgerOperationsService {

    public  double getCurrentBalance(Ledger ledger);
    public  List<Transection> getTransectionByTypeForLedger(Ledger ledger, TransectionType type);
}
