package com.nimi.ledgerservice.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TransectionType transectionType;
    @OneToOne
    private Bank bank;
    private double amount;
    private LocalDateTime dateTime;
    private Currency currency;
    @ManyToOne
    private Ledger ledger;
}
