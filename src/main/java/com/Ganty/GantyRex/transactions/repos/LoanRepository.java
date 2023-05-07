package com.Ganty.GantyRex.transactions.repos;

import com.Ganty.GantyRex.transactions.loans.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loans, Long> {

//    @Query("SELECT l FROM LoansService")
//    List<LoansService> findByCustomer(int accountNumber);
}
