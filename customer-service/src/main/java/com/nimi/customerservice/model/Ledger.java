package com.nimi.customerservice.model;

import lombok.Data;

@Data
public class Ledger {
    private Long id;
    private String name;
    private Long customerId;
}
