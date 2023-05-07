package com.Ganty.GantyRex.transactions.dto;

import lombok.Data;

@Data
public class TransactionDTO {
    private float amountSaved;
    private long accountNumber;
    private int loanYears;
}
