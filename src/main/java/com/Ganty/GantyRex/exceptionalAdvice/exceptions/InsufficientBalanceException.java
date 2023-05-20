package com.Ganty.GantyRex.exceptionalAdvice.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@ResponseStatus(NOT_ACCEPTABLE)
public class InsufficientBalanceException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public InsufficientBalanceException (long accountNumber){
        super(
                String.format(
                        "insufficient balance account number %d",
                        accountNumber)
        );
    }
}
