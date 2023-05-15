package com.Ganty.GantyRex.transactions.loans;

import com.Ganty.GantyRex.ExceptionalAdvice.Exceptions.CustomerNotFoundException;
import com.Ganty.GantyRex.customers.Customers;
import com.Ganty.GantyRex.customers.repository.CustomersRepository;
import com.Ganty.GantyRex.guarantors.Guarantors;
import com.Ganty.GantyRex.guarantors.repository.GuarantorsRepository;
import com.Ganty.GantyRex.models.container_classes.Interest;
import com.Ganty.GantyRex.transactions.dto.GuarantorsDTO;
import com.Ganty.GantyRex.transactions.dto.LoanDTO;
import com.Ganty.GantyRex.transactions.repos.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanServiceImpl implements LoansService{
    private final CustomersRepository customersRepository;
    private final GuarantorsRepository guarantorsRepository;
    private final LoanRepository loanRepository;
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
        List<Guarantors> guarantorsList = new ArrayList<>();
        for (GuarantorsDTO guarantorsDTO: loanDTO.getGuarantorsDTO()) {
            Guarantors guarantors = new Guarantors(
                    guarantorsDTO.getFirstname(),
                    guarantorsDTO.getLastname(),
                    guarantorsDTO.getOthername(),
                    guarantorsDTO.getEmail(),
                    guarantorsDTO.getAddress(),
                    guarantorsDTO.getPhonenumber(),
                    customers
            );
            guarantorsRepository.save(guarantors);
            guarantorsList.add(guarantors);
        }

        Interest interest = getValues(loanDTO.getCapitalBorrowed());

        Loans loans =
                new Loans(
                        loanDTO.getCapitalBorrowed(),
                        interest.getTotalMoneyToBeReturned(),
                        interest.getInterestToBePaid(),
                        customers,
                        new Date(),
                        guarantorsList
                );
        loanRepository.save(loans);
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

    Interest getValues(float capitalBorrowed){
        double interest = 15;

        return new Interest(interest,interestToBePaid(interest, capitalBorrowed),totalMoneyToBeReturned(interest, capitalBorrowed));
    }

    private float totalMoneyToBeReturned(double interest, float capitalBorrowed){
        return interestToBePaid(interest, capitalBorrowed) + capitalBorrowed;
    }

    private float interestToBePaid(double interest, float capitalBorrowed){
        return (float) ((interest/100) * capitalBorrowed);
    }

}
