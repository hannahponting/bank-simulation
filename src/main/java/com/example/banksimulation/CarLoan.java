package com.example.banksimulation;

import java.text.DecimalFormat;

public class CarLoan extends Loan{

    String loanType ="CarLoan";
    int loanDuration;

    double loanAmount;
    Customer customer;

    CarLoan(Customer customer, int length, double amount, String loanType){
        this.loanDuration=length;
        this.loanAmount=amount;
        this.customer = customer;
        this.loanType = loanType;

    }
    double interestRate = 7;
    int daysInYear = 365;
    private static final DecimalFormat df = new DecimalFormat("0.00");


    public void addInterestToLoan(double initialLoan, int dayOftheYear) {
        double finalLoan = initialLoan;
        for (int i = 0; i < dayOftheYear; i++) {
            double loanWithInterest = initialLoan * (((interestRate/100)*dayOftheYear)/daysInYear);
            String trimmedNumber = df.format(loanWithInterest);
            double loanWithInterest2 = Double.parseDouble(trimmedNumber);
            finalLoan += loanWithInterest2;
            System.out.println(finalLoan);
        }
    }
}
