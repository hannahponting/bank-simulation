package com.example.banksimulation;

public class PersonalLoan extends Loan{

    String loanType = "Personal";
    int loanDuration;

    double loanAmount;
    Customer customer;

    PersonalLoan(Customer customer, int length, double amount){
        this.loanDuration=length;
        this.loanAmount=amount;
        this.customer = customer;

    }

}
