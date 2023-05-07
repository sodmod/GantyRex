package com.Ganty.GantyRex.transactions;

import com.Ganty.GantyRex.customers.Customers;
import com.Ganty.GantyRex.transactions.loans.Loans;
import com.Ganty.GantyRex.transactions.savings.Savings;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;
@NoArgsConstructor
@Data
@Entity
@Table
public class Transactions {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customersFK")
    private Customers customers;
    @ManyToMany
    @Column
    private List<Savings> savings;
    @ManyToMany
    @Column
    private List<Loans> loans;
    private float initialAmount;
    private float amountDeposited;
    private float amountWithdrawn;
    private float newBalance;
    @Column
    private String transactionReference;
    private Date transactionDate;

    public Transactions(Savings savings, Customers customers, float initialAmount, float amountDeposited, float finalAmount, String transactionReference, Date transactionDate){
        this.savings = List.of(savings);
        this.customers = customers;
        this.initialAmount = initialAmount;
        this.amountDeposited = amountDeposited;
        this.newBalance = finalAmount;
        this.transactionReference = transactionReference;
        this.transactionDate = transactionDate;
    }

    public Transactions(Savings savings, Customers customers, float initialAmount, float amountWithdrawn, String transactionReference, float finalAmount, Date transactionDate){
        this.savings = List.of(savings);
        this.customers = customers;
        this.initialAmount = initialAmount;
        this.amountWithdrawn = amountWithdrawn;
        this.newBalance = finalAmount;
        this.transactionReference = transactionReference;
        this.transactionDate = transactionDate;
    }

    public Transactions(Loans loans, Customers customers, float initialAmount, float amountWithdrawn, float finalAmount, String transactionReference, Date transactionDate){
        this.loans = List.of(loans);
        this.customers = customers;
        this.initialAmount = initialAmount;
        this.amountWithdrawn = amountWithdrawn;
        this.newBalance = finalAmount;
        this.transactionReference = transactionReference;
        this.transactionDate = transactionDate;
    }

    public Transactions(List<Loans> loans, Customers customers, float initialAmount,float amountWithdrawn, float finalAmount, String transactionReference, Date transactionDate){
        this.loans = loans;
        this.customers = customers;
        this.initialAmount = initialAmount;
        this.amountWithdrawn = amountWithdrawn;
        this.newBalance = finalAmount;
        this.transactionReference = transactionReference;
        this.transactionDate = transactionDate;
    }

}
