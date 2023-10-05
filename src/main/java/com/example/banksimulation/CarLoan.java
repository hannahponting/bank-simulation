package com.example.banksimulation;

import java.text.DecimalFormat;

public class CarLoan extends Loan{

    CarLoan(Customer customer, int length, double amount, int loanNumber) {
        super(customer, length, amount, loanNumber);
        loanType="CarLoan";
    }

    CarLoan(Customer customer, int length, double amount) {
        super(customer, length, amount);
        loanType="CarLoan";
    }
    double amount;
  
    double interestRate = 7;
    int daysInYear = 365;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public double addInterestToLoan(double initialLoan) {
        double finalLoan = initialLoan;
        for (int i = 0; i < daysInYear; i++) {
            double loanWithInterest = initialLoan * (((interestRate/100))/daysInYear);
            String trimmedNumber = df.format(loanWithInterest);
            double loanWithInterest2 = Double.parseDouble(trimmedNumber);
            finalLoan += loanWithInterest2;
        }
        return finalLoan;
    }
}


