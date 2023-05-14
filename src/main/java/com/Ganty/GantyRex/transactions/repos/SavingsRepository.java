package com.Ganty.GantyRex.transactions.repos;

import com.Ganty.GantyRex.customers.Customers;
import com.Ganty.GantyRex.transactions.savings.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavingsRepository extends JpaRepository<Savings, Long> {

    @Query(
            value = "SELECT s.account_balance from Savings s where s.customers = ?1",
            nativeQuery = true
    )
    float accountBalance (Customers customers);
    Optional<Savings> findByCustomers(Customers customers);
}
