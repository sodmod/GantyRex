package com.Ganty.GantyRex.transactions;

import com.Ganty.GantyRex.transactions.dto.LoanDTO;
import com.Ganty.GantyRex.transactions.loans.LoanServiceImpl;
import com.Ganty.GantyRex.transactions.savings.SavingServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionService  {
    private final LoanServiceImpl loanService;
    private final SavingServicesImpl savingServices;

    public String applyLoan(LoanDTO loanDTO){
        loanService.applyLoad(loanDTO);
        return null;
    }

    public ResponseEntity<?> saveCredit(float amount, long accountNumber){
        savingServices.credit(accountNumber, amount);
        return ResponseEntity.ok().body("success");
    }

    public ResponseEntity<?> debit(long accountNumber, float amount) {
        savingServices.debit(accountNumber, amount);
        return ResponseEntity.ok().body("success");
    }
}
