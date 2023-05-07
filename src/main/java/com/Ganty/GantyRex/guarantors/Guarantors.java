package com.Ganty.GantyRex.guarantors;

import com.Ganty.GantyRex.customers.Customers;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Table
@Data
public class Guarantors {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String othername;
    @Column
    private String email;
    @Column
    private String address;
    @Column
    private String phonenumber;
    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinTable(
            name = "customer_guarantors",
            joinColumns = @JoinColumn(
                    name = "guarantor_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "customer_id"
            )
    )
    @JoinColumn(name = "customers_id")
    private Customers customers;

    public Guarantors(String firstname, String lastname, String othername, String email, String address, String phonenumber, Customers customers) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.othername = othername;
        this.email = email;
        this.address = address;
        this.phonenumber = phonenumber;
        this.customers = customers;
    }
}
