package com.nimi.customerservice.repository;

import com.nimi.customerservice.domain.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    private Customer customer;

    @BeforeEach
    void setUp() {
     customer = new Customer("testFirstName","testLastName","001","test");
        customerRepository.save(customer);
    }
    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }
    @Test
    void findByFirstName() {
        //when
        Customer result = customerRepository.findByFirstName("testFirstName");
        //then
        assertThat(result).isEqualTo(customer);
    }
    @Test
    void findByNic(){
        Customer result = customerRepository.findByNic("001");
        assertThat(result).isEqualTo(customer);
    }
}