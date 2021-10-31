package com.nimi.customerservice.response;

import com.nimi.customerservice.domain.Customer;
import com.nimi.customerservice.model.Ledger;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerLedgerResponse {

    private Customer customer;
    private List<Ledger> ledgers = new ArrayList<>();

}
