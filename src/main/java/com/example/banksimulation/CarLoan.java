package com.example.banksimulation;

public class CarLoan extends Loan{

    String loanType ="car";
    int loanDuration;

    double loanAmount;
    Customer customer;

    CarLoan(Customer customer, int length, double amount){
        this.loanDuration=length;
        this.loanAmount=amount;
        this.customer = customer;

    }
}
