package com.example.banksimulation;

import java.text.DecimalFormat;

public class HomeLoan extends Loan{



    HomeLoan(Customer customer, int length, double amount, int loanNumber) {
        super(customer, length, amount, loanNumber);
        loanType = "HomeLoan";
    }

    HomeLoan(Customer customer, int length, double amount) {
        super(customer, length, amount);
        loanType = "HomeLoan";
    }

    double interestRate = 6;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public double addInterestToLoan(double initialLoan, int length) {
        double finalLoan;

        double x = Math.pow((1 + ((interestRate/100)/12)), (length * 12)) - 1;
        double y = ((interestRate/100)/12) * Math.pow((1 + ((interestRate/100)/12)),(length * 12));

        System.out.println(x);
        System.out.println(y);
        System.out.println(x/y);


        double loanWithInterest = initialLoan / ( x / y );
        String trimmedNumber = df.format(loanWithInterest);
        double loanWithInterest2 = Double.parseDouble(trimmedNumber);
        finalLoan = 12 * loanWithInterest2 * length;

        return finalLoan;
    }
}
