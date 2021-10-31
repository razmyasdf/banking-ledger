package com.nimi.customerservice.repository;

import com.nimi.customerservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("SELECT c FROM Customer c WHERE C.firstName=?1")
    Customer findByFirstName(String firstName);
    @Query("SELECT c FROM Customer c WHERE C.nic=?1")
    Customer findByNic(String nic);
}