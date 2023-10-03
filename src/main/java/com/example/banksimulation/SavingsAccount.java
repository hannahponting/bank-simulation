package com.example.banksimulation;

public class SavingsAccount extends Account {



    SavingsAccount(int accountNumber, double accountBalance, Customer accountHolder) {
        super(accountNumber, accountBalance, accountHolder);
        }


    SavingsAccount(Customer accountHolder) {
        super(accountHolder);
    }



    public double getInterest() {
        return 0;
    }


}
