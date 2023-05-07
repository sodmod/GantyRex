package com.Ganty.GantyRex.transactions.savings;

public interface SavingsService {
    void credit(long accountNumber, float amount);
    void debit(long accountNumber, float amount);
    float savingsBalance();
}
