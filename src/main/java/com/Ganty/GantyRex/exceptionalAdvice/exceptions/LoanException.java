package com.Ganty.GantyRex.exceptionalAdvice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class LoanException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public LoanException(){
        super(
                String.format("")
        );
    }
}
