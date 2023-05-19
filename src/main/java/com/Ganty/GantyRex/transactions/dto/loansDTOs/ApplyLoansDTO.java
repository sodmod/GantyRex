package com.Ganty.GantyRex.transactions.dto.loansDTOs;

import com.Ganty.GantyRex.transactions.dto.GuarantorsDTO;
import lombok.Data;

import java.util.List;

@Data
public class ApplyLoansDTO {
    private float capitalBorrowed;
    private long accountNumber;
    private List<GuarantorsDTO> guarantorsDTO;
}
