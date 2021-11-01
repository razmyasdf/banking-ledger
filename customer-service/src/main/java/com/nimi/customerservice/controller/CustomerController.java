package com.nimi.customerservice.controller;

import com.nimi.customerservice.domain.Customer;
import com.nimi.customerservice.response.CustomerLedgerResponse;
import com.nimi.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Customer> getAllCustomer(){
        return customerService.fetchAllCustomer();
    }

    @GetMapping("/{customerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Customer getCustomerById(@PathVariable("customerId") Long customerId){
        Customer customer = customerService.fetchCustomerById(customerId);
            return customerService.fetchCustomerById(customerId);
    }

    @GetMapping("/first/{firstName}")
    @PreAuthorize("hasRole('ADMIN')")
    public Customer getCustomerByFirstName(@PathVariable("firstName") String firstName){
        return customerService.fetchCustomerByFirstName(firstName);
    }
    @GetMapping("/nic/{nic}")
    @PreAuthorize("hasRole('ADMIN')")
    public Customer getCustomerByNic(@PathVariable("nic") String nic){
        return customerService.fetchCustomerByNic(nic);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/ledger/{customerId}")
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public CustomerLedgerResponse getLedgerForCustomer(@PathVariable("customerId") Long customerId){
        return customerService.fetchCustomerWithLedger(customerId);
    }
}
