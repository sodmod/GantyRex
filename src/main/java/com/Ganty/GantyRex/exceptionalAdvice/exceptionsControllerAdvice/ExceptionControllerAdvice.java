package com.Ganty.GantyRex.exceptionalAdvice.exceptionsControllerAdvice;

import com.Ganty.GantyRex.exceptionalAdvice.exceptionEntities.ErrorMessage;
import com.Ganty.GantyRex.exceptionalAdvice.exceptions.CustomerNotFoundException;
import com.Ganty.GantyRex.exceptionalAdvice.exceptions.InsufficientBalanceException;
import com.Ganty.GantyRex.exceptionalAdvice.exceptions.LoanException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = CustomerNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorMessage customerNotFound(CustomerNotFoundException exception){
        return new ErrorMessage(
                NOT_FOUND.value(),
                exception.getMessage(),
                new Date()
        );
    }

    @ExceptionHandler(value = InsufficientBalanceException.class)
    @ResponseStatus(NOT_ACCEPTABLE)
    public ErrorMessage inSufficientBalance(InsufficientBalanceException exception){
        return new ErrorMessage(
                NOT_ACCEPTABLE.value(),
                exception.getMessage(),
                new Date()
        );
    }
    @ExceptionHandler(value = LoanException.class)
    @ResponseStatus(FORBIDDEN)
    public ErrorMessage loanException(LoanException exception){
        return new ErrorMessage(
                FORBIDDEN.value(),
                exception.getMessage(),
                new Date()
        );
    }
}
