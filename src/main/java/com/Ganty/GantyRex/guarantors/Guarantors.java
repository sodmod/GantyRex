package com.Ganty.GantyRex.guarantors;

import com.Ganty.GantyRex.customers.Customers;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@NoArgsConstructor
@Table
@Getter
@Setter
@ToString
public class Guarantors {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
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
    @ManyToOne
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
