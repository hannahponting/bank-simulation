package com.example.banksimulation;

public class Loan {

    String loanType;
    int loanDuration;
    public static int nextLoan = 1;
    public int loanNumber;


    double loanAmount;

    Customer customer;

    Loan(Customer customer, int length, double amount, int loanNumber){
        this.loanDuration=length;
        this.loanAmount=amount;
        this.customer = customer;
        this.loanNumber = loanNumber;
        Loan.nextLoan++;
    }

    Loan(Customer customer, int length, double amount){
        this.loanDuration=length;
        this.loanAmount=amount;
        this.customer = customer;
        this.loanNumber = Loan.nextLoan++;
    }

    public void makeRepayment(double repaymentAmount){
        if (repaymentAmount > loanAmount){
            throw new IllegalArgumentException("That is greater than the remaining balance");
        }
        loanAmount -= repaymentAmount;
    }


}
