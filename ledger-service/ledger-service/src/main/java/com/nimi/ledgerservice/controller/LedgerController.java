package com.nimi.ledgerservice.controller;

import com.nimi.ledgerservice.domain.Bank;
import com.nimi.ledgerservice.domain.Ledger;
import com.nimi.ledgerservice.repository.BankRepository;
import com.nimi.ledgerservice.service.LedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ledger")
public class LedgerController {

    @Autowired
    private LedgerService service;


    public ResponseEntity<Ledger> saveLedger(@RequestBody Ledger ledger){
        Ledger ledger1 = service.saveLedger(ledger);
        return ResponseEntity.ok(ledger1);
    }

    @GetMapping("/all")
    public List<Ledger> fetchAllLedgers(){
        return service.findAllLedgers();
    }



}
