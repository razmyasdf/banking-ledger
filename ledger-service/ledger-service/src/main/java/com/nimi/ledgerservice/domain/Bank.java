package com.nimi.ledgerservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@Entity
public class Bank implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bankName;
    private String shortCode;

    public Bank(String bankName, String shortCode) {
        this.bankName = bankName;
        this.shortCode = shortCode;
    }
}
