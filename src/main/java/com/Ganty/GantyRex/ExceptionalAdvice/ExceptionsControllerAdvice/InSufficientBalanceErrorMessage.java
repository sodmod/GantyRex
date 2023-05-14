package com.Ganty.GantyRex.ExceptionalAdvice.ExceptionsControllerAdvice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class InSufficientBalanceErrorMessage {
    private int statusCode;
    private String message;
    private Date timestamp;
}
