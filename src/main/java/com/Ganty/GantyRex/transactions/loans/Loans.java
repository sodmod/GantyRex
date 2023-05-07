package com.Ganty.GantyRex.transactions.loans;

import com.Ganty.GantyRex.customers.Customers;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.EAGER;

@Data
@Entity
@Table(name = "loan")
@NoArgsConstructor
public class Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(
            columnDefinition = "float(24) default 0.0"
    )
    private float principalPaid;
    @Column(
            columnDefinition = "float(24) default 0.0"
    )
    private float interestPaid;
    @Column(
            columnDefinition = "float(24) default 0.0"
    )
    private float monthlyRepayment;
    @Column(
            columnDefinition = "float(24) default 0.0"
    )
    private float newBalance;
    private int loanYears;
    @ManyToMany(fetch = EAGER)
    @JoinColumn(name = "customers_id")
    private Customers customers;
    private Date paymentDate;

    public Loans(float principalPaid, float interestPaid, float monthlyRepayment, float newBalance, int loanYears, Date paymentDate, Customers customers) {
        this.principalPaid = principalPaid;
        this.interestPaid = interestPaid;
        this.monthlyRepayment = monthlyRepayment;
        this.newBalance = newBalance;
        this.loanYears = loanYears;
        this.paymentDate = paymentDate;
        this.customers = customers;
    }


}
