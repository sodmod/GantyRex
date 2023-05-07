package com.Ganty.GantyRex.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String firstname;
    private String lastname;
    private String othername;
    private String email;
    private String address;
    private String phonenumber;
    private long accountNumber;
}
