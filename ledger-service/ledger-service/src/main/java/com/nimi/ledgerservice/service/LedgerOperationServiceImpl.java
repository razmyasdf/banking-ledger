package com.nimi.ledgerservice.service;

import com.nimi.ledgerservice.domain.Ledger;
import com.nimi.ledgerservice.domain.Transection;
import com.nimi.ledgerservice.domain.TransectionType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public  class LedgerOperationServiceImpl implements LedgerOperationsService{
    @Override
    public double getCurrentBalance(Ledger ledger) {
        double deposit = ledger.getTransections()
                .stream()
                .filter(transection -> transection.getTransectionType().equals(TransectionType.DEPOSIT))
                .mapToDouble(Transection::getAmount).sum();

        double withrawel = ledger.getTransections()
                .stream()
                .filter(transection -> transection.getTransectionType().equals(TransectionType.WITHDRAW))
                .mapToDouble(Transection::getAmount).sum();

        return deposit-withrawel;
    }

    @Override
    public List<Transection> getTransectionByTypeForLedger(Ledger ledger, TransectionType type) {
        return ledger.getTransections().stream()
                .filter(transection -> transection.getTransectionType().equals(type))
                .collect(Collectors.toList());
    }
}
