package com.nimi.customerservice.controller;

import com.nimi.customerservice.domain.Customer;
import com.nimi.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public List<Customer> getAllCustomer(){
        return customerService.fetchAllCustomer();
    }

    @GetMapping("/id/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") Long customerId){
        Customer customer = customerService.fetchCustomerById(customerId);
            return customerService.fetchCustomerById(customerId);
    }

    @GetMapping("/first/{firstName}")
    public Customer getCustomerByFirstName(@PathVariable("firstName") String firstName){
        return customerService.fetchCustomerByFirstName(firstName);
    }
    @GetMapping("/nic/{nic}")
    public Customer getCustomerByNic(@PathVariable("nic") String nic){
        return customerService.fetchCustomerByNic(nic);
    }
    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }
}
