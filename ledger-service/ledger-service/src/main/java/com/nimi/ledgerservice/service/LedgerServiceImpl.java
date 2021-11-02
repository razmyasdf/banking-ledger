package com.nimi.ledgerservice.service;

import com.nimi.ledgerservice.domain.*;
import com.nimi.ledgerservice.exception.LedgerNotFountException;
import com.nimi.ledgerservice.exception.NotEnoughFundException;
import com.nimi.ledgerservice.model.Customer;
import com.nimi.ledgerservice.repository.LedgerRepository;
import com.nimi.ledgerservice.repository.TransectionRepository;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LedgerServiceImpl implements LedgerService{

    private final LedgerRepository ledgerRepository;
    private final TransectionRepository transectionRepository;
    private final LedgerOperationsService ledgerOperationsService;
    @Autowired
    private KeycloakRestTemplate template;

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
    public Transection addDeposit(Long ledgerId, Transection transection) {
        Ledger ledger = ledgerRepository.findById(ledgerId).get();
        if(ledger==null)
            throw new LedgerNotFountException("Ledger Not Fount");
        transection.setLedger(ledger);
        return transectionRepository.save(transection);
    }

    @Override
    public List<Ledger> findByCustomerId(Long customerId) {
        return ledgerRepository.findLedgerByCustomerId(customerId);
    }

    @Override
    public Transection addWithdrawal(Long ledgerId, Transection transection) {
        Ledger ledger = ledgerRepository.findById(ledgerId).get();
        double currentBalance = ledgerOperationsService.getCurrentBalance(ledger);
        if(currentBalance>transection.getAmount()){
            throw new NotEnoughFundException("Not Enough Fund Available");
        }
        transection.setLedger(ledger);
        return transectionRepository.save(transection);

    }

    @Override
    public List<Transection> findTransectionForLedger(Long ledgerId) {
        return transectionRepository.findByLedger(ledgerId);
    }

    @Override
    public List<Transection> findTransectionByType(TransectionType transectionType) {
        ResponseEntity<Customer> customerResponse = template.getForEntity("http://CUSTOMER-SERVICE/api/v1/customer/profile", Customer.class);
        Customer customer = customerResponse.getBody();
        System.out.println(customerResponse);
        List<Ledger> customerLedgers = ledgerRepository.findLedgerByCustomerId(customer.getId());
        if(customerLedgers.isEmpty()){
            throw new LedgerNotFountException("Ledger Not Found");
        }
        List<Transection> transectionList = new ArrayList<>();
        customerLedgers.stream()
                .forEach(
                        ledger -> ledger.getTransections()
                                .stream()
                                .filter(transection -> transection.getTransectionType().equals(transectionType))
                                .forEach(transection -> transectionList.add(transection)));
        return transectionList;
    }

    @Override
    public List<Transection> fetchTransectionForLedger(Long ledgerId) {
        ResponseEntity<Customer> customerResponse = template.getForEntity("http://CUSTOMER-SERVICE/api/v1/customer/profile", Customer.class);
        Customer customer = customerResponse.getBody();
        if(customer==null){
            throw new LedgerNotFountException("No Ledger Found");
        }
        List<Ledger> customerLedger = ledgerRepository.findLedgerByCustomerId(customer.getId());
        List<Transection> transectionList = new ArrayList<>();
        customerLedger.stream()
                .filter(ledger -> ledger.getId()==ledgerId)
                .forEach(ledger -> ledger.getTransections().stream()
                        .forEach(transection -> transectionList.add(transection)));
        return transectionList;
    }
}
