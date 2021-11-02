package com.nimi.ledgerservice.controller;

import com.nimi.ledgerservice.domain.Ledger;
import com.nimi.ledgerservice.domain.Transection;
import com.nimi.ledgerservice.domain.TransectionType;
import com.nimi.ledgerservice.service.LedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ledger")
public class LedgerController {

    @Autowired
    private LedgerService service;


    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Ledger> getLedgersForCustomer(@PathVariable("customerId") Long customerId){
        return service.findByCustomerId(customerId);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Ledger> saveLedger(@RequestBody Ledger ledger){
        Ledger responseLedger = service.saveLedger(ledger);
        return ResponseEntity.ok(responseLedger);
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Ledger> fetchAllLedgers(){
        return service.findAllLedgers();
    }

    @PostMapping(value = "/transection/depo/{ledgerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Transection addDeposit(@PathVariable("ledgerId") Long ledgerId,@RequestBody Transection transection){
        return service.addDeposit(ledgerId,transection);
    }

    @PostMapping(value = "/transection/width/{ledgerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Transection addWithdrawal(@PathVariable("ledgerId") Long ledgerId,@RequestBody Transection transection){
        return service.addWithdrawal(ledgerId,transection);
    }

    @GetMapping(value = "/transection/{ledgerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Transection> getTransectionsForLedger(@PathVariable("ledgerId") Long ledgerId){
        return service.findTransectionForLedger(ledgerId);
    }

    @GetMapping("/byType/{type}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<Transection> fechTransectionByType(@PathVariable("type") TransectionType type){
        return service.findTransectionByType(type);
    }

    @GetMapping("/byLedger/{ledgerId}")
    public List<Transection> fetchTransectionForLedger(@PathVariable("ledgerId") Long ledgerId){
        return service.fetchTransectionForLedger(ledgerId);
    }
}
