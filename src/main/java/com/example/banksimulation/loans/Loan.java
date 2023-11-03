package com.example.banksimulation.loans;

import com.example.banksimulation.Bank;
import com.example.banksimulation.Customer;

import java.util.ArrayList;

public abstract class Loan {

    public String loanType;
    public int loanDuration;
    public static int nextLoan = 1;
    public int loanNumber;
    protected double loanTypeMaximum;


    public double loanAmount;

    public Customer customer;

    Loan(Customer customer, int length, double amount, int loanNumber) {
        this.loanDuration = length;
        this.loanAmount = amount;
        this.customer = customer;
        this.loanNumber = loanNumber;
    }

    Loan(Customer customer, int length, double amount) {
        checkLoanTypeEligibility(amount);
        this.loanDuration = length;
        this.loanAmount = amount;
        this.customer = customer;
        this.loanNumber = nextLoan;
    }

    public void makeRepayment(double repaymentAmount) {
        if (repaymentAmount > loanAmount) {
            throw new IllegalArgumentException("That is greater than the remaining balance");
        }
        loanAmount -= repaymentAmount;
    }

    void checkLoanTypeEligibility(double loanAmount){
        setLoanTypeMaximum();
        if (loanAmount > loanTypeMaximum){
            throw new IllegalArgumentException("Amount is greater than the maximum");
        };
    }
    abstract void setLoanTypeMaximum();


}
