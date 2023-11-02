package com.example.banksimulation.loans;

import com.example.banksimulation.Customer;

public abstract class Loan {

    public String loanType;
   public int loanDuration;
    public static int nextLoan = 1;
    public int loanNumber;


    public double loanAmount;

   public Customer customer;

    Loan(Customer customer, int length, double amount, int loanNumber){
        this.loanDuration=length;
        this.loanAmount=amount;
        this.customer = customer;
        this.loanNumber = loanNumber;
    }

    Loan(Customer customer, int length, double amount){
        this.loanDuration=length;
        this.loanAmount=amount;
        this.customer = customer;
        this.loanNumber = nextLoan;
    }

    public void makeRepayment(double repaymentAmount){
        if (repaymentAmount > loanAmount){
            throw new IllegalArgumentException("That is greater than the remaining balance");
        }
        loanAmount -= repaymentAmount;
    }


}
