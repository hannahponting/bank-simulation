package com.example.banksimulation;

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
}
