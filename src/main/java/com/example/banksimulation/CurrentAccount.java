package com.example.banksimulation;

public class CurrentAccount extends Account{
    CurrentAccount(int accountNumber, int accountBalance, Customer accountHolder) {
        super(accountNumber, accountBalance, accountHolder);
    }

    CurrentAccount(Customer accountHolder) {
        super(accountHolder);
    }
}
