package com.Ganty.GantyRex.customers;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;
    @Column(
            name = "firstname",
            nullable = false
    )
    private String firstname;
    @Column(
            name = "lastname",
            nullable = false
    )
    private String lastname;
    @Column(
            name = "othername",
            nullable = false
    )
    private String othername;
    @Column(
            name = "email",
            nullable = false
    )
    private String email;
    @Column(
            name = "address",
            nullable = false
    )
    private String address;
    @Column(
            name = "phonenumber",
            nullable = false
    )
    private String phonenumber;
    @Column(
            name = "accountnumber",
            unique = true,
            nullable = false
    )
    private long accountNumber;

    public Customers(String firstname, String lastname, String othername, String email,
                     String address, String phonenumber, long accountNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.othername = othername;
        this.email = email;
        this.address = address;
        this.phonenumber = phonenumber;
        this.accountNumber = accountNumber;
    }
}
