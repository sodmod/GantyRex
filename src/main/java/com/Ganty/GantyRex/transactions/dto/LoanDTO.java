package com.Ganty.GantyRex.transactions.dto;

import lombok.Data;

import java.util.List;

@Data
public class LoanDTO {
    private float capitalBorrowed;
    private long accountNumber;
    private List<GuarantorsDTO> guarantorsDTO;
}
