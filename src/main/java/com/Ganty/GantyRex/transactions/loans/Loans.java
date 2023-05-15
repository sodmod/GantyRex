package com.Ganty.GantyRex.transactions.loans;

import com.Ganty.GantyRex.customers.Customers;
import com.Ganty.GantyRex.guarantors.Guarantors;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
@Table(name = "loan")
@NoArgsConstructor
public class Loans {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    @Column(nullable = false)
    private float capitalBorrowed;
    @Column(nullable = false)
    private float moneyReturned;
    @Column(nullable = false)
    private float interestPaid;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "customersFK"
    )
    private Customers customers;
    @ManyToMany
    @JoinTable(
            name = "loan_guarantors",
            joinColumns = @JoinColumn(name = "loan_id"),
            inverseJoinColumns = @JoinColumn(name = "guarantors_id")
    )
    private List<Guarantors> guarantors;
    @Column(nullable = false)
    private Date dateBorrowed;
//    @Column(nullable = false)
//    private Date dateToBeReturned;

    public Loans
            (
            float capitalBorrowed,
            float moneyReturned,
            float interestPaid,
            Customers customers,
            Date dateBorrowed, List<Guarantors> guarantors)
    {
        this.capitalBorrowed = capitalBorrowed;
        this.moneyReturned = moneyReturned;
        this.interestPaid = interestPaid;
        this.customers = customers;
        this.dateBorrowed = dateBorrowed;
        this.guarantors = guarantors;
//        this.dateToBeReturned = dateToBeReturned;
    }

}
