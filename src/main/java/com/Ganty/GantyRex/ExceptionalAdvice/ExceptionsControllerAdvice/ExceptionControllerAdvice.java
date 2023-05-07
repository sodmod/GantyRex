package com.Ganty.GantyRex.ExceptionalAdvice.ExceptionsControllerAdvice;

import com.Ganty.GantyRex.ExceptionalAdvice.Exceptions.CustomerNotFoundException;
import com.Ganty.GantyRex.ExceptionalAdvice.Exceptions.InsufficientBalanceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = CustomerNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public CustomerNotFoundErrorMessage customerNotFound(CustomerNotFoundException exception){
        return new CustomerNotFoundErrorMessage(
               404,
                exception.getMessage(),
                new Date()
        );
    }

    @ExceptionHandler(value = InsufficientBalanceException.class)
    @ResponseStatus(NOT_ACCEPTABLE)
    public InSufficientBalanceErrorMessage inSufficientBalance(InsufficientBalanceException exception){
        return new InSufficientBalanceErrorMessage(
                NOT_ACCEPTABLE.value(),
                exception.getMessage(),
                new Date()
        );
    }
}
