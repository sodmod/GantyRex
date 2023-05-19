package com.Ganty.GantyRex.transactions.dto.loansDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanDTOs {
    private String firstname;
    private String lastname;
    private float capitalBorrowed;
    private float moneyReturned;
    private float loanBalance;
    private boolean completed;

}
