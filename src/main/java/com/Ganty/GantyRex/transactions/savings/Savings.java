package com.Ganty.GantyRex.transactions.savings;

import com.Ganty.GantyRex.customers.Customers;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "savings")
public class Savings {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @Column(columnDefinition = "float(25) default 0.00")
    private float accountBalance;
    @OneToOne()
    @JoinColumn(name = "customerFK")
    private Customers customers;

    public Savings(Customers customers){
        this.customers = customers;
    }
}
