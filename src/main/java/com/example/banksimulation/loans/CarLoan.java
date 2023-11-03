package com.example.banksimulation.loans;

import com.example.banksimulation.Customer;
import com.example.banksimulation.Utilities;

import java.text.DecimalFormat;

public class CarLoan extends Loan{

    public CarLoan(Customer customer, int length, double amount, int loanNumber) {
        super(customer, length, amount, loanNumber);
        loanType="CarLoan";
    }

    public CarLoan(Customer customer, int length, double amount) {
        super(customer, length, amount);
        loanType="CarLoan";
    }
    double amount;
  
    double interestRate = 7;


    private static final DecimalFormat df = new DecimalFormat("0.00");

    public double calculateCarLoanInterest(double initialLoan, int length) {

        double loanInterest = Utilities.calculateLoanInterest(initialLoan, length, interestRate);
        return loanInterest;
    }
}


