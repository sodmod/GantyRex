package com.Ganty.GantyRex.exceptionalAdvice.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;


    public CustomerNotFoundException(long accountnumber, String message){
        super(
                String.format(
                        "customer with account number %d not found ",
                        accountnumber,
                        message
                )
        );

    }
}
