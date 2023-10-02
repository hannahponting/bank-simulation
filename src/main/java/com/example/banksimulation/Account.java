package com.example.banksimulation;

public class Account {
    int accountNumber;
    double accountBalance;
    Customer accountHolder;
    private static int nextAccountNumber = 1;
    public void deposit(){};
    public void withdraw(){};
    Account(int accountNumber, int accountBalance, Customer accountHolder){
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountHolder = accountHolder;
    }
    Account(Customer accountHolder){
        this.accountHolder = accountHolder;
        accountBalance = 0;
        accountNumber = nextAccountNumber++;

    }
}
