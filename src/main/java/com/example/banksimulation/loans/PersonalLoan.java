package com.example.banksimulation.loans;

import com.example.banksimulation.Customer;

import java.text.DecimalFormat;

public class PersonalLoan extends Loan{

    public PersonalLoan(Customer customer, int length, double amount, int loanNumber) {
        super(customer, length, amount, loanNumber);
        loanType="PersonalLoan";
    }


    public PersonalLoan(Customer customer, int length, double amount) {
        super(customer, length, amount);
        loanType="PersonalLoan";
    }
}
