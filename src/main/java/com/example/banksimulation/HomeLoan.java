package com.example.banksimulation;

public class HomeLoan extends Loan{



    HomeLoan(Customer customer, int length, double amount, int loanNumber) {
        super(customer, length, amount, loanNumber);
        loanType = "HomeLoan";
    }

    HomeLoan(Customer customer, int length, double amount) {
        super(customer, length, amount);
        loanType = "HomeLoan";
    }
}
