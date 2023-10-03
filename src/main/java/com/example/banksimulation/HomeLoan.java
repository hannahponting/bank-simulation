package com.example.banksimulation;

public class HomeLoan extends Loan{

    String loanType = "HomeLoan";

    int loanDuration;

    double loanAmount;
    Customer customer;

    HomeLoan(Customer customer, int length, double amount, String loanType){
        this.loanDuration=length;
        this.loanAmount=amount;
        this.customer = customer;

    }
}
