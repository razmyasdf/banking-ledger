package com.nimi.ledgerservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@Entity
public class Transection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TransectionType transectionType;
    @OneToOne(cascade = CascadeType.ALL)
    private Bank bank;
    private double amount;
    private LocalDateTime dateTime;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @ManyToOne
    @JoinColumn(
            name = "ledger_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "ledger_transection_id")
    )
    private Ledger ledger;

    public Transection(TransectionType transectionType, Bank bank, double amount,
                       LocalDateTime dateTime, Currency currency, Ledger ledger) {
        this.transectionType = transectionType;
        this.bank = bank;
        this.amount = amount;
        this.dateTime = dateTime;
        this.currency = currency;
        this.ledger = ledger;
    }
}
