package com.Ganty.GantyRex.transactions;

import com.Ganty.GantyRex.transactions.dto.loansDTOs.ApplyLoansDTO;
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

    public ResponseEntity<?> savingsCredit(float amount, long accountNumber){
        savingServices.credit(accountNumber, amount);
        return ResponseEntity.ok().body("success");
    }

    public ResponseEntity<?> savingsDebit(long accountNumber, float amount) {
        savingServices.debit(accountNumber, amount);
        return ResponseEntity.ok().body("success");
    }

    public ResponseEntity<?> applyLoan(ApplyLoansDTO applyLoansDTO){
        loanService.applyLoad(applyLoansDTO);
        return ResponseEntity.ok("You have successfully applied for Loan");
    }

    public ResponseEntity<?> loanPayment(long accountNumber, float amount){
        return ResponseEntity.ok().body(loanService.loanPayment(accountNumber, amount));
    }

    public ResponseEntity<?> loanStatus(long accountNumber) {
        return ResponseEntity.ok().body(loanService.loanStatus(accountNumber));

    }

    public ResponseEntity<?> findAllLoans() {
        return ResponseEntity.ok().body(loanService.findAllLoans());
    }
}
