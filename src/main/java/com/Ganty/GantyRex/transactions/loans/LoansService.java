package com.Ganty.GantyRex.transactions.loans;

import com.Ganty.GantyRex.transactions.dto.loansDTOs.ApplyLoansDTO;
import com.Ganty.GantyRex.transactions.dto.loansDTOs.LoanDTOs;

import java.util.List;

public interface LoansService {
    void applyLoad(ApplyLoansDTO applyLoansDTO);
    Object loanPayment(long accountNumber, float amount);
    float loanBalance(float loanBalance, float amountToPay);

    LoanDTOs loanStatus(long accountNumber);
    List<LoanDTOs> findAllLoans();
}
