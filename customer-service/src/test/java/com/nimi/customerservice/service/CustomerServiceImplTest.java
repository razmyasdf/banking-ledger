package com.nimi.customerservice.service;

import com.nimi.customerservice.domain.Customer;
import com.nimi.customerservice.exception.CustomerExistException;
import com.nimi.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository repository;
    private AutoCloseable autoCloseable;
    private CustomerService customerService;
    private Customer customer;

    @BeforeEach
    void setUp() {
         customer = new Customer("test","test","001",10L);
         autoCloseable = MockitoAnnotations.openMocks(this);
         customerService = new CustomerServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void saveCustomer() {
        Customer customer = new Customer("firstName","lastName","001",10L);
        //when
        customerService.saveCustomer(customer);
        //then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(repository).save(customerArgumentCaptor.capture());
        Customer capturedCustomer = customerArgumentCaptor.getValue();
        assertThat(capturedCustomer).isEqualTo(customer);

    }
    @Test
    void saveCustomerException() {
        //given
        Customer customer = new Customer("firstName","lastName","001",10L);
        //when
        customerService.saveCustomer(customer);
        given(customerService.fetchCustomerByNic(customer.getNic())).willReturn(customer);
        assertThatThrownBy(()->customerService.saveCustomer(customer))
                .isInstanceOf(CustomerExistException.class)
                .hasMessageContaining("Customer already exists");


    }

    @Test
    void fetchCustomerById() {

    }

    @Test
    void fetchCustomerByFirstName() {
        String firstName = "test";
        customerService.fetchCustomerByNic(firstName);
        verify(repository).findByNic(firstName);
    }

    @Test
    void fetchCustomerByNic() {
        String nic = "001";
        customerService.fetchCustomerByNic(nic);
        verify(repository).findByNic(nic);
    }

    @Test
    void fetchAllCustomer() {
        //when
        customerService.fetchAllCustomer();
        //then
        verify(repository).findAll();
    }
}