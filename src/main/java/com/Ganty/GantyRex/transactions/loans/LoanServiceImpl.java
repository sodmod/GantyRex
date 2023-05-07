package com.Ganty.GantyRex.transactions.loans;

import com.Ganty.GantyRex.ExceptionalAdvice.Exceptions.CustomerNotFoundException;
import com.Ganty.GantyRex.customers.Customers;
import com.Ganty.GantyRex.customers.repository.CustomersRepository;
import com.Ganty.GantyRex.guarantors.repository.GuarantorsRepository;
import com.Ganty.GantyRex.transactions.dto.LoanDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoanServiceImpl implements LoansService{
    private final CustomersRepository customersRepository;
    private final GuarantorsRepository guarantorsRepository;
    @Override
    public String applyLoad(LoanDTO loanDTO) {
        Customers customers =
                customersRepository
                        .findByAccountNumber(loanDTO.getAccountNumber())
                        .orElseThrow(
                                ()-> new CustomerNotFoundException(
                                        loanDTO.getAccountNumber(),
                                        "customernot found")
                        );

        Loans loans = new Loans();
        return null;
    }

    @Override
    public float loanPayment() {
        return 0;
    }

    @Override
    public float loanBalance() {
        return 0;
    }
}
