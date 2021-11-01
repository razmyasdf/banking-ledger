package com.nimi.customerservice.config;

import com.nimi.customerservice.domain.Customer;
import com.nimi.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {
    @Autowired
    private CustomerRepository repository;
    @Override
    public void run(String... args) throws Exception {

        List<Customer> customers = Arrays.asList(
                new Customer("Razmy","Mahsoom","8691","170dd251-47e3-46c2-ba77-61701adea431")

        );

        repository.saveAll(customers);

    }
}
