package com.example.banksimulation.loans;

import com.example.banksimulation.Customer;
import com.example.banksimulation.Utilities;

public class HomeLoan extends Loan{



    HomeLoan(Customer customer, int length, double amount, int loanNumber) {
        super(customer, length, amount, loanNumber);
        loanType = "HomeLoan";
    }

    public HomeLoan(Customer customer, int length, double amount) {
        super(customer, length, amount);
        loanType = "HomeLoan";
    }

    double interestRate = 6;


    public double calculateHomeLoanInterest(double initialLoan, int length) {
        double loanInterest = Utilities.calculateLoanInterest(initialLoan, length, interestRate);
        return loanInterest;

    }
}
