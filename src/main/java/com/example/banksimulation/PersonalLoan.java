package com.example.banksimulation;

public class PersonalLoan extends Loan{

    PersonalLoan(Customer customer, int length, double amount, int loanNumber) {
        super(customer, length, amount, loanNumber);
        loanType="PersonalLoan";
    }

    PersonalLoan(Customer customer, int length, double amount) {
        super(customer, length, amount);
        loanType="PersonalLoan";
    }
}
