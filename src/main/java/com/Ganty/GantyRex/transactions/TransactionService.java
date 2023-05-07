package com.Ganty.GantyRex.transactions;

import com.Ganty.GantyRex.customers.repository.CustomersRepository;
import com.Ganty.GantyRex.transactions.dto.LoanDTO;
import com.Ganty.GantyRex.transactions.loans.LoanServiceImpl;
import com.Ganty.GantyRex.transactions.savings.SavingServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionService  {
    private final LoanServiceImpl loanService;
    private final SavingServicesImpl savingServices;
    private final CustomersRepository customersRepository;

    public String applyLoan(LoanDTO loanDTO){
        loanService.applyLoad(loanDTO);
        return null;
    }
}
