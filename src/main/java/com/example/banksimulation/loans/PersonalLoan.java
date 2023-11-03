package com.example.banksimulation.loans;

import com.example.banksimulation.Customer;
import com.example.banksimulation.ProductTypes;

import java.text.DecimalFormat;

public class PersonalLoan extends Loan{

    private final double LOAN_TYPE_MAXIMUM = 45000;

    public PersonalLoan(Customer customer, int length, double amount, int loanNumber) {
        super(customer, length, amount, loanNumber);
        loanType= ProductTypes.PersonalLoan.name();
    }


    public PersonalLoan(Customer customer, int length, double amount) {
        super(customer, length, amount);
        loanType=ProductTypes.PersonalLoan.name();
    }

    @Override
    void setLoanTypeMaximum() {
        this.loanTypeMaximum = LOAN_TYPE_MAXIMUM;

    }
}
