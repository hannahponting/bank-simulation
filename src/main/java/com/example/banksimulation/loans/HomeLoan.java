package com.example.banksimulation.loans;

import com.example.banksimulation.Customer;

import java.text.DecimalFormat;

public class HomeLoan extends Loan{

    private final double LOAN_TYPE_MAXIMUM = 2000000;

    HomeLoan(Customer customer, int length, double amount, int loanNumber) {
        super(customer, length, amount, loanNumber);
        loanType = "HomeLoan";
    }

    public HomeLoan(Customer customer, int length, double amount) {
        super(customer, length, amount);
        loanType = "HomeLoan";
    }

    @Override
    void setLoanTypeMaximum() {
        this.loanTypeMaximum = LOAN_TYPE_MAXIMUM;

    }

    double interestRate = 6;

    private static final DecimalFormat df = new DecimalFormat("0.00");
    public double addInterestToLoan(double initialLoan, int length) {
        double finalLoan;

        double x = Math.pow((1 + ((interestRate/100)/12)), (length * 12)) - 1;
        double y = ((interestRate/100)/12) * Math.pow((1 + ((interestRate/100)/12)),(length * 12));

        double loanWithInterest = initialLoan / ( x / y );
        String trimmedNumber = df.format(loanWithInterest);
        double loanWithInterest2 = Double.parseDouble(trimmedNumber);
        finalLoan = loanWithInterest2;


        return finalLoan;
    }
}
