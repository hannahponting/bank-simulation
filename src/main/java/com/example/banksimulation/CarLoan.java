package com.example.banksimulation;

public class CarLoan extends Loan {


    CarLoan(Customer customer, int length, double amount, int loanNumber) {
        super(customer, length, amount, loanNumber);
        loanType="CarLoan";
    }

    CarLoan(Customer customer, int length, double amount) {
        super(customer, length, amount);
        loanType="CarLoan";
    }
}


