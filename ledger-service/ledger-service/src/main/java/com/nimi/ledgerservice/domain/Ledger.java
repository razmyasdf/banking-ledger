package com.nimi.ledgerservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@Entity
public class Ledger implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long customerId;
    @JsonIgnore
    @OneToMany(
            mappedBy = "ledger",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Transection> transections = new ArrayList<>();

    public Ledger(String name, Long customerId, List<Transection> transections) {
        this.name = name;
        this.customerId = customerId;
        this.transections = transections;
    }

    public Ledger(String name, Long customerId) {
        this.name = name;
        this.customerId = customerId;
    }

    public void addTransection(Transection transection){
        if(!this.transections.contains(transection)){
            this.transections.add(transection);
        }
    }
}
