package com.example.banksimulation.loans;

import com.example.banksimulation.Bank;
import com.example.banksimulation.Customer;
import com.example.banksimulation.ProductTypes;
import com.example.banksimulation.Utilities;
import java.text.DecimalFormat;

public class CarLoan extends Loan{
    private final double LOAN_TYPE_MAXIMUM = 50000;

    public CarLoan(Customer customer, int length, double amount, int loanNumber) {
        super(customer, length, amount, loanNumber);
        loanType= ProductTypes.CarLoan.name();
    }

    public CarLoan(Customer customer, int length, double amount) {
        super(customer, length, amount);
        loanType=ProductTypes.CarLoan.name();
    }

    @Override
    void setLoanTypeMaximum() {
        this.loanTypeMaximum = LOAN_TYPE_MAXIMUM;
    }

    double amount;
  
    double interestRate = 7;


    private static final DecimalFormat df = new DecimalFormat("0.00");

    public double calculateCarLoanInterest(double initialLoan, int length) {

        double loanInterest = Utilities.calculateLoanInterest(initialLoan, length, interestRate);
        return loanInterest;
    }
}


