package com.nimi.customerservice.repository;

import com.nimi.customerservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("SELECT c FROM Customer c WHERE c.firstName=?1")
    Customer findByFirstName(String firstName);
    @Query("SELECT c FROM Customer c WHERE c.nic=?1")
    Customer findByNic(String nic);
    @Query("SELECT c FROM Customer c where c.userId=?1")
    Customer findByUserId(String userId);
}
