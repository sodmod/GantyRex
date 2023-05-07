package com.Ganty.GantyRex.ExceptionalAdvice.ExceptionsControllerAdvice;

import com.Ganty.GantyRex.ExceptionalAdvice.Exceptions.CustomerNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

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
}
