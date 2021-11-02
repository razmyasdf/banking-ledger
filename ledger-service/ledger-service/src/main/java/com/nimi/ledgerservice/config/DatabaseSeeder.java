package com.nimi.ledgerservice.config;

import com.nimi.ledgerservice.domain.*;
import com.nimi.ledgerservice.repository.LedgerRepository;
import com.nimi.ledgerservice.service.LedgerOperationServiceImpl;
import com.nimi.ledgerservice.service.LedgerOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private LedgerRepository repository;

    @Override
    public void run(String... args) throws Exception {

        Ledger ledger = new Ledger();
        ledger.setName("Saving ledger");
        ledger.setCustomerId(1L);

        Transection t1 = new Transection();
        t1.setAmount(200.00);
        t1.setBank(Bank.HNB);
        t1.setTransectionType(TransectionType.DEPOSIT);
        t1.setCurrency(Currency.LKR);
        t1.setLedger(ledger);
        t1.setDateTime(LocalDateTime.now());

        Transection t2 = new Transection();
        t2.setAmount(10000);
        t2.setBank(Bank.HNB);
        t2.setTransectionType(TransectionType.DEPOSIT);
        t2.setCurrency(Currency.AUD);
        t2.setLedger(ledger);
        t2.setDateTime(LocalDateTime.now());

        Transection t3 = new Transection();
        t3.setAmount(100);
        t3.setBank(Bank.HNB);
        t3.setTransectionType(TransectionType.WITHDRAW);
        t3.setCurrency(Currency.AUD);
        t3.setLedger(ledger);
        t3.setDateTime(LocalDateTime.now());

        ledger.addTransection(t1);
        ledger.addTransection(t2);
        ledger.addTransection(t3);
        repository.save(ledger);


        List<Ledger> ledgers = Arrays.asList(
                new Ledger("Current Account",1L),
                new Ledger("NRFC Account",1L),
                new Ledger("Joint Account",1L)
        );

        repository.saveAll(ledgers);
    }
}
