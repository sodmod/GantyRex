package com.Ganty.GantyRex.Controllers;

import com.Ganty.GantyRex.transactions.TransactionService;
import com.Ganty.GantyRex.transactions.dto.loansDTOs.ApplyLoansDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/loans/")
public class LoanController {
    private final TransactionService transactionService;

    @PostMapping(value = "applyloan")
    public ResponseEntity<?> applyLoan(@RequestBody @NonNull ApplyLoansDTO applyLoansDTO){
        return transactionService.applyLoan(applyLoansDTO);
    }

    @PostMapping(value = "/loanPayment/{accountNumber}/{amount}")
    public ResponseEntity<?> loanPayment(@PathVariable long accountNumber, @PathVariable float amount){
        return transactionService.loanPayment(accountNumber, amount);
    }

    @GetMapping(value = "/status/{accountNumber}")
    public ResponseEntity<?> loanStatus(@PathVariable long accountNumber){
        return transactionService.loanStatus(accountNumber);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAllLoans(){
        return transactionService.findAllLoans();
    }

}
