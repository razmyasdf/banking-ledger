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
                new Customer("Razmy","Mahsoom","8691",1L),
                new Customer("Raza","Mahsoom","8891",2L),
                new Customer("Yahya","Razmy","1891",3L),
                new Customer("Hubab","Razmy","2091",4L),
                new Customer("Raziya","Nalim","9391",5L)
        );

        repository.saveAll(customers);

    }
}
