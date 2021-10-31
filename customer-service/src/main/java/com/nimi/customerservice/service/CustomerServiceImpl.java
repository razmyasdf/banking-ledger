package com.nimi.customerservice.service;

import com.nimi.customerservice.domain.Customer;
import com.nimi.customerservice.exception.CustomerExistException;
import com.nimi.customerservice.model.Ledger;
import com.nimi.customerservice.repository.CustomerRepository;
import com.nimi.customerservice.response.CustomerLedgerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final   CustomerRepository customerRepository;
    @Autowired
    private RestTemplate restTemplate;
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }




    @Override
    public Customer saveCustomer(Customer customer) {
        Customer customerExist = customerRepository.findByNic(customer.getNic());
        if(customerExist==null){
            return customerRepository.save(customer);
        }
        else {
            throw new CustomerExistException("Customer already exists");
        }
    }

    @Override
    public Customer fetchCustomerById(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.get();
    }

    @Override
    public Customer fetchCustomerByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    @Override
    public Customer fetchCustomerByNic(String nic) {
        return customerRepository.findByNic(nic);
    }

    @Override
    public List<Customer> fetchAllCustomer() {
       return customerRepository.findAll();
    }

    @Override
    public CustomerLedgerResponse fetchCustomerWithLedger(Long customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        ResponseEntity<Ledger[]> ledgers = restTemplate.getForEntity("http://localhost:8081/api/v1/ledger/customer/"+customerId,Ledger[].class);
        CustomerLedgerResponse customerLedgerResponse = new CustomerLedgerResponse();
        customerLedgerResponse.setCustomer(customer);
        List<Ledger> ledgers1 = Arrays.asList(ledgers.getBody());
        customerLedgerResponse.setLedgers(ledgers1);
        return customerLedgerResponse;
    }
}
