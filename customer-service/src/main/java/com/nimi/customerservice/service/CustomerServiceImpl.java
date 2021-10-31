package com.nimi.customerservice.service;

import com.nimi.customerservice.domain.Customer;
import com.nimi.customerservice.exception.CustomerExistException;
import com.nimi.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    private final   CustomerRepository customerRepository;

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
}
