package org.api.events.service.investmentservice;

import org.api.events.models.Investment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class InvestmentService implements  IInvestmentService {

    @Autowired
    private Investment investment;

    public double getInterestRate(double amount, double rateOfInterest, double years) {
        int months = investment.getMonths(years);
        return (amount * rateOfInterest * months)/100;
    }

    /**
     * Compound interest is interest accumulated from a principal sum and previously accumulated interest.
     * It is the result of reinvesting or retaining interest that would otherwise be paid out,
     * or of the accumulation of debts from a borrower.
     */

    public double compoundInterestRate(double amount, double rateOfInterest, int time_in_months) {
       return amount *= rateOfInterest / time_in_months;
    }





}
