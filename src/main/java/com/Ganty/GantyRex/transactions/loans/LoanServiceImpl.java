package com.Ganty.GantyRex.transactions.loans;

import com.Ganty.GantyRex.exceptionalAdvice.exceptions.CustomerNotFoundException;
import com.Ganty.GantyRex.customers.Customers;
import com.Ganty.GantyRex.customers.repository.CustomersRepository;
import com.Ganty.GantyRex.exceptionalAdvice.exceptions.LoanException;
import com.Ganty.GantyRex.guarantors.Guarantors;
import com.Ganty.GantyRex.guarantors.repository.GuarantorsRepository;
import com.Ganty.GantyRex.models.container_classes.Interest;
import com.Ganty.GantyRex.transactions.Transactions;
import com.Ganty.GantyRex.transactions.dto.GuarantorsDTO;
import com.Ganty.GantyRex.transactions.dto.loansDTOs.ApplyLoansDTO;
import com.Ganty.GantyRex.transactions.dto.loansDTOs.LoanDTOs;
import com.Ganty.GantyRex.transactions.repos.LoanRepository;
import com.Ganty.GantyRex.transactions.repos.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LoanServiceImpl implements LoansService{
    private final CustomersRepository customersRepository;
    private final GuarantorsRepository guarantorsRepository;
    private final LoanRepository loanRepository;
    private final TransactionRepository transactionRepository;
    @Autowired
    private Loans loans;
    @Autowired
    private Customers customers;
    @Override
    public void applyLoad(ApplyLoansDTO applyLoansDTO) {
        customers =
                customersRepository
                        .findByAccountNumber(applyLoansDTO.getAccountNumber())
                        .orElseThrow(
                                ()-> new CustomerNotFoundException(
                                        applyLoansDTO.getAccountNumber(),
                                        "customer not found")
                        );

        List<Guarantors> guarantorsList = new ArrayList<>();
        if (loanRepository.existsByCustomers(customers)){
            if (loanRepository.findByCompleted(customers)){
                for (GuarantorsDTO guarantorsDTO: applyLoansDTO.getGuarantorsDTO()) {
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
                Interest interest = getValues(applyLoansDTO.getCapitalBorrowed());
                loans =
                        new Loans(
                                applyLoansDTO.getCapitalBorrowed(),
                                interest.getTotalMoneyToBeReturned(),
                                interest.getInterestToBePaid(),
                                interest.getTotalMoneyToBeReturned(),
                                customers,
                                new Date(),
                                guarantorsList
                        );
                loanRepository.save(loans);
                Transactions transactions = new Transactions(loans,customers, applyLoansDTO.getCapitalBorrowed(), UUID.randomUUID().toString(),new Date());
                transactionRepository.save(transactions);
            } else {
                throw new RuntimeException();
            }

        }else{
            for (GuarantorsDTO guarantorsDTO: applyLoansDTO.getGuarantorsDTO()) {
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
            Interest interest = getValues(applyLoansDTO.getCapitalBorrowed());
            loans =
                    new Loans(
                            applyLoansDTO.getCapitalBorrowed(),
                            interest.getTotalMoneyToBeReturned(),
                            interest.getInterestToBePaid(),
                            interest.getTotalMoneyToBeReturned(),
                            customers,
                            new Date(),
                            guarantorsList
                    );
            loanRepository.save(loans);

            Transactions transactions = new Transactions(loans,customers, applyLoansDTO.getCapitalBorrowed(), UUID.randomUUID().toString(),new Date());
            transactionRepository.save(transactions);
        }
    }
    @Override
    public Object loanPayment(long accountNumber, float amount) {
        customers = customersRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(
                        ()-> new CustomerNotFoundException(
                                accountNumber,
                                "customer not found"
                        )
                );
        loans =
                loanRepository.findByCustomers(customers)
                        .orElseThrow(
                                ()-> new CustomerNotFoundException(
                                        accountNumber,
                                        "customer not found"
                                )
                        );
        float oldOutstanding = loans.getLoanBalance();

        if (!loans.isCompleted()){
            loans.setLoanBalance(loanBalance(loans.getLoanBalance(), amount));
            if (loans.getLoanBalance() <= 0.00){
                loans.setCompleted(true);
                Transactions transactions =
                        new Transactions(loans,customers,
                                amount,oldOutstanding,
                                loans.getLoanBalance(),UUID.randomUUID().toString(),new Date()
                        );
                loans = loanRepository.save(loans);
                transactionRepository.save(transactions);

            }else{
                Transactions transactions =
                        new Transactions(loans,customers,
                                amount,oldOutstanding,
                                loans.getLoanBalance(),UUID.randomUUID().toString(),new Date()
                        );
                loans = loanRepository.save(loans);
                transactionRepository.save(transactions);

            }
            return new LoanDTOs(
                    loans.getCustomers().getFirstname(),
                    loans.getCustomers().getLastname(),
                    loans.getCapitalBorrowed(),
                    loans.getMoneyReturned(),
                    loans.getLoanBalance(),
                    loans.isCompleted()
            );
        }else{
            throw new LoanException();
        }




    }
    @Override
    public float loanBalance(float loanBalance, float amountPaid) {
        return loanBalance - amountPaid;
    }
    @Override
    public LoanDTOs loanStatus(long accountNumber) {

        loans =
                loanRepository.findByCustomers(customersRepository.
                        findByAccountNumber(accountNumber)
                        .orElseThrow(
                                ()-> new CustomerNotFoundException(
                                        accountNumber,
                                        "account number not found"
                                )
                        )).orElseThrow(
                        ()-> new CustomerNotFoundException(
                                accountNumber,
                                "customer not found"
                        )
                );
        return
                new LoanDTOs(
                        loans.getCustomers().getFirstname(),
                        loans.getCustomers().getLastname(),
                        loans.getCapitalBorrowed(),
                        loans.getMoneyReturned(),
                        loans.getLoanBalance(),
                        loans.isCompleted()
                );
    }
    @Override
    public List<LoanDTOs> findAllLoans() {
        List<Loans> loans = loanRepository.findAll();
        List<LoanDTOs> loanDTOsList = new ArrayList<>();
        for (Loans loans1: loans) {
            LoanDTOs loanDTOs =
                    new LoanDTOs(
                            loans1.getCustomers().getFirstname(),
                            loans1.getCustomers().getLastname(),
                            loans1.getCapitalBorrowed(),
                            loans1.getMoneyReturned(),
                            loans1.getLoanBalance(),
                            loans1.isCompleted()
                    );
            loanDTOsList.add(loanDTOs);
        }
        return loanDTOsList;
    }
    Interest getValues(float capitalBorrowed){
        double interest = 15;
        return
                new Interest(
                        interest,
                        interestToBePaid(
                                interest,
                                capitalBorrowed
                        ),
                        totalMoneyToBeReturned(
                                interest,
                                capitalBorrowed
                        )
                );
    }
    private float totalMoneyToBeReturned(double interest, float capitalBorrowed){
        return interestToBePaid(interest, capitalBorrowed) + capitalBorrowed;
    }
    private float interestToBePaid(double interest, float capitalBorrowed){
        return (float) ((interest/100) * capitalBorrowed);
    }
}