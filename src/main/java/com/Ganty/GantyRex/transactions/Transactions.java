package com.Ganty.GantyRex.transactions;

import com.Ganty.GantyRex.customers.Customers;
import com.Ganty.GantyRex.transactions.loans.Loans;
import com.Ganty.GantyRex.transactions.savings.Savings;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;
@NoArgsConstructor
@Getter
@Setter
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

    private float SavingsInitialAmount;
    private float SavingsAmountDeposited;
    private float amountWithdrawn;
    private float SavingsNewBalance;
    @Column
    private String SavingsTransactionReference;
    private Date SavingsTransactionDate;
    @ManyToMany
    @Column
    private List<Loans> loans;
    private float oldOutstanding;
    private  float newOutstanding;
    private  float loanPaid;
    private  String LoansTransactionReference;
    private  Date LoansTransactionDate;

    /*
    * This Constructor below is used to deposit money into the savings account
    * */
    public Transactions(Savings savings, Customers customers, float initialAmount, float amountDeposited, float finalAmount, String transactionReference, Date transactionDate){
        this.savings = List.of(savings);
        this.customers = customers;
        this.SavingsInitialAmount = initialAmount;
        this.SavingsAmountDeposited = amountDeposited;
        this.SavingsNewBalance = finalAmount;
        this.SavingsTransactionReference = transactionReference;
        this.SavingsTransactionDate = transactionDate;
    }

    /*
    * This Constructor below is used to withdraw money from the savings account
    * */
    public Transactions(Savings savings, Customers customers, float initialAmount, float amountWithdrawn, String transactionReference, float finalAmount, Date transactionDate){
        this.savings = List.of(savings);
        this.customers = customers;
        this.SavingsInitialAmount = initialAmount;
        this.amountWithdrawn = amountWithdrawn;
        this.SavingsNewBalance = finalAmount;
        this.SavingsTransactionReference = transactionReference;
        this.SavingsTransactionDate = transactionDate;
    }

    /*
    * This Constructor below is uses to apply for new loans
    * */
    public Transactions(Loans loans, Customers customers, float newOutstanding, String transactionReference, Date transactionDate){
        this.loans = List.of(loans);
        this.customers = customers;
        this.newOutstanding = newOutstanding;
        this.LoansTransactionReference = transactionReference;
        this.LoansTransactionDate = transactionDate;
    }

    /*
    * This Constructor below is used to pay the outstanding loans*/
    public Transactions(Loans loans, Customers customers, float loanPaid, float oldOutstanding, float newOutstanding, String transactionReference, Date transactionDate){
        this.loans = List.of(loans);
        this.customers = customers;
        this.loanPaid = loanPaid;
        this.oldOutstanding = oldOutstanding;
        this.newOutstanding = newOutstanding;
        this.LoansTransactionReference = transactionReference;
        this.LoansTransactionDate = transactionDate;
    }

}
