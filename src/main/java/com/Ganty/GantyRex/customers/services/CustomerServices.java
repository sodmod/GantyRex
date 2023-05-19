package com.Ganty.GantyRex.customers.services;

import com.Ganty.GantyRex.ExceptionalAdvice.Exceptions.CustomerNotFoundException;
import com.Ganty.GantyRex.customers.Customers;
import com.Ganty.GantyRex.customers.repository.CustomersRepository;
import com.Ganty.GantyRex.models.CustomerDTO;
import com.Ganty.GantyRex.transactions.repos.SavingsRepository;
import com.Ganty.GantyRex.transactions.savings.Savings;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServices {
    private final CustomersRepository customersRepository;
    private final SavingsRepository savingsRepository;
    public ResponseEntity<?> saveCustomer(@NonNull CustomerDTO customerDTO){
        if(customersRepository.existsByAccountNumber(customerDTO.getAccountNumber())){
            return ResponseEntity.status(404).body("account number already exist");
        }
        Customers customers = new Customers(
                customerDTO.getFirstname(),
                customerDTO.getLastname(),
                customerDTO.getOthername(),
                customerDTO.getEmail(),
                customerDTO.getAddress(),
                customerDTO.getPhonenumber(),
                customerDTO.getAccountNumber()
        );
        customersRepository.save(customers);
        Savings savings = new Savings(customers);
        savingsRepository.save(savings);
        return ResponseEntity.ok("saved Successfully");
    }
    public ResponseEntity<?> getAllCustomers(){
        List<Customers> allStudents = customersRepository.findAll();
        return ResponseEntity.ok().body(allStudents);
    }
    public ResponseEntity<?> customerDetails(int accountnumber){
        Customers customers =
                customersRepository
                        .findByAccountNumber(accountnumber)
                        .orElseThrow(
                                ()->new CustomerNotFoundException(
                                        accountnumber,
                                        "account number is empty"
                                )
                        );

        CustomerDTO customerDTO = new CustomerDTO(
                customers.getFirstname(),
                customers.getLastname(),
                customers.getOthername(),
                customers.getEmail(),
                customers.getAddress(),
                customers.getPhonenumber(),
                customers.getAccountNumber()
        );
        return ResponseEntity.status(200).body(customerDTO);
    }
    public String updateCustomer(@NonNull CustomerDTO customerDTO){
        Customers customers =
                customersRepository
                        .findByAccountNumber(
                                customerDTO.getAccountNumber()
                        ).orElseThrow(
                                ()-> new CustomerNotFoundException(
                                        customerDTO.getAccountNumber(),
                                        "customer not found")
                        );
        customers.setFirstname(customerDTO.getFirstname());
        customers.setLastname(customers.getLastname());
        customers.setOthername(customers.getOthername());
        customers.setEmail(customers.getEmail());
        customers.setAddress(customers.getAddress());
        customers.setPhonenumber(customers.getPhonenumber());
        customersRepository.save(customers);
        return "successfully";
    }

    private int generateAccountNumber(){
       return 0;
    }
}
