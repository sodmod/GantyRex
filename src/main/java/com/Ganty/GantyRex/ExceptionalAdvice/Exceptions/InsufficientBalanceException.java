package com.Ganty.GantyRex.ExceptionalAdvice.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@ResponseStatus(NOT_ACCEPTABLE)
public class InsufficientBalanceException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public InsufficientBalanceException (long accountnumber, String message){
        super(
                String.format(
                        "insufficient balance account number %d",
                        accountnumber,
                        message)
        );
    }
}
