package com.Ganty.GantyRex.Controllers;

import com.Ganty.GantyRex.transactions.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "api/savings")
@RestController
@RequiredArgsConstructor
public class SavingsController {
    private final TransactionService transactionService;

    @PostMapping(value = "/credit/{accountnumber}/{amount}")
    public ResponseEntity<?> credit(@PathVariable long accountnumber, @PathVariable float amount){
        return transactionService.saveCredit(amount, accountnumber);
    }

    @PostMapping(value = "/debit/{accountNumber}/{amount}")
    public ResponseEntity<?> debit(@PathVariable long accountNumber, @PathVariable float amount){
        return transactionService.debit(accountNumber, amount);
    }



}
