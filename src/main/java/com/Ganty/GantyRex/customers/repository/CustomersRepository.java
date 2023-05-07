package com.Ganty.GantyRex.customers.repository;

import com.Ganty.GantyRex.customers.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long> {
    Optional<Customers> findByAccountNumber(long accountNumber);

    Boolean existsByAccountNumber(long accountNumber);

}
