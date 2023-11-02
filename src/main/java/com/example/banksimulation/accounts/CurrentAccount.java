package com.example.banksimulation.accounts;

import com.example.banksimulation.Customer;
import com.example.banksimulation.ProductTypes;

public class CurrentAccount extends Account{
    public CurrentAccount(int accountNumber, double accountBalance, Customer accountHolder) {
        super(accountNumber, accountBalance, accountHolder);
        this.accountType = ProductTypes.Current.name();
    }

    public CurrentAccount(Customer accountHolder) {
        super(accountHolder);
        this.accountType = ProductTypes.Current.name();
    }
}
