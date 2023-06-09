package com.Ganty.GantyRex.Controllers;

import com.Ganty.GantyRex.customers.services.CustomerServices;
import com.Ganty.GantyRex.models.CustomerDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerServices customerServices;

    @PostMapping(value = "/save")
    public ResponseEntity<?> createCustomer(@RequestBody @NonNull CustomerDTO customerDTO){
        return customerServices.saveCustomer(customerDTO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllCustomers(){
        return customerServices.getAllCustomers();
    }

    @GetMapping(value = "/customer_details/{accountNumber}")
    public ResponseEntity<?> customerDetails(@PathVariable int accountNumber){
        return customerServices.customerDetails(accountNumber);
    }

    @PutMapping(value = "/update/customer")
    public ResponseEntity<?> updateCustomer(@RequestBody @NonNull CustomerDTO customerDTO){
        String response = customerServices.updateCustomer(customerDTO);
        return ResponseEntity.ok().body(response);
    }
}
