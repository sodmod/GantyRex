package com.Ganty.GantyRex.transactions.savings;

import com.Ganty.GantyRex.ExceptionalAdvice.Exceptions.CustomerNotFoundException;
import com.Ganty.GantyRex.ExceptionalAdvice.Exceptions.InsufficientBalanceException;
import com.Ganty.GantyRex.customers.Customers;
import com.Ganty.GantyRex.customers.repository.CustomersRepository;
import com.Ganty.GantyRex.transactions.Transactions;
import com.Ganty.GantyRex.transactions.repos.SavingsRepository;
import com.Ganty.GantyRex.transactions.repos.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SavingServicesImpl implements SavingsService{
    private final CustomersRepository customersRepository;
    private final SavingsRepository savingsRepository;
    private final TransactionRepository transactionRepository;
    private float initialBalance;
    private float newBalance;

    @Override
    public void credit(long accountNumber, float amountDeposited) {
        Customers customers =
                customersRepository
                        .findByAccountNumber(accountNumber)
                        .orElseThrow(
                                ()-> new CustomerNotFoundException(
                                        accountNumber,
                                        "customer not found"
                                )
                        );
        Savings savings =
                savingsRepository.findByCustomers(customers)
                        .orElseThrow(
                                ()-> new CustomerNotFoundException(
                                        customers.getAccountNumber(),
                                        "customer not found"
                                )
                        );
        initialBalance = savings.getAccountBalance();
        newBalance = newBalance(initialBalance, amountDeposited);
        savings.setAccountBalance(newBalance);
        savings = savingsRepository.save(savings);
        String transactionRef = UUID.randomUUID().toString();
        Transactions transactions =
                new Transactions(
                        savings,
                        customers,
                        initialBalance,
                        amountDeposited,
                        newBalance,
                        transactionRef,
                        new Date()
                );
        transactionRepository.save(transactions);
    }

    @Override
    public void debit(long accountNumber, float amountWithdrawn) {
        Customers customers =
                customersRepository
                        .findByAccountNumber(accountNumber)
                        .orElseThrow(
                                ()->new CustomerNotFoundException(
                                        accountNumber,
                                        "customer not found"
                                )
                        );
        Savings savings =
                savingsRepository.
                        findByCustomers(customers)
                        .orElseThrow(
                                ()-> new CustomerNotFoundException(
                                        customers.getAccountNumber(),
                                        "customer not found"
                                )
                        );
        initialBalance = savings.getAccountBalance();
        newBalance = debit(initialBalance,amountWithdrawn,accountNumber);
        savings.setAccountBalance(newBalance);
        savings = savingsRepository.save(savings);
        String transRef = UUID.randomUUID().toString();
        Transactions transactions =
                new Transactions(
                        savings,
                        customers,
                        initialBalance,
                        amountWithdrawn,
                        transRef,
                        newBalance,
                        new Date()
                );
        transactionRepository.save(transactions);
    }

    @Override
    public float savingsBalance() {
        return 0;
    }

    private float newBalance(float initialAmount, float amount){
        return initialAmount + amount;
    }

    private float debit(float initialAmount, float amountWithdrawn, long accountNumber){
        if(initialAmount > 0){
            return initialAmount - amountWithdrawn;
        }
        throw new InsufficientBalanceException(
                accountNumber,
                "insufficient balance"
        );
    }
}
