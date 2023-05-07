package com.Ganty.GantyRex.transactions.savings;

import com.Ganty.GantyRex.customers.Customers;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Data
@Entity
@Table(name = "savings")
public class Savings {
    @Id
    private Long id;
    private float accountBalance;
    private Date transactionDate;

    @ManyToOne(fetch = EAGER, cascade = ALL)
    @JoinTable(
            name = "customers_savings",
            joinColumns = @JoinColumn(
                    name = "saving_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "customer_id"
            )
    )
    private Customers customers;

}
