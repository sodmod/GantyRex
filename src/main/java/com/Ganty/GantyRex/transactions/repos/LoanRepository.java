package com.Ganty.GantyRex.transactions.repos;

import com.Ganty.GantyRex.customers.Customers;
import com.Ganty.GantyRex.transactions.loans.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loans, Long> {

    Optional<Loans> findByCustomers(Customers customers);
    @Query(value = "select u.completed from Loans u where u.customers = ?1")
    boolean findByCompleted(Customers customers);
}
