package com.nimi.customerservice.service;

import com.nimi.customerservice.domain.Customer;
import com.nimi.customerservice.response.CustomerLedgerResponse;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(Customer customer);
    Customer fetchCustomerById(Long customerId);
    Customer fetchCustomerByFirstName(String firstName);
    Customer fetchCustomerByNic(String nic);
    List<Customer> fetchAllCustomer();
    CustomerLedgerResponse fetchCustomerWithLedger(Long id);
}
