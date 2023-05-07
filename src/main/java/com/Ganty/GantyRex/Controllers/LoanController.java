package com.Ganty.GantyRex.Controllers;

import com.Ganty.GantyRex.transactions.TransactionService;
import com.Ganty.GantyRex.transactions.dto.LoanDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/loans/")
public class LoanController {
    private final TransactionService transactionService;

    @PostMapping(value = "applyloans")
    public String applyLoan(@RequestBody @NonNull LoanDTO loanDTO){
        return transactionService.applyLoan(loanDTO);
    }
}
