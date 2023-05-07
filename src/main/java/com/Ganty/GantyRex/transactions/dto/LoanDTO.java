package com.Ganty.GantyRex.transactions.dto;

import lombok.Data;

@Data
public class LoanDTO {
    private float amountBorrowed;
    private long accountNumber;
    private int loanYears;
    private GuarantorsDTO guarantorsDTO;
}
