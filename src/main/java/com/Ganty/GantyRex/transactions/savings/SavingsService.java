package com.Ganty.GantyRex.transactions.savings;

public interface SavingsService {
    float credit(float amount);
    float debit(float amount);
    float savingsBalance();
}
