package com.example.banksimulation.loans;

import com.example.banksimulation.Customer;
import com.example.banksimulation.ProductTypes;
import com.example.banksimulation.Utilities;

public class HomeLoan extends Loan{

    private final double LOAN_TYPE_MAXIMUM = 2000000;

    HomeLoan(Customer customer, int length, double amount, int loanNumber) {
        super(customer, length, amount, loanNumber);
        loanType = ProductTypes.HomeLoan.name();
    }

    public HomeLoan(Customer customer, int length, double amount) {
        super(customer, length, amount);
        loanType = ProductTypes.HomeLoan.name();
    }

    @Override
    void setLoanTypeMaximum() {
        this.loanTypeMaximum = LOAN_TYPE_MAXIMUM;

    }

    double interestRate = 6;


    public double calculateHomeLoanInterest(double initialLoan, int length) {
        double loanInterest = Utilities.calculateLoanInterest(initialLoan, length, interestRate);
        return loanInterest;

    }
}
