package org.api.events.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Investment {
    private Double initial_Investment;
    private Double final_Investment;
    private int time_in_years;
    private Double rate_of_interest;
    private int time_in_months;



    public double PTR(int time_in_months, double rate_of_interest, double initial_Investment) {
        return (initial_Investment * time_in_months * rate_of_interest)/100;
    }


    public int total_hundreds(double initial_Investment ){
        return (int)(initial_Investment/100);
    }

    public int getMonths(double years){
        return (int)(years * 12);
    }



    public static void fuck(){

    }


}
