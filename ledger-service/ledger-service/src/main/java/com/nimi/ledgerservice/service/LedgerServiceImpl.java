package com.nimi.ledgerservice.service;

import com.nimi.ledgerservice.domain.Ledger;
import com.nimi.ledgerservice.domain.Transection;
import com.nimi.ledgerservice.exception.NotEnoughFundException;
import com.nimi.ledgerservice.repository.LedgerRepository;
import com.nimi.ledgerservice.repository.TransectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LedgerServiceImpl implements LedgerService{

    private final LedgerRepository ledgerRepository;
    private final TransectionRepository transectionRepository;
    private final LedgerOperationsService ledgerOperationsService;

    @Autowired
    public LedgerServiceImpl(LedgerRepository ledgerRepository, TransectionRepository transectionRepository, LedgerOperationsService ledgerOperationsService) {
        this.ledgerRepository = ledgerRepository;
        this.transectionRepository = transectionRepository;
        this.ledgerOperationsService = ledgerOperationsService;
    }

    @Override
    public Ledger saveLedger(Ledger ledger) {
        return ledgerRepository.save(ledger);
    }

    @Override
    public List<Ledger> findAllLedgers() {
        return ledgerRepository.findAll();
    }

    @Override
    public Transection addDeposit(Ledger ledger, Transection transection) {
        transection.setLedger(ledger);
        return transectionRepository.save(transection);
    }

    @Override
    public Transection addWithdrawal(Ledger ledger, Transection transection) {
        double currentBalance = ledgerOperationsService.getCurrentBalance(ledger);
        if(currentBalance>transection.getAmount()){
            throw new NotEnoughFundException("Not Enough Fund Available");
        }
        transection.setLedger(ledger);
        return transectionRepository.save(transection);

    }
}
