package com.nimi.customerservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String nic;
    @Column(unique = true)
    private String userId;

    public Customer(String firstName, String lastName, String nic, String userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nic = nic;
        this.userId = userId;
    }
}
