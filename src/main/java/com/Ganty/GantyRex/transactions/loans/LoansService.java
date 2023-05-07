package com.Ganty.GantyRex.transactions.loans;

import com.Ganty.GantyRex.transactions.dto.LoanDTO;

public interface LoansService {
    String applyLoad(LoanDTO loanDTO);
    float loanPayment();
    float loanBalance();
}
