package com.example.banksimulation.accounts;

import com.example.banksimulation.Customer;
import com.example.banksimulation.ProductTypes;

import java.text.DecimalFormat;


public class SavingsAccount extends Account {

    public SavingsAccount(int accountNumber, double accountBalance, Customer accountHolder) {
        super(accountNumber, accountBalance, accountHolder);
        this.accountType = ProductTypes.Savings.name();
    }
    public SavingsAccount(Customer accountHolder) {
        super(accountHolder);
        this.accountType = ProductTypes.Savings.name();
    }
    public double getInterest() {
        return 0;
    }

}




