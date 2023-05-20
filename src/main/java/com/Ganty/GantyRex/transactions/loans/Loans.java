package com.Ganty.GantyRex.transactions.loans;

import com.Ganty.GantyRex.customers.Customers;
import com.Ganty.GantyRex.guarantors.Guarantors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
@Table(name = "loan")
@NoArgsConstructor
@Component
public class Loans {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private long id;
    @Column(nullable = false)
    private float capitalBorrowed;
    @Column(nullable = false)
    private float moneyReturned;
    @Column(nullable = false)
    private float interestPaid;
    @Column(columnDefinition = "float(25) default 0.00 ")
    private float loanBalance;
    @Column(columnDefinition = "boolean default false ")
    private boolean completed;
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
    @ToString.Include
    private List<Guarantors> guarantors;
    @Column(nullable = false)
    private Date dateBorrowed;
    public Loans
            (
            float capitalBorrowed,
            float moneyReturned,
            float interestPaid,
            float outstandingLoan,
            Customers customers,
            Date dateBorrowed, List<Guarantors> guarantors)
    {
        this.capitalBorrowed = capitalBorrowed;
        this.moneyReturned = moneyReturned;
        this.interestPaid = interestPaid;
        this.loanBalance = outstandingLoan;
        this.customers = customers;
        this.dateBorrowed = dateBorrowed;
        this.guarantors = guarantors;
    }

}
