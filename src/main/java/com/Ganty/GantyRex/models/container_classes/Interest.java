package com.Ganty.GantyRex.models.container_classes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class Interest {
    private double interest;
    private float interestToBePaid;
    private float totalMoneyToBeReturned;

    public Interest(double interest, float interestToBePaid, float totalMoneyToBeReturned){
        this.interest = interest;
        this.interestToBePaid = interestToBePaid;
        this.totalMoneyToBeReturned = totalMoneyToBeReturned;
    }
}
